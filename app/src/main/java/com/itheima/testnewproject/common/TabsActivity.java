package com.itheima.testnewproject.common;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.itheima.testnewproject.R;
import com.itheima.testnewproject.utils.UIUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Tabs 页  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/3/20 17:12 <br/>
 * Version 1.0
 */
public class TabsActivity extends BaseActivity {
    @Bind(R.id.tabs_title_layout)
    TabLayout layoutTitleLayout;
    @Bind(R.id.tabs_page_vp)
    ViewPager vpPages;

    private List<Tab> mTabs = new ArrayList<>(5);

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tabs;
    }

    @Override
    protected void initData(Intent intent) {
        super.initData(intent);
    }

    public void addTab(String title, Fragment fragment) {
        mTabs.add(new Tab(title, fragment));
    }

    public List<Tab> getTabs() {
        return mTabs;
    }

    public ViewPager getViewpager() {
        return vpPages;
    }

    @Override
    protected void initView(View contentView) {
        super.initView(contentView);
        vpPages.setOffscreenPageLimit(mTabs.size());
        vpPages.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position).fragment;
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTabs.get(position).title;
            }
        });
        layoutTitleLayout.setupWithViewPager(vpPages);
    }

    protected class Tab {
        String title;
        Fragment fragment;

        Tab(String title, Fragment fragment) {
            this.title = title;
            this.fragment = fragment;
        }

        public String getTitle() {
            return title;
        }

        public Fragment getFragment() {
            return fragment;
        }
    }

    public void setIndicator(int leftDip, int rightDip) {
        Class<?> tabLayout = layoutTitleLayout.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(layoutTitleLayout);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) (UIUtils.dip2px(leftDip));
        int right = (int) (UIUtils.dip2px(rightDip));

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

}
