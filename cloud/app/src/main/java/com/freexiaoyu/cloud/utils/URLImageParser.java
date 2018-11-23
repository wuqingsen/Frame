package com.freexiaoyu.cloud.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * Created by DIY on 2017/8/24. 10:29
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class URLImageParser implements Html.ImageGetter {

    private Context mContext;
    private TextView tvContent;
    private int actX;//实际的宽  放大缩小基于textview的宽度
    private int actY;

    public URLImageParser(Context context, TextView tvContent) {
        this.mContext = context;
        this.tvContent = tvContent;
        actX = CommonUtil.widthPixels(context);
        actY = CommonUtil.heightPixels(context);
    }

    @Override
    public Drawable getDrawable(String source) {
        final URLDrawable urlDrawable = new URLDrawable();

        /***
         Glide.with(context)
         .asBitmap()
         .load(source)
         .into(new SimpleTarget<Bitmap>(){
        @Override
        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
        int x = resource.getWidth();
        int y = resource.getHeight();
        if (x > actX || y > actY) {
        //进行等比例缩放程序
        Matrix matrix = new Matrix();
        matrix.postScale((float) (actX * 1.00 / x), (float) (actX * 1.00 / x));
        //长和宽放大缩小的比例
        resource = Bitmap.createBitmap(resource, 0, 0, x, y, matrix, true);
        }
        urlDrawable.bitmap = resource;
        urlDrawable.setBounds(0, 0, resource.getWidth(), resource.getHeight());
        tvContent.invalidate();
        tvContent.setText(tvContent.getText()); // 解决图文重叠
        }
        });
         */
        Glide.with(mContext).asBitmap().load(source).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onStop() {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public void onLoadStarted(Drawable placeholder) {

            }

            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                int x = resource.getWidth();
                int y = resource.getHeight();
                //进行等比例缩放程序
                Matrix matrix = new Matrix();
                matrix.postScale((float) (actX * 1.00 / x), (float) (actX * 1.00 / x));
                //长和宽放大缩小的比例
                resource = Bitmap.createBitmap(resource, 0, 0, x, y, matrix, true);
                urlDrawable.bitmap = resource;
                //urlDrawable.setBounds(0, 0, CommonUtil.pixelsToDp(mContext,resource.getWidth()) ,CommonUtil.pixelsToDp(mContext,resource.getHeight()));
                //urlDrawable.setBounds(0, 0, actX, CommonUtil.pixelsToDp(mContext, 800));
                urlDrawable.setBounds(0, 0, resource.getWidth(), resource.getHeight());
                tvContent.setText(tvContent.getText()); // 解决图文重叠
            }

            @Override
            public void onLoadCleared(Drawable placeholder) {

            }


            @Override
            public void setRequest(Request request) {

            }

            @Override
            public Request getRequest() {
                return null;
            }
        });
        return urlDrawable;
    }

    public class URLDrawable extends BitmapDrawable {
        protected Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }
    }
}
