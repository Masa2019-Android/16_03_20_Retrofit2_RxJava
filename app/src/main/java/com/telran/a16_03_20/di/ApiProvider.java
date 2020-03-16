package com.telran.a16_03_20.di;

import com.telran.a16_03_20.data.provider.web.Api;
import com.telran.a16_03_20.data.provider.web.ApiRx;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {
    private static final ApiProvider instance = new ApiProvider();
    private Api api;
    private ApiRx apiRx;

    public static ApiProvider getInstance() {
        return instance;
    }

    private ApiProvider(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://contacts-telran.herokuapp.com/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(Api.class);
        apiRx = retrofit.create(ApiRx.class);
    }

    public Api getApi(){
        return api;
    }

    public ApiRx getApiRx() {
        return apiRx;
    }
}
