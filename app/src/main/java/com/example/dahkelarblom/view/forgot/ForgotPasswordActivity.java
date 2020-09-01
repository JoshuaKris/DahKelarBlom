package com.example.dahkelarblom.view.forgot;


import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.example.dahkelarblom.BaseVMF;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.popup.PopupRegisterFragment;
import com.example.dahkelarblom.utils.BaseActivity;
import com.example.dahkelarblom.utils.HeaderFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import java.util.Objects;

public class ForgotPasswordActivity extends BaseActivity {

    private ForgotPasswordViewModel viewModel;

    private TextInputEditText et_email_field;
    private PopupRegisterFragment popupRegisterFragment;
    private final PopupRegisterFragment.PopupRegisterListener
            popupRegisterListener =
            isClicked -> {
                if (isClicked) {
                    finish();
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        BaseVMF factory = new BaseVMF<>(new ForgotPasswordViewModel(ForgotPasswordActivity.this));
        viewModel = ViewModelProviders.of(this,factory).get(ForgotPasswordViewModel.class);
        initLiveData();

        HeaderFragment headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.f_header);
        assert headerFragment != null;
        ImageButton ib_backButton = Objects.requireNonNull(headerFragment.getView()).findViewById(R.id.ib_backButton);

        et_email_field = findViewById(R.id.et_email_field);
        Button bt_send_password = findViewById(R.id.bt_send_password);

        headerFragment.headerV2("Lupa Password",false,false);

        ib_backButton.setOnClickListener(v -> onBackPressed());

        bt_send_password.setOnClickListener(v -> {
            if (isNotEmpty(et_email_field) && (Patterns.EMAIL_ADDRESS.matcher(Objects.requireNonNull(et_email_field.getText()).toString()).matches())) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email",et_email_field.getText().toString());
                viewModel.sendPassword(jsonObject);
            } else {
                Toast.makeText(ForgotPasswordActivity.this, "format email masih salah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initLiveData() {
        viewModel.getsendPassRes().observe(this, s -> {
            if (s.equalsIgnoreCase("berhasil")) {
                popupRegisterFragment = PopupRegisterFragment.newInstance(
                        "Password Sudah Dikirim !",
                        "Selamat! Password Sudah dikirimkan ke email anda. Silahkan masuk kembali.");
                popupRegisterFragment.show(getSupportFragmentManager(),"send Pass");
                popupRegisterFragment.setListener(popupRegisterListener);
            } else {
                Toast.makeText(ForgotPasswordActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}