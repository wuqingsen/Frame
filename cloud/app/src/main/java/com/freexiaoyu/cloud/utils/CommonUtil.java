package com.freexiaoyu.cloud.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by DIY on 2017/8/3. 16:02
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class CommonUtil {
    /**
     * 获取APP版本
     *
     * @param context 环境
     * @return String
     */
    public static final int getAppVersionCode(Context context)
            throws PackageManager.NameNotFoundException {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
        return pi.versionCode;
    }

    /**
     * 获取APP版本
     *
     * @param context 环境
     * @return String
     */
    public static final String getAppVersionName(Context context)
            throws PackageManager.NameNotFoundException {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
        return pi.versionName;
    }

    /***
     * 获取手机唯一标识
     * @param context
     * @return
     */
    public static String getIMSI(Context context) {
        TelephonyManager phoneMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return phoneMgr.getDeviceId();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dpToPixel(Context context, float dpValue) {
        final float scale = getDensity(context);
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int pixelsToDp(Context context, float pxValue) {
        final float scale = getDensity(context);
        return (int) (pxValue / scale + 0.5f);
    }

    /***
     * 屏幕宽度（像素）
     * @param context
     * @return
     */
    public static int widthPixels(Context context){
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * 屏幕高度（像素）
     * @param context
     * @return
     */
    public static int heightPixels(Context context){
        return getDisplayMetrics(context).heightPixels;
    }

    /**
     * 获得缩放因子 density
     *
     * @param context
     * @return
     */
    public static float getDensity(Context context) {
        return getDisplayMetrics(context).density;
    }


    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics;
    }

    /***
     * 检查是否安装某个应用
     * @param mContext
     * @param packagename
     * @return
     */
    public static Boolean checkInstall(Context mContext,String packagename){
        PackageInfo packageInfo;
        try {
            packageInfo = mContext.getPackageManager().getPackageInfo(
                    packagename, 0);

        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if(packageInfo ==null){
            return false;
        }else{
            return true;
        }
    }
}
