package com.example.dahkelarblom.view.register;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.service.InternetService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;
    private Context context;

    private MutableLiveData<String> registerData = new MutableLiveData<>();
    private MutableLiveData<HashMap<Integer,String>> locationList = new MutableLiveData<>();

    public RegisterViewModel(Context context) {
        this.context = context;
        this.internetService = new InternetService(context);
    }

    public LiveData<String> getRegisterData() {
        return registerData;
    }

    public LiveData<HashMap<Integer,String>> getLocationList() {
        return locationList;
    }

    public void fetchLocationData() {
        HashMap<Integer,String> temp = new HashMap<>();
        temp.put(1,"Jakarta Barat");
        temp.put(2,"Jakarta Selatan");
        temp.put(3,"Bekasi");
        temp.put(4,"Jakarta Utara");
        temp.put(5,"Jakarta Timur");
        locationList.setValue(temp);
    }

    public void registerAccount(JsonObject jsonObject) {
        Log.d("getRg", "onResponse: " + jsonObject.toString());
        apiCall = InternetService.getServicesApi().registerAdmin(jsonObject);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.d("getRegiss", "onResponse: " + response.body());
                    JsonObject object = new Gson().fromJson(response.body(),JsonObject.class);
                    String s = object.get("Status").getAsString();
                    Log.d("getRegissss", "onResponse: " + s);
                    registerData.setValue(s);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(context, "Username sudah dipakai", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
