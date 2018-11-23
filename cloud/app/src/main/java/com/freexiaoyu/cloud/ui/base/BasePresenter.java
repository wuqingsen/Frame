package com.freexiaoyu.cloud.ui.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by DIY on 2017/4/17. 11:52
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public abstract class BasePresenter<V> {

    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewRef.get();
    }

}
