package com.freexiaoyu.cloud.utils;


import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by DIY on 2017/5/3. 17:57
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */
public class Camera {


    public static String getCameraPhoto(Context context, int requestcode) {
        String url = null;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ContentValues values = new ContentValues();
        Uri photoUri = context.getContentResolver().insert(
                Media.EXTERNAL_CONTENT_URI, values);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        ((Activity) context).startActivityForResult(intent, requestcode);
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(photoUri, null, null, null, null);
        cursor.moveToFirst();
        if (cursor != null) {
            url = cursor.getString(1);
            cursor.close();
        }
        return url;
    }

    public static String getCameraPhoto(Context context, Fragment fragment,
                                        int requestcode) {
        String url = null;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ContentValues values = new ContentValues();
        Uri photoUri = context.getContentResolver().insert(
                Media.EXTERNAL_CONTENT_URI, values);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        fragment.startActivityForResult(intent, requestcode);
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(photoUri, null, null, null, null);
        cursor.moveToFirst();
        if (cursor != null) {
            url = cursor.getString(1);
            cursor.close();
        }
        return url;
    }


    /***
     * 调用系统剪切
     * @param uri
     * @param outputX
     * @param outputY
     * @param requestCode
     */
    public static void cropImageUri(Context context, Uri uri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        ((Activity) context).startActivityForResult(intent, requestCode);
    }


    public static void cropImageUri(Context context, android.support.v4.app.Fragment fragment, Uri uri, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        fragment.startActivityForResult(intent, requestCode);
    }

    /***
     * 调用系统相机
     * @param context
     * @param requestcode
     */
    public static void startCamera(Context context, int requestcode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
        ((Activity) context).startActivityForResult(intent, requestcode);
    }


    /***
     * 调用系统相机
     * @param context 上下文
     * @param filePath 指定图片路径
     * @param requestcode 返回标识
     */
    public static void startCameraUrl(Context context, String filePath, int requestcode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uri;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            uri = Uri.fromFile(new File(filePath));
        } else {
            /**
             * 7.0 调用系统相机拍照不再允许使用Uri方式，应该替换为FileProvider
             * 并且这样可以解决MIUI系统上拍照返回size为0的情况
             */
            uri = FileProvider.getUriForFile(context, context.getPackageName()+ ".provider"
                    , new File(filePath));
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        ((Activity) context).startActivityForResult(intent, requestcode);
    }

    /***
     * 调用系统相机
     * @param fragment 上下文
     * @param filePath 指定图片路径
     * @param requestcode 返回标识
     */
    public static void startCameraUrl(Context context,android.support.v4.app.Fragment fragment, String filePath, int requestcode){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uri;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            uri = Uri.fromFile(new File(filePath));
        } else {
            /**
             * 7.0 调用系统相机拍照不再允许使用Uri方式，应该替换为FileProvider
             * 并且这样可以解决MIUI系统上拍照返回size为0的情况
             */
            uri = FileProvider.getUriForFile(context,context.getPackageName() + ".provider", new File(filePath));
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        fragment.startActivityForResult(intent, requestcode);
    }

    /***
     * 调用系统相册
     * @param context
     * @param requestcode
     */
    public static void startPhotoAlbum(Context context, int requestcode) {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT, null);
            intent.setType("image/*");
        } else {
            intent = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI);
        }
        ((Activity) context).startActivityForResult(intent, requestcode);
    }


    /***
     * 调用系统相册
     * @param context
     * @param requestcode
     */
    public static void startPhotoAlbum(Context context, android.support.v4.app.Fragment fragment, int requestcode) {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT, null);
            intent.setType("image/*");
        } else {
            intent = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI);
        }
        fragment.startActivityForResult(intent, requestcode);
    }

    /***
     * 调用系统相册并裁剪
     * @param context
     * @param outputX
     * @param outputY
     * @param requestCode
     */
    public static void startPhotoCrop(Context context, int outputX, int outputY, int requestCode) {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT, null);
            intent.setType("image/*");
        } else {
            intent = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI);
        }
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    public static void startPhotoZoom(Context context, Uri uri, String imagePath, int size, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(imagePath));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        ((Activity) context).startActivityForResult(intent, requestCode);
    }


    public static void startPhotoZoom(Context context, android.support.v4.app.Fragment fragment,Uri uri, String imagePath, int size, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(imagePath));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        fragment.startActivityForResult(intent, requestCode);
    }

    public Bitmap decodeUriAsBitmap(Context context, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
}