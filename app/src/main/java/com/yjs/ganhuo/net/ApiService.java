package com.yjs.ganhuo.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

import com.yjs.ganhuo.GanHuoApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangjingsong on 16/6/15.
 */
public class ApiService {
    private static final String path = Environment.getExternalStorageDirectory().getPath() + "/Ganhuo/cache";

    public static <T> T getApi(String baseurl, Class<T> api) {

        Retrofit retrofit = new Retrofit.Builder()
                .client(getOKHttpClient())
                .baseUrl(baseurl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(api);
    }

    public static File getCacheDir() {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    private static OkHttpClient getOKHttpClient() {
        File cacheFile = new File(GanHuoApplication.mContext.getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(netWorkInterceptor)
                .addInterceptor(netWorkInterceptor)
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.i("RetrofitLog","retrofitBack = "+message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .build();
//        okHttpClient.interceptors().add(netWorkInterceptor);
//        okHttpClient.networkInterceptors().add(netWorkInterceptor);
        return okHttpClient;
    }


    /**
     * 判断网络是否可用
     *
     * @param context Context对象
     */
    public static Boolean isNetworkReachable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private static Interceptor netWorkInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            if (!isNetworkReachable(GanHuoApplication.getmContext())) {//没网强制从缓存读取(必须得写，不然断网状态下，退出应用，或者等待一分钟后，就获取不到缓存）
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            Response responseLatest;
            if (isNetworkReachable(GanHuoApplication.getmContext())) {
                int maxAge = 60; //有网失效一分钟
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 6; // 没网失效6小时
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }

        }
    };


}
