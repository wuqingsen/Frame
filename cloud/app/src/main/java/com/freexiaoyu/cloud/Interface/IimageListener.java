package com.freexiaoyu.cloud.Interface;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by DIY on 2017/1/19. 10:38
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public interface  IimageListener {
    void display(Context context, ImageView imageView,
                 String url, int progressId, int errorId,
                 Object tag);

    void display(Context context, ImageView imageView,
                 String url, int progressId, int errorId);

    void display(Context context, ImageView imageView,
                 String url, int progressId);

    void display(Context context, ImageView imageView,
                 String url);

    void display(Context context, ImageView imageView, Uri uri);
    void displayCircle(Context context,ImageView imageView,String url);
    void displayCircle(Context mContext, ImageView imageView, String url,int defImgId);
}
