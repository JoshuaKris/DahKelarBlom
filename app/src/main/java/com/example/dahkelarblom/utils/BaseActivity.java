package com.example.dahkelarblom.utils;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String DEFAULT_ORDER_TEXT_KEY = "Jenis Order";

    /* for validation*/
    public boolean isNotEmpty(TextView tv) {
        return tv.getText().toString().trim().length() != 0;
    }

    public boolean isEquals(TextView tv1, TextView tv2) {
        return tv1.getText().toString().equalsIgnoreCase(tv2.getText().toString());
    }
    public boolean isEquals(TextView tv, String s) {
        return tv.getText().toString().equalsIgnoreCase(s);
    }

    public static Date addMinutesToDate(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public BaseActivity() {
    }
}
