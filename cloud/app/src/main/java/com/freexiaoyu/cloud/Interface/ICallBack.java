package com.freexiaoyu.cloud.Interface;

/**
 * Created by DIY on 2016/11/14. 17:27
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */


public interface ICallBack<T> {
    void onSuccess(String flag, String key, T t);

    void onFailure(String flag, String key, String why);
}