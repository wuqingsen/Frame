package com.freexiaoyu.cloud.utils;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES3Utils {

    private static final String Algorithm = "DESede";

    private static final String PASSWORD_CRYPT_KEY = "cloudCLOUD";

    /**
     * @param src
     * @return
     */
    public static byte[] encryptMode(byte[] src) {
        try {

            SecretKey deskey = new SecretKeySpec(
                    build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);

            Cipher cipher = Cipher.getInstance(Algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            return cipher.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }


    public static byte[] decryptMode(byte[] src) {
        try {
            SecretKey deskey = new SecretKeySpec(
                    build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }


    public static byte[] build3DesKey(String keyStr)
            throws UnsupportedEncodingException {
        byte[] key = new byte[24];
        byte[] temp = keyStr.getBytes("UTF-8");

        if (key.length > temp.length) {
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }


    public static String DESEncryption(String content) {
        byte[] secretArr = DES3Utils.encryptMode(content.getBytes());
        return Base64.encode(secretArr);
    }


    public static String DESDecryption(String content) {
        byte[] myMsgArr = content.getBytes();
        return Base64.encode(myMsgArr);
    }
}
