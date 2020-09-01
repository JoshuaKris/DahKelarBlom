package com.example.dahkelarblom.view.menuUser.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.model.Merchant;
import com.example.dahkelarblom.model.responses.ViewAllMerchants;
import com.example.dahkelarblom.service.InternetService;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;

    private MutableLiveData<String> mText = new MutableLiveData<>();
    private MutableLiveData<List<Merchant>> merchantList = new MutableLiveData<>();
    private MutableLiveData<HashMap<Integer,String>> locationList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ViewAllMerchants>> testMerchantList = new MutableLiveData<>();

    public HomeViewModel(Context context) {
        //still looking
        internetService = new InternetService(context);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Merchant>> getMerchantList() {
        return merchantList;
    }

    public LiveData<HashMap<Integer,String>> getLocationList() {
        return locationList;
    }

    public LiveData<ArrayList<ViewAllMerchants>> getTestMerchantList() {
        return testMerchantList;
    }

    public void fetchText(String text) {
        mText.setValue(text);
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

    public void fetchMerchantList() {
        apiCall = InternetService.getServicesApi().getAllMerchant();
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                if (response.isSuccessful()) {
                    String s = response.body();
                    Type listType = new TypeToken<ArrayList<ViewAllMerchants>>(){}.getType();
                    List<ViewAllMerchants> merchantListTemp = new GsonBuilder().create().fromJson(s,listType);
                    testMerchantList.setValue((ArrayList<ViewAllMerchants>) merchantListTemp);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}