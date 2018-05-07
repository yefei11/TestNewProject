package com.itheima.testnewproject.bean;


import com.google.gson.annotations.SerializedName;

public class Banner {

    private String bannerUrl;
    private String linkUrl;
    private String title;
    @SerializedName("content_module_type")
    private String moduleType;
    @SerializedName("content_url_type")
    private String urlType;
    @SerializedName("content_borrowid")
    private String borrowId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getModuleType() {
        return moduleType;
    }

    public String getUrlType() {
        return urlType;
    }

    public String getBorrowId() {
        return borrowId;
    }

}
