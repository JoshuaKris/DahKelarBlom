package com.example.dahkelarblom.view.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dahkelarblom.R;
import com.example.dahkelarblom.view.menuAdmin.AdminMenuActivity;
import com.example.dahkelarblom.view.register.RegisterActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private MaterialCardView cv_button_sign_in;
    private TextView tv_register;
    private TextInputEditText et_username_field;
    private TextInputEditText et_password_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cv_button_sign_in = findViewById(R.id.cv_button_sign_in);
        tv_register = findViewById(R.id.tv_register);

        cv_button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, AdminMenuActivity.class));
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }
}
