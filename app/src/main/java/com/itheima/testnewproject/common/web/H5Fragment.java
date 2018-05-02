package com.itheima.testnewproject.common.web;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.utils.ToastUtils;
import com.itheima.testnewproject.R;
import com.itheima.testnewproject.app.Extra;
import com.itheima.testnewproject.common.fragment.BaseFragment;
import com.itheima.testnewproject.utils.NetUtils;
import com.itheima.testnewproject.utils.TDString;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class H5Fragment extends BaseFragment {

    private String mUrl;

    @Bind(R.id.web_wv)
    public WebView mWebView;

    public static H5Fragment newInstance(String url, String htmlContent) {
        H5Fragment fragment = new H5Fragment();
        Bundle args = new Bundle();
        args.putString(Extra.URL, url);
        args.putString(Extra.HTML_CONTENT, htmlContent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.t2_fragment_web;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWebView();
    }

    protected void initWebView() {
        try {
            mUrl = getArguments().getString(Extra.URL);
        } catch (Exception e) {
            ToastUtils.showShortToast("链接地址为空");
            return;
        }
        if (TextUtils.isEmpty(mUrl)) {
            return;
        }

        if (!NetUtils.isConnected(getActivity())) {
            ToastUtils.showShortToast(TDString.getStr(R.string.net_error));
            return;
        }
        WebSettings settings = mWebView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        //        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBlockNetworkImage(false);
        settings.setDomStorageEnabled(true);  //设置适应Html5的一些方法
        //解决5.0以上图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        if (URLUtil.isNetworkUrl(mUrl) || mUrl.startsWith("file://")) {
            mWebView.loadUrl(mUrl);
        } else {
            mWebView.loadDataWithBaseURL(null, mUrl, "text/html", "utf-8", null);
        }
        mWebView.setWebViewClient(new CustomWebViewClient());
        mWebView.setWebChromeClient(new DefaultWebChromeClient(this));
    }


    class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(final WebView view, String url) {

            mWebView.setVisibility(View.VISIBLE);
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    view.loadUrl("javascript:setWebViewFlag()");
                }
            });
            if (url != null && url.endsWith("/index.html")) {
//                MobclickAgent.onPageStart("index.html");
            }
            imgReset();//重置webview中img标签的图片大小
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            try {
                Log.d("UMHybrid", "shouldOverrideUrlLoading url:" + url);
                String decodedURL = java.net.URLDecoder.decode(url, "UTF-8");
//                UMHybrid.getInstance(getActivity()).execute(decodedURL, view);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //拉起QQ
            try {
                if (url.startsWith("mqqwpa") || url.startsWith("mqqapi")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                }
            } catch (ActivityNotFoundException e) {
                ToastUtils.showShortToast("未安装QQ客户端，请自行安装。");
                return true;
            }
            return false;
        }


        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // TODO Auto-generated method stub
            super.onReceivedError(view, errorCode, description, failingUrl);
            mWebView.setVisibility(View.INVISIBLE);
            ToastUtils.showShortToast(description);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            super.onReceivedSslError(view, handler, error);
        }
    }

    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset() {
        mWebView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }
}
