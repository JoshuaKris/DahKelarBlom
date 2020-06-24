package com.example.dahkelarblom.view;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.service.InternetService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;

    public OrderViewModel(Context context) {
        this.internetService = new InternetService(context);
    }

    public void postMyOrder(String orderType,String name,String phoneNum,String estimation,String info) {
        apiCall = InternetService.getServicesApi().userAddOrder(orderType,name,phoneNum,estimation,info);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
