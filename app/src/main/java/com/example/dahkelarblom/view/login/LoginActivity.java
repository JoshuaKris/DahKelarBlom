package com.example.dahkelarblom.view.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dahkelarblom.R;
import com.example.dahkelarblom.utils.BaseActivity;
import com.example.dahkelarblom.view.menuAdmin.AdminMenuActivity;
import com.example.dahkelarblom.view.register.RegisterActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends BaseActivity {

    private MaterialCardView cv_button_sign_in;
    private TextView tv_register;
    private TextInputEditText et_username_field;
    private TextInputEditText et_password_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cv_button_sign_in = findViewById(R.id.cv_button_sign_in);
        et_username_field = findViewById(R.id.et_username_field);
        et_password_field = findViewById(R.id.et_password_field);
        tv_register = findViewById(R.id.tv_register);

        cv_button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotEmpty(et_username_field) && isNotEmpty(et_password_field)) {
                    startActivity(new Intent(LoginActivity.this, AdminMenuActivity.class));
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

    }
}
