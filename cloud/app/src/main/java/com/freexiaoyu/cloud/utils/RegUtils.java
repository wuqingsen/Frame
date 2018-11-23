package com.freexiaoyu.cloud.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DIY on 2017/5/3. 17:56
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:正则工具类
 */

public class RegUtils {
    /**
     * 邮箱验证
     * @param strEmail 要验证的字符
     * @return
     */
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 验证手机
     * @param mobile 要验证的字符
     * @return
     */
    public static boolean isMobile(String mobile) {
        String strPattern = "^1[\\d]{10}$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }


    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isUrl(String str){
        String strPattern = "[http|https]+://[^s]*";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
