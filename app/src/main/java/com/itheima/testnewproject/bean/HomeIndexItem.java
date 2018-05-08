package com.itheima.testnewproject.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;


/**
 * 创建者     yf
 * 创建时间   2018/5/7 13:48
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class HomeIndexItem implements MultiItemEntity {
    /**
     * Item 头部广告
     */
    public static final int ITEM_AD = 10;
    /**
     * Item 功能集合
     */
    public static final int ITEM_FUNC = 11;
    /**
     * Item 特色服务
     */
    public static final int ITEM_SERVICES = 12;
    /**
     * Item 产学研
     */
    public static final int ITEM_PRODUCTION = 13;
    /**
     * Item  创业管理
     */
    public static final int ITEM_ENTREPRENEURSHIP = 14;
    /**
     * Item  投融专区
     */
    public static final int ITEM_INVESTMENT = 15;
    /**
     * Item  精选理财
     */
    public static final int ITEM_FINANCE = 16;
    /**
     * Item  创选优品
     */
    public static final int ITEM_GOODS = 17;

    private int itemType = 0;
    private Object mObject;

    public HomeIndexItem(int itemType, Object object) {
        this.itemType = itemType;
        mObject = object;
    }

    public Object getData() {
        return mObject;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
