package com.example.dahkelarblom.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dahkelarblom.R;
import com.example.dahkelarblom.view.login.LoginActivity;
import com.example.dahkelarblom.view.menuUser.UserMenuActivity;
import com.google.android.material.card.MaterialCardView;

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
}
