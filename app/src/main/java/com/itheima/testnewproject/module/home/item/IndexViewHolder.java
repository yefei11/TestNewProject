package com.itheima.testnewproject.module.home.item;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itheima.testnewproject.R;
import com.itheima.testnewproject.app.MyApplication;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/5/10 16:17 <br/>
 * Version 1.0
 */
public class IndexViewHolder<T> extends BaseViewHolder {

    private T mItem;

    public IndexViewHolder(ViewGroup viewGroup, @LayoutRes int itemLayoutRes) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(itemLayoutRes, viewGroup, false));
    }

    public void updateView(T item) {
        mItem = item;
    }

    public T getItem() {
        return mItem;
    }


    protected void loadImage(ImageView imageView, String photoName) {
        imageView.setBackgroundColor(Color.parseColor("#E8E8E8"));
        Glide.with(MyApplication.getInstance())
//                .load(AppInitManager.getInstance().getBseImageUrl(Property.BANNER) + photoName)
                .load("http://192.168.1.108:18099/banner/" + photoName)
                .placeholder(R.drawable.defaul_iamg_large)
                .centerCrop().into(imageView);
    }


}
