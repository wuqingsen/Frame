package com.freexiaoyu.cloud.utils;


import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class Zip {
    static int BUFFERSIZE=1024;

    /**
     * Zip 压缩数据
     *
     * @param unZipStr
     * @return
     */
    public static String compressForZip(String unZipStr) {
        if (TextUtils.isEmpty(unZipStr)) {
            return null;
        }
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zip = new ZipOutputStream(baos);
            zip.putNextEntry(new ZipEntry("0"));
            zip.write(unZipStr.getBytes());
            zip.closeEntry();
            zip.close();
            byte[] encode = baos.toByteArray();
            baos.flush();
            baos.close();
            return Base64.encode(encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Zip解压数据
     *
     * @param zipStr
     * @return
     */
    public static String decompressForZip(String zipStr) {
        if (TextUtils.isEmpty(zipStr)) {
            return null;
        }
        byte[] t = Base64.decode(zipStr);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = new ByteArrayInputStream(t);
            ZipInputStream zip = new ZipInputStream(in);
            zip.getNextEntry();
            byte[] buffer = new byte[BUFFERSIZE];
            int n = 0;
            while ((n = zip.read(buffer, 0, buffer.length)) > 0) {
                out.write(buffer, 0, n);
            }
            zip.close();
            in.close();
            out.close();
            return out.toString("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
