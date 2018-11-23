package com.freexiaoyu.cloud.service;

import android.os.StatFs;
import android.text.TextUtils;


import com.freexiaoyu.cloud.CloudApplication;
import com.freexiaoyu.cloud.service.cookies.CookiesManager;
import com.freexiaoyu.cloud.utils.AppConfig;
import com.freexiaoyu.cloud.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by DIY on 2017/04/17
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class BuildService {
    private static Retrofit retrofit;

    public static ApiService getCloud() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConfig.HOT_URL)//设置Base的访问路径
                    .client(defaultOkHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())//字符串
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }

    /**这个方法获取显示图片明显没有上面的client快*/
    public static OkHttpClient defaultOkHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .addInterceptor(new CacheControlInterceptor())
            .addInterceptor(new QueryParameterInterceptor())
            .addNetworkInterceptor(new CacheControlInterceptor())
            //.cookieJar(new CookiesManager())//增加cookie
            .cache(getCache())
            .build();


    //公共参数
   public static class QueryParameterInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request request;
            String method = originalRequest.method();
            Headers headers = originalRequest.headers();
            HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                    // Provide your custom parameter here
                    .addQueryParameter("platform", "android")
                    .addQueryParameter("ver",AppConfig.VER )
                    .build();
            request = originalRequest.newBuilder().url(modifiedUrl).build();
            return chain.proceed(request);
        }
    };


    public static class CacheControlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();

            // 无网络时加载缓存
            if (!NetUtil.isNetworkAvailable(CloudApplication.getAppContext())) {
                if (!TextUtils.isEmpty(cacheControl)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();

                    Response originalResponse = chain.proceed(request);

                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                            .removeHeader("Pragma")
                            .build();
                } else {
                    return chain.proceed(request);
                }


            } else {
                Response originalResponse = chain.proceed(request);

                if (cacheControl != null && cacheControl.length() != 0) {
                    // 获取请求头的缓存设置，直接设置到响应头中
                    // 这样就可以通过设置请求头来缓存内容
                    return originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 对于图片，不作处理
                    return originalResponse;
                }
            }
        }
    }

    public static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            Request request = chain.request();
            long t1 = System.nanoTime();//请求发起的时间
            Logger.getAnonymousLogger().info(String.format("发送请求 %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();//收到响应的时间
            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            Logger.getAnonymousLogger().info(String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                    response.request().url(),
                    responseBody.string(),
                    (t2 - t1) / 1e6d,
                    response.headers()));

            return response;
        }
    }

    /**
     * This is copied from Picasso
     */
    private static long calculateDiskCacheSize(File dir) {
        long size = 5 * 1024 * 1024;
        try {
            StatFs statFs = new StatFs(dir.getAbsolutePath());
            long available = ((long) statFs.getBlockCount()) * statFs.getBlockSize();
            size = available / 50;
        } catch (IllegalArgumentException ignored){

        }
        // Bound inside min/max size for disk cache.
        return Math.max(Math.min(size, 50 * 1024 * 1024), 5 * 1024 * 1024);
    }

    private static Cache getCache() {
        File file = new File(CloudApplication.getAppContext().getCacheDir(), "GlobalCache");
        return new Cache(file, calculateDiskCacheSize(file));
    }
}
