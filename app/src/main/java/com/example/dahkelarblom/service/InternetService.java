package com.example.dahkelarblom.service;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.dahkelarblom.utils.Constants;
import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InternetService {

    @SuppressLint("StaticFieldLeak")
    private static ChuckInterceptor chuckInterceptor;
    private static Retrofit retrofit;
    private static ServicesApi servicesApi;

    public InternetService(Context context) {
        chuckInterceptor = new ChuckInterceptor(context);
    }

//    private static OkHttpClient client = new OkHttpClient.Builder()
//            .addInterceptor(chuckInterceptor)
//            .build();

//    private static Retrofit.Builder retrofitBuilder =
//            new Retrofit.Builder()
//                    .baseUrl(Constants.BASE_URL)
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create());

//    private static Retrofit retrofit =
//            retrofitBuilder.build();

//    private static ServicesApi servicesApi =
//            retrofit.create(ServicesApi.class);
//
//    public static ServicesApi getServicesApi(){
//        return servicesApi;
//    }

    private static void initializeRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chuckInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static ServicesApi getServicesApi() {
        initializeRetrofit();
        servicesApi = retrofit.create(ServicesApi.class);
        return servicesApi;
    }
}
