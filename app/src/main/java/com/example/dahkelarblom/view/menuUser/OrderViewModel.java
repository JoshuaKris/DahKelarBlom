package com.example.dahkelarblom.view.menuUser;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.service.InternetService;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;
    private MutableLiveData<String> userOrder = new MutableLiveData<>();
    private Context context;

    public OrderViewModel(Context context) {
        this.context = context;
        this.internetService = new InternetService(context);
    }

    public LiveData<String> getUserOrder() {
        return userOrder;
    }

    public void postMyOrder(JsonObject jsonObject) {
        apiCall = InternetService.getServicesApi().userAddOrder(jsonObject);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    userOrder.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
