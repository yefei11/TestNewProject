package com.itheima.testnewproject.common.web;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Keep;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.blankj.utilcode.utils.ToastUtils;
import com.itheima.testnewproject.utils.UriUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

import static android.app.Activity.RESULT_OK;

public class DefaultWebChromeClient extends WebChromeClient {

    private ValueCallback<Uri> mFilePathCallback;
    private ValueCallback<Uri[]> mFilesPathCallback;

    private Context mContext;
    private Activity mActivity;
    private Fragment mFragment;

    public DefaultWebChromeClient(Activity activity) {
        mActivity = activity;
        mContext = activity;
    }

    public DefaultWebChromeClient(Fragment fragment) {
        mFragment = fragment;
        mContext = fragment.getActivity();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Keep
    @Override
    public boolean onShowFileChooser(WebView webView, final ValueCallback<Uri[]> filePathCallback, final WebChromeClient.FileChooserParams fileChooserParams) {
        mFilesPathCallback = filePathCallback;

        RxPermissions.getInstance(mContext)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean isGranted) {
                        if (isGranted) {
                            if (mActivity != null) {
                                mActivity.startActivityForResult(fileChooserParams.createIntent(), 0);
                            } else if (mFragment != null) {
                                mFragment.startActivityForResult(fileChooserParams.createIntent(), 0);
                            }
                        } else {
                            ToastUtils.showShortToast("请授予读取媒体文件权限！");
                            onFileChooseResult(0, 0, null);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        onFileChooseResult(0, 0, null);
                    }
                });

        return true;
    }

    @Keep
    public void openFileChooser(final ValueCallback<Uri> uploadFile, final String acceptType, String capture) {
        mFilePathCallback = uploadFile;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(acceptType);
        if (mActivity != null) {
            mActivity.startActivityForResult(intent, 0);
        } else if (mFragment != null) {
            mFragment.startActivityForResult(intent, 0);
        }
    }

    public void onFileChooseResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            Uri realFileUri = null;
            switch (resultCode) {
                case RESULT_OK:
                    realFileUri = getRealFileUri(data.getDataString());
                    break;
            }

            if (mFilePathCallback != null) {
                mFilePathCallback.onReceiveValue(realFileUri);
            } else if (mFilesPathCallback != null) {
                Uri[] uris = realFileUri == null ? null : new Uri[]{realFileUri};
                mFilesPathCallback.onReceiveValue(uris);
            }
        }
    }

    private Uri getRealFileUri(final String uriString) {
        if (TextUtils.isEmpty(uriString))
            return Uri.EMPTY;
        return Uri.fromFile(UriUtil.getFile(mContext, Uri.parse(uriString)));
    }
}
