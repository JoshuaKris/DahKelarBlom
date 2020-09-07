package com.example.dahkelarblom.view.menuAdmin;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.service.InternetService;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;
    private MutableLiveData<Boolean> isStoreOpen = new MutableLiveData<>();
    private Context context;

    public SettingViewModel(Context context) {
        this.context = context;
        this.internetService = new InternetService(context);
    }

    public LiveData<Boolean> getisStoreOpen() {
        return isStoreOpen;
    }

    public void updateOperationalTime(JsonObject jsonObject) {
        apiCall = InternetService.getServicesApi().updateOperationalTime(jsonObject);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.d("getUpdate", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
