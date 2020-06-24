package com.example.dahkelarblom.view.login;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.service.InternetService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;

    public LoginViewModel(Context context) {
        this.internetService = new InternetService(context);
    }

    public void loginAdmin(String username,String password) {
        apiCall = InternetService.getServicesApi().loginAdmin(username,password);
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
