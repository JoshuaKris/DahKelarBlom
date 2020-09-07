package com.example.dahkelarblom.view.login;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.model.responses.LoginResponse;
import com.example.dahkelarblom.model.responses.TrackBookingResponse;
import com.example.dahkelarblom.model.responses.ViewAllMerchants;
import com.example.dahkelarblom.service.InternetService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;
    private Context context;

    private MutableLiveData<Boolean> statusSuccess = new MutableLiveData<>();
    private MutableLiveData<ArrayList<LoginResponse>> idMerchant = new MutableLiveData<>();

    public LiveData<Boolean> getStatusSuccess() {
        return statusSuccess;
    }

    public LiveData<ArrayList<LoginResponse>> getIdMerchant() {
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
                    Type listType = new TypeToken<ArrayList<LoginResponse>>(){}.getType();
                    List<LoginResponse> merchantListTemp = new GsonBuilder().create().fromJson(s,listType);
                    idMerchant.setValue((ArrayList<LoginResponse>) merchantListTemp);
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
