package com.freexiaoyu.cloud.service;

import android.content.Context;


import com.freexiaoyu.cloud.Interface.ICallBack;
import com.freexiaoyu.cloud.bean.BaseResultBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DIY on 2017/3/31. 16:25
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class RequestCallBack<T> {
    private final static int RESULT_SUCESS_CODE=200;
    private BaseResultBean<T> baseResultBean;
    public void RXReqeust(Context context,Observable<BaseResultBean<T>> mObservable, final ICallBack<T> callBack) {
        mObservable.subscribeOn(Schedulers.io())//请求数据的事件发生在io线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<BaseResultBean<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResultBean<T> t) {
                        baseResultBean = t;
                    }

                    @Override
                    public void onError(Throwable e) {
                        //数据错误
                        callBack.onFailure("", e.getMessage(), "数据错误");
                    }

                    @Override
                    public void onComplete() {
                        if (baseResultBean != null) {
                            if (baseResultBean.getStatus() == RESULT_SUCESS_CODE) {
                                //数据正确，把数据返回
                                callBack.onSuccess("", "", baseResultBean.getData());
                            } else {
                                callBack.onFailure(String.valueOf(baseResultBean.getStatus()), "", baseResultBean.getMessage());
                            }
                        } else {
                            //数据错误
                            callBack.onFailure("", "", "数据错误");
                        }

                    }
                });
    }

    public void CallReqeust(Context context,Call<BaseResultBean<T>> call, final ICallBack<T> callBack) {
        call.enqueue(new Callback<BaseResultBean<T>>() {
            @Override
            public void onResponse(Call<BaseResultBean<T>> call, Response<BaseResultBean<T>> response) {
                if (response.isSuccessful()) {
                    BaseResultBean<T> resut = response.body();
                    if (resut.getStatus() == RESULT_SUCESS_CODE) {
                        //数据正确，把数据返回
                        callBack.onSuccess("", "", resut.getData());
                    } else {
                        //数据错误
                        callBack.onFailure("", "", resut.getMessage());
                    }

                } else {
                    //数据错误
                    callBack.onFailure("", "", "数据错误");
                }
            }

            @Override
            public void onFailure(Call<BaseResultBean<T>> call, Throwable t) {
            }
        });
    }

}
