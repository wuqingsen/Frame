package com.freexiaoyu.cloud.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by DIY on 2017/5/3. 18:00
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:自定义字体
 */

public class FontCustom {
    //字体库路径
    static String fongUrl = "";
    static Typeface tf;

    /***
     * 设置字体
     *
     * @return
     */
    public static Typeface setFont(Context context) {
        if(tf==null){
            tf = Typeface.createFromAsset(context.getAssets(), fongUrl);
        }
        return tf;
    }

    public static void changeFonts(ViewGroup root, Context context) {
        Typeface tf = setFont(context);
        for (int i = 0; i < root.getChildCount(); i++) {
            View v = root.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(tf);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(tf);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setTypeface(tf);
            } else if (v instanceof ViewGroup) {
                changeFonts((ViewGroup) v, context);
            }
        }

    }


    /***
     * 获取该activity所有view
     * @param context
     * @return
     */
    public static List<View> getAllChildViews(Context context) {
        View view = ((Activity) context).getWindow().getDecorView();
        return getAllChildViews(view);
    }

    private static List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList<View>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }


    public static void changeFonts(List<View> view, Context context) {
        Typeface tf = setFont(context);
        for (int i = 0; i < view.size(); i++) {
            View v = view.get(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(tf);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(tf);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setTypeface(tf);
            }
        }

    }


    /***
     * 修改单个控件字体样式
     * @param v 当前控件
     * @param context 上下文
     */
    public static void changeFonts(View v, Context context) {
        Typeface tf = setFont(context);
        if (v instanceof TextView) {
            ((TextView) v).setTypeface(tf);
        } else if (v instanceof Button) {
            ((Button) v).setTypeface(tf);
        } else if (v instanceof EditText) {
            ((EditText) v).setTypeface(tf);
        } else if (v instanceof RadioButton) {
            ((RadioButton) v).setTypeface(tf);
        }
    }

}
