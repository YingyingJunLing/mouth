package com.example.mouth.utils;

import com.example.mouth.api.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils
{
    /**
     * 单例
     */
    public static RetrofitUtils instance;
    private final Retrofit retrofit;

    public static RetrofitUtils getInstance() {
        if(instance == null)
        {
            synchronized (RetrofitUtils.class)
            {
                if(instance==null)
                {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }
    /**
     * 写okhttp
     */
    public OkHttpClient getOkHttp()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                .connectTimeout(3, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        return client;
    }
    /**
     * 写retrofit
     */
    public RetrofitUtils()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttp())
                .build();
    }
    //写泛型
    public <T> T create(Class<T> clazz)
    {
        return retrofit.create(clazz);
    }

}
