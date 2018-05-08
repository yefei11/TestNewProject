package com.itheima.testnewproject.module.home.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.utils.ScreenUtils;
import com.itheima.testnewproject.R;
import com.itheima.testnewproject.bean.HomeIndex;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 头部广告  <br/>
 * Author : zhongw <br/>
 * CreateDate : 2017/5/10 16:22 <br/>
 * Version 1.0
 */
public class AdViewHolder extends IndexViewHolder<List<HomeIndex.InnerData>> {
    private final Banner vBannerView;

    public AdViewHolder(ViewGroup viewGroup) {
        super(viewGroup, R.layout.layout_banner);
        vBannerView = convertView.findViewById(R.id.banner_view);
        vBannerView.setIndicatorGravity(BannerConfig.CENTER)
                .isAutoPlay(true)
                .setImageLoader(new ImageLoaderInterface<ImageView>() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        String imageUrl = (String) path;
                        loadImage(imageView, imageUrl);
                       /* Glide.with(context)
//                                .load(AppInitManager.getInstance().getBseImageUrl(Property.BANNER) + photoName)
                                                .load("http://192.168.1.108:18099/banner/" + imageUrl)
                                .placeholder(R.drawable.defaul_iamg_large).into(imageView);*/
                    }

                    @Override
                    public ImageView createImageView(Context context) {
                        return (ImageView) LayoutInflater.from(context).inflate(R.layout.item_banner_image, null);
                    }
                });
        ViewGroup.LayoutParams params = vBannerView.getLayoutParams();
        params.height = ScreenUtils.getScreenWidth() / 2;
        vBannerView.setLayoutParams(params);
    }


  /*  public void bindData(List<HomeIndex.InnerData> item) {
        Observable.from(item).map(new Func1<HomeIndex.InnerData, String>() {
            @Override
            public String call(HomeIndex.InnerData innerData) {
                return innerData.getPhotoName();
            }
        }).toList().subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                vBannerView.setImages(strings);
                vBannerView.start();
            }
        });

    }*/

    @Override
    public void updateView(List<HomeIndex.InnerData> item) {
        super.updateView(item);
        Observable.from((item))
                .map(new Func1<HomeIndex.InnerData, String>() {
                    @Override
                    public String call(HomeIndex.InnerData data) {
                        return data.getPhotoName();
                    }
                })
                .toList()
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> photoNames) {
                        vBannerView.setImages(photoNames);
                        vBannerView.start();
                    }
                });
    }

    public Banner getBannerView() {
        return vBannerView;
    }
}


   /* private final Banner vBannerView;

    public AdViewHolder(ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        vBannerView = (Banner) convertView.findViewById(R.id.banner_view);
        vBannerView.setIndicatorGravity(BannerConfig.CENTER)
                .isAutoPlay(true)
                .setImageLoader(new ImageLoaderInterface<ImageView>() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        String imageUrl = (String) path;
                        loadImage(imageView, imageUrl);
                    }

                    @Override
                    public ImageView createImageView(Context context) {
                        return (ImageView) LayoutInflater.from(context).inflate(R.layout.item_banner_image, null);
                    }
                });
        vBannerView.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                HomeIndex.InnerData data = getItem().get(position);
                jumpTo(context, data);
            }
        });

        ViewGroup.LayoutParams params = vBannerView.getLayoutParams();
        params.height = ScreenUtils.getScreenWidth(viewGroup.getContext()) / 2;
        vBannerView.setLayoutParams(params);
    }

    @Override
    public void updateView(List<HomeIndex.InnerData> item) {
        super.updateView(item);
        Observable.from((item))
                .map(new Func1<HomeIndex.InnerData, String>() {
                    @Override
                    public String call(HomeIndex.InnerData data) {
                        return data.getPhotoName();
                    }
                })
                .toList()
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> photoNames) {
                        vBannerView.setImages(photoNames);
                        vBannerView.start();
                    }
                });
    }

    public Banner getBannerView() {
        return vBannerView;
    }*/
