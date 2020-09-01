package com.example.dahkelarblom.view.forgot;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.service.InternetService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;
    private Context context;

    private MutableLiveData<String> sendPassRes = new MutableLiveData<>();

    public ForgotPasswordViewModel(Context context) {
        this.context = context;
        this.internetService = new InternetService(context);
    }

    public LiveData<String> getsendPassRes() {
        return sendPassRes;
    }

    public void sendPassword(JsonObject jsonObject) {
        apiCall = InternetService.getServicesApi().sendPassword(jsonObject);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
                    String lava = jsonObject.get("Status").getAsString();
                    sendPassRes.setValue(lava);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
