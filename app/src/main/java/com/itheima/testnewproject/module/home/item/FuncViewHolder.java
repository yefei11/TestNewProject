package com.itheima.testnewproject.module.home.item;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.ToastUtils;
import com.itheima.testnewproject.R;
import com.itheima.testnewproject.bean.HomeIndex;
import com.itheima.testnewproject.common.loading.DialogLoading;
import com.itheima.testnewproject.network.ServerAddress;

import java.util.List;

import de.greenrobot.event.EventBus;
import okhttp3.HttpUrl;

/**
 * 功能集合  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/5/10 16:22 <br/>
 * Version 1.0
 */
public class FuncViewHolder extends IndexViewHolder<List<HomeIndex.InnerData>> {

    @SuppressWarnings("deprecation")
    public FuncViewHolder(ViewGroup viewGroup) {
        super(viewGroup, R.layout.item_index_func);

    }

}
