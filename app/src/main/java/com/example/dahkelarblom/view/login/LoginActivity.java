package com.example.dahkelarblom.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dahkelarblom.BaseVMF;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.utils.BaseActivity;
import com.example.dahkelarblom.view.forgot.ForgotPasswordActivity;
import com.example.dahkelarblom.view.menuAdmin.AdminMenuActivity;
import com.example.dahkelarblom.view.register.RegisterActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

public class LoginActivity extends BaseActivity {

    private MaterialCardView cv_button_sign_in;
    private TextView tv_forgot_password,tv_register;
    private TextInputEditText et_username_field;
    private TextInputEditText et_password_field;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BaseVMF factory = new BaseVMF(new LoginViewModel(LoginActivity.this));
        loginViewModel = ViewModelProviders.of(this,factory).get(LoginViewModel.class);
        initLiveData();

        cv_button_sign_in = findViewById(R.id.cv_button_sign_in);
        et_username_field = findViewById(R.id.et_username_field);
        et_password_field = findViewById(R.id.et_password_field);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        tv_register = findViewById(R.id.tv_register);

        cv_button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotEmpty(et_username_field) && isNotEmpty(et_password_field)) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("testUsernameAdmin",et_username_field.getText().toString());
                    jsonObject.addProperty("testPassword",et_password_field.getText().toString());
                    loginViewModel.login(jsonObject);
                } else {
                    Toast.makeText(LoginActivity.this, "Username dan atau Password masih kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });

    }

    private void initLiveData() {
        loginViewModel.getIdMerchant().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty()) {
                    Intent intent = new Intent(LoginActivity.this, AdminMenuActivity.class);
                    intent.putExtra("merchantId",s);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "maaf akun ini tidak ada, silakan register terlebih dahulu.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
