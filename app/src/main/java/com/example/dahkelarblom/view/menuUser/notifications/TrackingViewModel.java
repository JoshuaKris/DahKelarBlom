package com.example.dahkelarblom.view.menuUser.notifications;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.model.responses.TrackBookingResponse;
import com.example.dahkelarblom.service.InternetService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackingViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<TrackBookingResponse>> trackResponse = new MutableLiveData<>();

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<ArrayList<TrackBookingResponse>> getTrackResponse() {
        return trackResponse;
    }

    public TrackingViewModel(Context context) {
        internetService = new InternetService(context);
    }

    public void fetchBooking(JsonObject jsonObject) {
        apiCall = InternetService.getServicesApi().trackMyBooking(jsonObject);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String s = response.body();
                    Type listType = new TypeToken<ArrayList<TrackBookingResponse>>(){}.getType();
                    List<TrackBookingResponse> merchantListTemp = new GsonBuilder().create().fromJson(s,listType);
                    trackResponse.setValue((ArrayList<TrackBookingResponse>) merchantListTemp);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}