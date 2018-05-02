package com.itheima.testnewproject.common;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.TintTypedArray;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ConvertUtils;
import com.itheima.testnewproject.R;
import com.itheima.testnewproject.app.Extra;
import com.itheima.testnewproject.common.back.BlackBack;
import com.itheima.testnewproject.common.back.WhiteBack;
import com.itheima.testnewproject.common.loading.Loading;
import com.itheima.testnewproject.utils.StatusBarUtil;
import com.itheima.testnewproject.utils.ViewUtil;
import com.itheima.testnewproject.widget.LoadingLayout;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * 基础Activity,包括公共部分的功能.
 * Author : zhongw <br/>
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private View mContentView;
    private boolean showUpButton = true;
    private TextView tvTitle;

    protected LoadingLayout vLoadingLayout;

    private Loading mLoading;

    @LayoutRes
    abstract protected int getLayoutResId();


    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        initInjector();
        super.onCreate(savedInstanceState);
        if (getLayoutResId() > 0)
            setContentView(getLayoutResId());

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        initData(getIntent());
        if (getIntent() != null && getIntent().hasExtra(Extra.TITLE)) {
            setTitle(getIntent().getStringExtra(Extra.TITLE));
        }
    }

    protected void initInjector() {
    }

    protected void initData(Intent intent) {
    }

    @CallSuper
    protected void initView(View contentView) {
        LoadingLayout loadingLayout = ViewUtil.findView(contentView, R.id.layout_loading);

        if (loadingLayout == null) {
            loadingLayout = new LoadingLayout(this);
            loadingLayout.setId(R.id.layout_loading);

            if (contentView.getId() == android.R.id.content) {
                ((ViewGroup) contentView).addView(loadingLayout,
                        new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.MATCH_PARENT));
            } else {
                ViewGroup parentViewGroup =
                        contentView.getId() == android.R.id.content ?
                                ((ViewGroup) contentView) : (ViewGroup) contentView.getParent();
                parentViewGroup.removeView(contentView);
                loadingLayout.addView(contentView);
                parentViewGroup.addView(loadingLayout,
                        new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.MATCH_PARENT));
            }

        }
        //在loadinglayout中有三种布局：正在加载中、加载数据为空，加载失败，当加载为空或失败时
        //在子类中重写onreloadclick()方法即可实现：点击布局重新加载
        loadingLayout.setOnLoadingClickListener(new LoadingLayout.OnLoadingClickListener() {
            @Override
            public void onReloadClick() {
                BaseActivity.this.onReloadClick();
            }
        });
        //loadinglayout是loading的子类实现，通过setLoading（）方法我们得到loadinglayout对象，子类中
        //通过调用mLoading.onstart()、onError(Throwable),onfinished控制progressbar的显示与否并显示具体什么错误
        setLoading(loadingLayout);
        vLoadingLayout = loadingLayout;
    }

    public void onReloadClick() {
    }

    public Loading getLoading() {
        return mLoading;
    }

    public void setLoading(Loading loading) {
        mLoading = loading;
    }

    public LoadingLayout getLoadingLayout() {
        return vLoadingLayout;
    }


    public BaseActivity getActivity() {
        return this;
    }


    public View getContentView() {
        ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
        if (mContentView == null) {
            mContentView = rootView.getChildCount() != 0 ? rootView.getChildAt(0) : rootView;
        }
        return mContentView;
    }

    public void setShowUpButton(boolean showUpButton) {
        this.showUpButton = showUpButton;
    }

    //Activity彻底运行起来之后的回调
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initActionBar();
        if (!isChild()) {
            onTitleChanged(getTitle(), getTitleColor());
        }
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(showUpButton);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setDisplayShowHomeEnabled(true);
            if (getDrawerToggleDelegate() != null) {
                if (this instanceof WhiteBack)
                    actionBar.setHomeAsUpIndicator(R.drawable.back_white_s);
                else if (this instanceof BlackBack)
                    actionBar.setHomeAsUpIndicator(R.drawable.back_black);
                else
                    actionBar.setHomeAsUpIndicator(getDrawerToggleDelegate().getThemeUpIndicator());
            }
            if (tvTitle != null) {
                actionBar.setCustomView(tvTitle);
            }
            actionBar.setElevation(ConvertUtils.dp2px(0));
            //            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //                actionBar.setElevation(ConvertUtils.dp2px(1));
            //            }
        }
    }

    public void setNoTopLine(boolean has) {
        ActionBar actionBar = getSupportActionBar();
        if (!has && actionBar != null) {
            actionBar.setElevation(ConvertUtils.dp2px(0));
        }
    }

    public void setCloseIndicatorDrawable() {
        Drawable closeDrawable = ContextCompat.getDrawable(this, R.drawable.titleclose);
        //        DrawableCompat.setTint(closeDrawable, Color.WHITE);
        setUpIndicatorDrawable(closeDrawable);
    }

    public void setUpIndicatorDrawable(@Nullable Drawable indicator) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(indicator);
    }

    private void setCenterTitle(CharSequence title) {
        if (tvTitle == null) {
            tvTitle = new TextView(this);

            //获取ActionBar原本Title的样式，并应用于自定义的Title。
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(this,
                    null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
            final int titleTextStyle = a.getResourceId(R.styleable.ActionBar_titleTextStyle, 0);
            if (titleTextStyle != 0) {
                tvTitle.setTextAppearance(this, titleTextStyle);
            }

            tvTitle.setMaxLines(2);
            tvTitle.setEllipsize(TextUtils.TruncateAt.END);

            ActionBar.LayoutParams params = new
                    ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                    ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
            tvTitle.setLayoutParams(params);
        }
        tvTitle.setText(title);
    }

    protected void setSingleLineCenterTitle(CharSequence title) {
        if (tvTitle == null) {
            tvTitle = new TextView(this);

            //获取ActionBar原本Title的样式，并应用于自定义的Title。
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(this,
                    null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
            final int titleTextStyle = a.getResourceId(R.styleable.ActionBar_titleTextStyle, 0);
            if (titleTextStyle != 0) {
                tvTitle.setTextAppearance(this, titleTextStyle);
            }

            tvTitle.setMaxLines(1);
            tvTitle.setEllipsize(TextUtils.TruncateAt.END);

            ActionBar.LayoutParams params = new
                    ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                    ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
            tvTitle.setLayoutParams(params);
        }
        tvTitle.setText(title);
    }

    protected void setTitleAppearance(int resId) {
        tvTitle.setTextAppearance(this, resId);
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        setCenterTitle(title);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        initView(getContentView());
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.main_color_normal));
    }

    protected void setStatusBarColor(int resId) {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, resId));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //支持Fragment监听按键事件.Fragment需要实现KeyEvent.Callback接口
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof KeyEvent.Callback && fragment.getUserVisibleHint()) {
                    KeyEvent.Callback callback = (KeyEvent.Callback) fragment;
                    if (callback.onKeyDown(keyCode, event)) {
                        return true;
                    }
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof KeyEvent.Callback && fragment.getUserVisibleHint()) {
                    KeyEvent.Callback callback = (KeyEvent.Callback) fragment;
                    if (callback.onKeyLongPress(keyCode, event)) {
                        return true;
                    }
                }
            }
        }
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof KeyEvent.Callback && fragment.getUserVisibleHint()) {
                    KeyEvent.Callback callback = (KeyEvent.Callback) fragment;
                    if (callback.onKeyUp(keyCode, event)) {
                        return true;
                    }
                }
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int count, KeyEvent event) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof KeyEvent.Callback && fragment.getUserVisibleHint()) {
                    KeyEvent.Callback callback = (KeyEvent.Callback) fragment;
                    if (callback.onKeyMultiple(keyCode, count, event)) {
                        return true;
                    }
                }
            }
        }
        return super.onKeyMultiple(keyCode, count, event);
    }

}
