package com.example.dahkelarblom.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dahkelarblom.R;
import com.example.dahkelarblom.view.login.LoginActivity;
import com.example.dahkelarblom.view.menuUser.UserMenuActivity;
import com.google.android.material.card.MaterialCardView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created By Joshua Kris
 * 6 - 27 - 2020
 */
public class AuthActivity extends AppCompatActivity {


    private MaterialCardView cv_auth_user;
    private MaterialCardView cv_auth_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        cv_auth_user = findViewById(R.id.cv_auth_user);
        cv_auth_admin = findViewById(R.id.cv_auth_admin);

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String dateformatted = dateFormat.format(date);
        Log.d("GetCurrentDate", "dateformatted: " + dateformatted);

        Date date2 = addMinutesToDate(date,20);
        String dateAddedFormatted = dateFormat.format(date2);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date2);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        Log.d("GetCurrentDate", "dateAddedFormatted: " + dateAddedFormatted);
        Log.d("GetCurrentDate", "hour: " + hour);
        Log.d("GetCurrentDate", "minute: " + minute);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
            Date datetes1 = sdf.parse("08:00");
            Date datetes2 = sdf.parse("08:30");
            if(datetes1.before(datetes2)){
                Log.d("GetCurrentDate", "Date1 is before Date2");
            }

            if(datetes1.after(datetes2)){
                Log.d("GetCurrentDate", "Date1 is after Date2");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cv_auth_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AuthActivity.this, UserMenuActivity.class));
            }
        });

        cv_auth_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AuthActivity.this, LoginActivity.class));
            }
        });

    }
    public static Date addMinutesToDate(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
}
