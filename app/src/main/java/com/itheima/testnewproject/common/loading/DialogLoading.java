package com.itheima.testnewproject.common.loading;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.itheima.testnewproject.common.exception.Exceptions;
import com.itheima.testnewproject.utils.Dialogs;


public class DialogLoading extends SimpleLoading {

    private final Context mContext;
    private Dialog mLoadingDialog = null;

    public DialogLoading(@NonNull Context context) {
        mContext = context;
        mLoadingDialog = Dialogs.getLoadingDialog(context);
        stepDialog(mLoadingDialog);
    }

    public void stepDialog(Dialog loadingDialog) {
    }

    public Dialog getDialog() {
        return mLoadingDialog;
    }

    public void setOnCancelListener(@Nullable DialogInterface.OnCancelListener listener) {
        mLoadingDialog.setOnCancelListener(listener);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mLoadingDialog != null)
            mLoadingDialog.show();
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        Exceptions.handleNetworkException( e);
    }
}