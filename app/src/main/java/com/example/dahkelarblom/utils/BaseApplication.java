package com.example.dahkelarblom.utils;

import android.app.Application;

import com.singhajit.sherlock.core.Sherlock;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Sherlock.init(this);
    }
}
