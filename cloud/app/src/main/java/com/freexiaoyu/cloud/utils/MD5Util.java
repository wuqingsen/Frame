package com.freexiaoyu.cloud.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by DIY on 2017/7/31. 16:00
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class MD5Util {
    public final static String md5(String s) {
        try {
            //得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(s.getBytes());
            StringBuffer buffer = new StringBuffer();
            //要把每一个byte做一个与运算0xff,0xff是十六进制，十进制为255
            for (byte b : result) {
                //与运算
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                //如果位数不够前面加个零
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            //标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }

    public static String md52(String s) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
