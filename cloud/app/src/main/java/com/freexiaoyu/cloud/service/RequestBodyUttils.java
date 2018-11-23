package com.freexiaoyu.cloud.service;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by DIY on 2017/08/03
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class RequestBodyUttils {
    final public static String MEDIA_TYPE_Text = "text/plain";
    final public static String MEDIA_TYPE_Json = "application/json;charset=UTF-8";

    public static RequestBody stringRequestBody(String params) {
        return RequestBody.create(MediaType.parse(MEDIA_TYPE_Json), params);
    }

    public static RequestBody stringRequestBody2(String params) {
        return RequestBody.create(MediaType.parse(MEDIA_TYPE_Json), params);
    }

    public static Map<String, RequestBody> getMapBody(Map<String, String> map) {
        Map<String, RequestBody> mapParams = new HashMap<>();
        for (String key : map.keySet()) {
            mapParams.put(key, RequestBodyUttils.stringRequestBody(map.get(key)));
        }
        return mapParams;
    }
}
