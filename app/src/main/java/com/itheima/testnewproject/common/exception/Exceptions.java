
package com.itheima.testnewproject.common.exception;

import android.text.TextUtils;

import com.blankj.utilcode.utils.ToastUtils;
import com.itheima.testnewproject.BuildConfig;
import com.itheima.testnewproject.network.exception.NoNetworkException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import timber.log.Timber;

public final class Exceptions {

    @SuppressWarnings("ThrowableInstanceNeverThrown")
    public static final EmptyException EMPTY_EXCEPTION = new EmptyException();

    private Exceptions() {
        throw new IllegalStateException("No instances!");
    }

    public static void handleNetworkException(Throwable e) {
        String errorMessage = getNetworkExceptionMessage(e);
        if (!TextUtils.isEmpty(errorMessage)) {
            ToastUtils.showShortToast(errorMessage);
        }
    }

    public static String getNetworkExceptionMessage(Throwable e) {
        Timber.e(e);
        String errorMessage = null;
        if (e instanceof ResultMessageException) {
            errorMessage = e.getMessage();
        } else if (e instanceof SocketTimeoutException
                || e.getCause() instanceof SocketTimeoutException) {
            errorMessage = "连接超时，请稍后重试";
        } else if (e instanceof HttpException) {
            HttpException httpException = ((HttpException) e);
            switch (httpException.code()) {
                case 404:
                case 500:
                    errorMessage = "内部服务器错误";
                    break;
            }
        } else if (e instanceof NoNetworkException) {
            errorMessage = "请检查网络连接";
        } else {
            if (BuildConfig.DEBUG) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter writer = new PrintWriter(stringWriter);
                e.printStackTrace(writer);
                writer.flush();
                errorMessage = "Debug 模式才会出现：\n" + stringWriter.toString();
            } else {
                errorMessage = "App不正常工作啦！";
            }
        }
        return errorMessage;
    }
}
