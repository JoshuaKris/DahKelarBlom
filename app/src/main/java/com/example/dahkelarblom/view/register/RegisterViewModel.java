package com.example.dahkelarblom.view.register;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.service.InternetService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;

    public RegisterViewModel(Context context) {
        this.internetService = new InternetService(context);
    }

    public void registerAccount(String merchantName,String username,String password,String email,String phoneNum,String address) {
        apiCall = InternetService.getServicesApi().registerAdmin(merchantName,username,password,email,phoneNum,address);
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
