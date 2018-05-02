package com.itheima.testnewproject.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.itheima.testnewproject.R;
import com.itheima.testnewproject.common.exception.EmptyException;
import com.itheima.testnewproject.common.exception.Exceptions;
import com.itheima.testnewproject.common.loading.Loading;
import com.itheima.testnewproject.utils.ViewUtil;


/**
 * 控制加载状态的Layout , 正在加载中 , 加载错误 , 加载数据空.  <br/>
 * Author : zhongw <br/>
 */
public class LoadingLayout extends FrameLayout implements Loading {

    ViewFlipper vfControlLayout;
    ViewGroup vgContentLayout;
    ProgressBar pbLoading;
    TextView tvEmpty;
    TextView tvError;
    TextView tvEmptyBottom;
    private OnLoadingClickListener listener;
    private OnBottomTvClickListener mOnBottomTvClickListener;
    private boolean add2ContentLayout = false;

    public LoadingLayout(Context context) {
        super(context);
        init();
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setId(R.id.layout_loading);
        View view = View.inflate(getContext(), R.layout.t2_layout_load, this);
        vfControlLayout = (ViewFlipper) view.findViewById(R.id.layout_load_controller_vf);
        vgContentLayout = (ViewGroup) view.findViewById(R.id.load_content_layout);
        pbLoading = (ProgressBar) view.findViewById(R.id.load_content_pb);
        tvEmpty = (TextView) view.findViewById(R.id.load_empty_tv);
        tvError = (TextView) view.findViewById(R.id.load_error_tv);
        tvEmptyBottom = (TextView) view.findViewById(R.id.load_empty_tv_bottom);
        tvEmptyBottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottomTvClickListener != null) {
                    mOnBottomTvClickListener.onBottomTvClick();
                }
            }
        });
        OnClickListener refreshClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) {
                    return;
                }
                vfControlLayout.setDisplayedChild(0);
                listener.onReloadClick();
            }
        };
        view.findViewById(R.id.load_empty_layout).setOnClickListener(refreshClickListener);
        view.findViewById(R.id.load_error_layout).setOnClickListener(refreshClickListener);
        add2ContentLayout = true;
    }

    public void setOnLoadingClickListener(OnLoadingClickListener listener) {
        this.listener = listener;
    }

    public ViewGroup getContentLayout() {
        return vgContentLayout;
    }

    @Override
    public void addView(@NonNull View child) {
        if (add2ContentLayout)
            vgContentLayout.addView(child);
        else
            super.addView(child);
    }

    @Override
    public void addView(@NonNull View child, int index) {
        vgContentLayout.addView(child, index);
        if (add2ContentLayout)
            vgContentLayout.addView(child, index);
        else
            super.addView(child, index);
    }

    @Override
    public void addView(@NonNull View child, int width, int height) {
        if (add2ContentLayout)
            vgContentLayout.addView(child, width, height);
        else
            super.addView(child, width, height);
    }

    @Override
    public void addView(@NonNull View child, ViewGroup.LayoutParams params) {
        if (add2ContentLayout)
            vgContentLayout.addView(child, params);
        else
            super.addView(child, params);
    }

    @Override
    public void addView(@NonNull View child, int index, ViewGroup.LayoutParams params) {
        if (add2ContentLayout)
            vgContentLayout.addView(child, index, params);
        else
            super.addView(child, index, params);
    }

    @Override
    public void onStart() {
        ViewUtil.setInvisible(pbLoading, false);
    }

    @Override
    public void onFinish() {
        ViewUtil.setInvisible(pbLoading, true);
        vfControlLayout.setDisplayedChild(0);
    }

    @Override
    public void onError(Throwable e) {
        ViewUtil.setInvisible(pbLoading, true);
        if (e instanceof EmptyException) {
            setEmpty(e.getMessage());
        } else {
            setError(Exceptions.getNetworkExceptionMessage(e));
        }
    }

    public void setEmpty(String text) {
        vfControlLayout.setDisplayedChild(1);
        //ViewUtil.setText(tvEmpty, text);
    }

    public void setError(String text) {
        vfControlLayout.setDisplayedChild(2);
        ViewUtil.setText(tvError, text);
    }

    public void setOnBottomTvClickListener(OnBottomTvClickListener onBottomTvClickListener) {
        mOnBottomTvClickListener = onBottomTvClickListener;
    }

    public interface OnLoadingClickListener {
        void onReloadClick();
    }

    public interface OnBottomTvClickListener {
        void onBottomTvClick();
    }

    public TextView getTvEmptyBottom() {
        return tvEmptyBottom;
    }
}