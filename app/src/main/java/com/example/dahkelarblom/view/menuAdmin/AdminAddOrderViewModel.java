package com.example.dahkelarblom.view.menuAdmin;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.service.InternetService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAddOrderViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;

    public AdminAddOrderViewModel(Context context) {
        this.internetService = new InternetService(context);
    }

    public void postAdminOrder(String orderType,String name,String phoneNum,String price,String estimation,String paymentStatus) {
        apiCall = InternetService.getServicesApi().adminAddOrder(orderType,name,phoneNum,price,estimation,paymentStatus);
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
