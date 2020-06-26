package com.example.dahkelarblom.view.menuAdmin;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.service.InternetService;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAddOrderViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;
    private MutableLiveData<String> adminAddOrder = new MutableLiveData<>();
    private Context context;

    public AdminAddOrderViewModel(Context context) {
        this.context = context;
        this.internetService = new InternetService(context);
    }

    public LiveData<String> getAdminAddOrder() {
        return adminAddOrder;
    }

    public void postAdminOrder(JsonObject jsonObject) {
        apiCall = InternetService.getServicesApi().adminAddOrder(jsonObject);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    adminAddOrder.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
