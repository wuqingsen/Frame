package com.freexiaoyu.cloud.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.freexiaoyu.cloud.Interface.IimageListener;
import com.freexiaoyu.cloud.R;

/**
 * Created by DIY on 2017/5/3. 17:41
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class FreeImageLoader implements IimageListener {
    protected int defaultDrawable = R.mipmap.ic_launcher;//默认显示图片

    private static FreeImageLoader instance = null;

    public FreeImageLoader() {
        super();
    }

    public static long imageSize = 0;

    // 单例模式
    public static FreeImageLoader getInstance() {
        if (instance == null) {
            instance = new FreeImageLoader();
        }
        return instance;
    }

    @Override
    public void display(Context context, ImageView imageView, String url, int progressId, int errorId, Object tag) {
        showImage(context, url, imageView, errorId, errorId);
    }

    @Override
    public void display(Context context, ImageView imageView, String url, int progressId, int errorId) {
        showImage(context, url, imageView, errorId, errorId);
    }

    @Override
    public void display(Context context, ImageView imageView, String url, int progressId) {
        showImage(context, url, imageView, defaultDrawable, defaultDrawable);
    }

    @Override
    public void display(Context context, ImageView imageView, String url) {
        showImage(context, url, imageView, defaultDrawable, defaultDrawable);
    }

    @Override
    public void display(Context context, ImageView imageView, Uri uri) {
        Glide.with(context)
                .load(uri)
                .into(imageView);
    }

    public void showImage(Context mContext, String url, ImageView imageView, int defImgId, int errorImgId) {
        RequestOptions requestOptions = setRequestOptions(defImgId, errorImgId);
        Glide.with(mContext).load(url).apply(requestOptions).into(imageView);
    }

    @Override
    public void displayCircle(Context mContext, ImageView imageView, String url) {
        RequestOptions requestOptions = setRequestOptions(defaultDrawable, defaultDrawable);
        requestOptions.transform(new CircleTransform(mContext));
        Glide.with(mContext).load(url).apply(requestOptions).into(imageView);
    }

    @Override
    public void displayCircle(Context mContext, ImageView imageView, String url, int defImgId) {
        RequestOptions requestOptions = setRequestOptions(defImgId, defImgId);
        requestOptions.transform(new CircleTransform(mContext));
        Glide.with(mContext).load(url).apply(requestOptions).into(imageView);
    }


    public RequestOptions setRequestOptions(int defImgId, int errorImgId) {
        return new RequestOptions()
                .placeholder(defImgId)
                .error(errorImgId);
    }
}
