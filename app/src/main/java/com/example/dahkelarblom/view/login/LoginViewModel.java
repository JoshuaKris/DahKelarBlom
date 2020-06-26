package com.example.dahkelarblom.view.login;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.service.InternetService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;
    private Context context;

    private MutableLiveData<Boolean> statusSuccess = new MutableLiveData<>();
    private MutableLiveData<String> idMerchant = new MutableLiveData<>();

    public LiveData<Boolean> getStatusSuccess() {
        return statusSuccess;
    }

    public LiveData<String> getIdMerchant() {
        return idMerchant;
    }

    public LoginViewModel(Context context) {
        this.context = context;
        internetService = new InternetService(context);
    }

    public void login(JsonObject jsonObject) {
        apiCall = InternetService.getServicesApi().loginAdmin(jsonObject);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String s = response.body();
                    JsonArray jsonArray = new Gson().fromJson(s,JsonArray.class);
                    if (jsonArray.size()>0) {
                        JsonObject object = jsonArray.get(0).getAsJsonObject();
                        String id = object.get("idmerchant").getAsString();
                        idMerchant.setValue(id);
                    } else {
                        idMerchant.setValue("");
                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
