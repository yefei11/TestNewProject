package com.itheima.testnewproject.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import com.itheima.testnewproject.R;


/**
 * Dialog 弹出框  <br/>
 * Author : zhongw <br/>
 */
public class Dialogs {

    private Dialogs() {
    }

    public static Dialog getLoadingDialog(Context context) {
        AppCompatDialog loadingDialog = new AppCompatDialog(context);
        loadingDialog.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        View contentView = new ProgressBar(context);
        contentView.setMinimumHeight(context.getResources().getDimensionPixelOffset(R.dimen.height_tall));
        contentView.setMinimumWidth(context.getResources().getDimensionPixelOffset(R.dimen.width_tall));
        int padding = context.getResources().getDimensionPixelOffset(R.dimen.spacing_normal);
        contentView.setPadding(padding, padding, padding, padding);
        loadingDialog.setContentView(contentView);
        loadingDialog.setCanceledOnTouchOutside(false);
        return loadingDialog;
    }
}
