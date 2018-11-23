package com.freexiaoyu.cloud.ui.base;

/**
 * Created by DIY on 2017/4/17. 11:59
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:实体类 父类
 */

public class BaseBean<T> {
    private int code;
    private String msg;
    private int  status;
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
