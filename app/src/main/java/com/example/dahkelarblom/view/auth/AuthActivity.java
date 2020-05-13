package com.example.dahkelarblom.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dahkelarblom.utils.HeaderFragment;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.view.adminMenu.AdminMenuActivity;
import com.example.dahkelarblom.view.login.LoginActivity;
import com.example.dahkelarblom.view.userMenu.MainActivity;
import com.google.android.material.card.MaterialCardView;

public class AuthActivity extends AppCompatActivity {

    private HeaderFragment headerFragment;
    private ImageButton ib_backButton;

    private MaterialCardView cv_auth_user;
    private MaterialCardView cv_auth_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        cv_auth_user = findViewById(R.id.cv_auth_user);
        cv_auth_admin = findViewById(R.id.cv_auth_admin);

        cv_auth_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AuthActivity.this, MainActivity.class));
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
