package com.itheima.testnewproject.common.web;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.utils.ToastUtils;
import com.itheima.testnewproject.common.loading.Loading;
import com.itheima.testnewproject.event.LoadPageFinish;

import de.greenrobot.event.EventBus;
import timber.log.Timber;

public class DefaultWebViewClient extends WebViewClient {
    private Context mContext;
    private Loading mLoading;

    public DefaultWebViewClient(Context context, Loading loading) {
        mContext = context;
        mLoading = loading;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Timber.d("shouldOverrideUrlLoading->%s", url);

        if (url.startsWith("tk")) {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            return true;
        }

        //拉起QQ
        try {
            if (url.startsWith("mqqwpa") || url.startsWith("mqqapi")) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                return true;
            }
        } catch (ActivityNotFoundException e) {
            ToastUtils.showShortToast("未安装QQ客户端，请自行安装。");
            return true;
        }
//        if (!CommonUtil.isUrlWithToken(url)) {
//            view.loadUrl(CommonUtil.getUrlWithToken(url));
//            return true;
//        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        Timber.d("onPageStarted->%s", url);
        if (mLoading != null)
            mLoading.onStart();
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Timber.d("onPageFinished->%s", url);
        EventBus.getDefault().post(new LoadPageFinish());
        if (mLoading != null)
            mLoading.onFinish();
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description,
                                String failingUrl) {
        Timber.d("onReceivedError->%s", failingUrl);
        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }
}