package com.itheima.testnewproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.itheima.testnewproject.common.BaseActivity;
import com.itheima.testnewproject.module.home.view.HomeFragment;
import com.itheima.testnewproject.module.investment.InvestmentFragment;
import com.itheima.testnewproject.module.jinfu.view.JinfuFragment;
import com.itheima.testnewproject.module.mine.MineFragment;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity {


    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rbt_index)
    RadioButton rbtIndex;
    @Bind(R.id.rbt_investment)
    RadioButton rbtInvestment;
    @Bind(R.id.rbt_jinfu_home)
    RadioButton rbtJinfuHome;
    @Bind(R.id.rbt_mine)
    RadioButton rbtMine;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    ArrayList<Fragment> mTabFragments;
    int mPosition;
    Fragment mFragment;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Intent intent) {
        super.initData(intent);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        if (mTabFragments ==null ){
            mTabFragments = new ArrayList<>();
            mTabFragments.add(new HomeFragment());
            mTabFragments.add(new InvestmentFragment());
            mTabFragments.add(new JinfuFragment());
            mTabFragments.add(new MineFragment());
        }
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbt_index:
                        mPosition =0;

                        break;
                    case R.id.rbt_investment:
                        mPosition =1;

                        break;
                    case R.id.rbt_jinfu_home:
                        mPosition =2;

                        break;
                    case R.id.rbt_mine:
                        mPosition =3;
                        break;

                    default:
                        break;
                }
                Fragment fragment = getFragment(mPosition);
                switchFragment(mFragment,fragment);
            }
        });
        rgMain.check(R.id.rbt_index);
    }

    private void switchFragment(Fragment fromFragment, Fragment nextFragment) {
        if (mFragment != nextFragment){
            mFragment = nextFragment;
            if (nextFragment !=null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()){
                    if (fromFragment !=null){
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout,nextFragment).commit();
                }else{
                    if (fromFragment !=null){
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }

            }
        }

    }

    private Fragment getFragment(int position) {
        if (mTabFragments !=null && mTabFragments.size()>0){
           Fragment fragment =  mTabFragments.get(position);
            return fragment;
        }
        return null;
    }
}
