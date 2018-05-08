package com.itheima.testnewproject.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.ColorRes;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.itheima.testnewproject.app.MyApplication;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


public class UIUtils {


    public static Context getContext() {
        return MyApplication.getInstance();
    }

    /**
     * 得到上下文
     */
    public static Context getAPPContext() {
        return MyApplication.getContext();
    }

    /**
     * dip转换px
     */
    public static int dip2px2(double dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    /**
     * 获取资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }


    /**
     * 获取两个日期之间的间隔天数
     *
     * @return
     */
    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }


    /**
     * 显示虚拟键盘
     */
    public static void ShowKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 隐藏虚拟键盘
     */
    public static void HideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

        }
    }
    public static void hideInput(Activity activity, View view) {
        ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void hideInput2(Activity activity) {
        InputMethodManager im = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im.isActive()) {
            im.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    public static void showInput(Activity activity, View view) {
        InputMethodManager im = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }


    /**
     * 获取long 类型的时间转化成"yy-MM-dd HHmmss"
     *
     * @param time
     * @return
     */
    public static String formatLong(Long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");
        String dateString = formatter.format(time);
        return dateString;
    }



    /**
     * 设置表
     *
     * @param content
     * @param view
     */
    public static void handleDrawableTextView(String content, TextView view, Drawable drawable) {
        if (TextUtils.isEmpty(content)) {
            view.setText("");
            return;
        }
        SpannableString spannableString = null;
        if (drawable != null) {
            spannableString = new SpannableString("  " + content);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            spannableString.setSpan(new ImageSpan(drawable), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            spannableString = new SpannableString(content);
        }
        view.setText(spannableString);
    }

    public static Handler getMainHandler() {
        return new Handler(MyApplication.getInstance().getMainLooper());
    }

    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    public static String getSmartStr(String oriStr, int maxLength, int wantLength) {
        if (TextUtils.isEmpty(oriStr)) {
            return null;
        } else {

            if (oriStr.length() >= maxLength) {
                String newName = oriStr.substring(0, wantLength) + "...";
                return newName;
            } else {
                return oriStr;
            }
        }
    }

    /**
     * 时间格式为2016-06-15 16:33:21的时间的月份，年份 返回格式为2016年或者6月 type=1为年份 type=2为月份
     */
    public static String getMonOrYear(String date, int type) {//type:1,年  2，月,3 年和月
        if (TextUtils.isEmpty(date)) {
            return "time is null";
        }
        if (date.length() < 7) {
            return "time format error";
        }
        String dateStr = "";
        if (type == 1) {
            dateStr = date.substring(0, 4);
        } else if (type == 2) {
            dateStr = date.substring(5, 7);
        } else if (type == 3) {
            dateStr = date.substring(0, 4) + "年" + date.substring(5, 7) + "月";
        } else if (type == 4) {
            dateStr = date.substring(5, 7) + "-" + date.substring(8, 10) + "";
        }
        return dateStr;
    }




    public static String getFormatMoney(String money) {
        if (TextUtils.isEmpty(money)) {
            return "";
        }
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("##,###.00");
        return (myformat.format(money));
    }
    public static String getFormatMoney2(String money) {
        if (TextUtils.isEmpty(money)) {
            return "";
        }
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("###");
        return (myformat.format(money));
    }
    /**
     * 对输入金额做两位小数限制
     *
     * @param editText
     */
    public static void setInputLimit(final EditText editText) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = s.toString();
                if (temp.equals(".")) {
                    editText.setText("");
                }
                int posDot = temp.indexOf(".");
                if (posDot <= 0) {
                    return;
                }

                if (temp.length() - posDot - 1 > 2) {
                    s.delete(posDot + 3, posDot + 4);
                }

            }
        });

    }

    private static long lastClickTime = 0;

    public static synchronized boolean isFastDoubleClick() {
        return isFastDoubleClick(500);
    }

    public static synchronized boolean isFastDoubleClick(int time) {
        long currentTime = System.currentTimeMillis();
        long doubleTime = currentTime - lastClickTime;
        lastClickTime = currentTime;
        if (0 < doubleTime && doubleTime < time) { // 500ms内不能同时起效
            return true;
        }
        return false;
    }

    public static void customString(TextView textView, String str1, String str2, int resId1, int resId2) {
        String content = str1 + str2;
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new ForegroundColorSpan(getColor(resId1)), 0, content.length() - str2.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getColor(resId2)), content.length() - str2.length(), content.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
    }

    public static void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 3;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(16, 16, 16, 16);
        // 设置参数
        listView.setLayoutParams(params);
    }


    /**
     * 解决小米手机上获取图片路径为null的情况
     *
     * @param intent
     * @return
     */
    public static Uri geturi(Intent intent) {
        Uri uri = intent.getData();
        String type = intent.getType();
        if (uri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = MyApplication.getInstance().getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.ImageColumns._ID},
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        return uri;
    }

    public static void setText(TextView tv, CharSequence charSequence) {
        if (tv == null) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        tv.setText(charSequence);
    }

    public static void setText(TextView tv, int content) {
        setText(tv, content + "");
    }

    public static void setTextColor(TextView tv, @ColorRes int color) {
        if (tv != null) {
            tv.setTextColor(getResources().getColor(color));
        }
    }

    public static void setVisiable(View view, boolean visiable) {
        if (view == null) return;
        view.setVisibility(visiable ? View.VISIBLE : View.GONE);
    }

    public static void setVisiable(View view, int visiable) {
        if (view == null) return;
        view.setVisibility(visiable);
    }


    public static void setEnable(View view, boolean enable) {
        if (view == null) return;
        view.setEnabled(enable);
    }

    /* 手机号码显示 * 号*/
    public static String phoneNoHide(String str) {
        String afterStr = str;
        if (!TextUtils.isEmpty(str) && str.length() > 6) {
            StringBuilder stringBuilder = new StringBuilder(str);
            afterStr = stringBuilder.replace(3, 7, "****").toString();
        }
        return afterStr;
    }


    /* 名字显示 * 号*/
    public static String nameHide(String str) {
        String afterStr = str;
        int length = afterStr.length();
        if (!TextUtils.isEmpty(afterStr) && length > 0) {
            StringBuilder stringBuilder = new StringBuilder(afterStr);
            for (int i = 0; i < length; i++) {
                if (i != length - 1) {
                    afterStr = stringBuilder.replace(i, i + 1, "*").toString();
                }
            }
        }
        return afterStr;
    }
}
