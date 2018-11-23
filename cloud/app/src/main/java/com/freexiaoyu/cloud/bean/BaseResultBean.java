package com.freexiaoyu.cloud.bean;

/**
 * Created by DIY on 2017/5/3. 17:26
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:接口返回结果基类
 */

public class BaseResultBean<T> {
    private int status;
    private String message;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
