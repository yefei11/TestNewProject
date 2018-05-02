package com.itheima.testnewproject.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.itheima.testnewproject.R;
import com.itheima.testnewproject.common.fragment.BaseFragment;

import butterknife.Bind;

/**
 * 创建者     yf
 * 创建时间   2018/4/28 18:37
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class HomeFragment extends BaseFragment {
    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = View.inflate(getActivity(),R.layout.fragment_home,null);
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        ButterKnife.bind(this, view);
//        tv.setText("我是第一个fragment");
//        ToastUtils.showShortToast("这是怎么了");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        ToastUtils.showShortToast("ceshishuju");
        Log.i("homefragment","shujune");
    }
}
