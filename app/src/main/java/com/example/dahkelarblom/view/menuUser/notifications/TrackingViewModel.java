package com.example.dahkelarblom.view.menuUser.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrackingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TrackingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tracking fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}