package com.demo.www.demo.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author:  zhouchaoxiang
 * date:    2018/6/22
 * explain:
 */
public class NetCore {
    private final Retrofit mRetrofit;

    private final NetApi mNetApi;

    private static class Instance {
        private static NetCore instance = new NetCore();
    }

    public static NetCore getInstance() {
        return Instance.instance;
    }

    private NetCore() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8080/android/")
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mNetApi = mRetrofit.create(NetApi.class);
    }

    public NetApi getNetApi() {
        return mNetApi;
    }

    /**
     * OkHttpClient
     */
    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();
    }
}
