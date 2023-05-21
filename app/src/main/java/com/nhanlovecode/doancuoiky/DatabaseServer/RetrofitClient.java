package com.nhanlovecode.doancuoiky.DatabaseServer;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit instance;
    public  static  Retrofit getInstance(String BASEURL){

        ExecutorService executor = Executors.newFixedThreadPool(5); // Số luồng thực thi yêu cầu
        Dispatcher dispatcher = new Dispatcher(executor);
        dispatcher.setMaxRequests(5); // Số yêu cầu tối đa được thực thi cùng lúc

        OkHttpClient client = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .addInterceptor(new RetryInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        if (instance == null){
            instance = new  Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return instance;
    }
}
