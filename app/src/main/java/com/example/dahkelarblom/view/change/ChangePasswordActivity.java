package com.example.dahkelarblom.view.change;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.example.dahkelarblom.BaseVMF;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.responses.LoginResponse;
import com.example.dahkelarblom.popup.PopupRegisterFragment;
import com.example.dahkelarblom.utils.BaseActivity;
import com.example.dahkelarblom.utils.HeaderFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import java.util.Objects;

public class ChangePasswordActivity extends BaseActivity {

    private ChangePasswordViewModel viewModel;
    private LoginResponse loginResponse;

    private TextInputEditText et_pass_field;
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
        setContentView(R.layout.activity_change_password);
        loginResponse = getIntent().getParcelableExtra("merchantData");

        BaseVMF factory = new BaseVMF<>(new ChangePasswordViewModel(ChangePasswordActivity.this));
        viewModel = ViewModelProviders.of(this,factory).get(ChangePasswordViewModel.class);
        initLiveData();

        HeaderFragment headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.f_header);
        assert headerFragment != null;
        ImageButton ib_backButton = Objects.requireNonNull(headerFragment.getView()).findViewById(R.id.ib_backButton);

        et_pass_field = findViewById(R.id.et_pass_field);
        Button bt_send_password = findViewById(R.id.bt_send_password);

        headerFragment.headerV2("Ganti Password",false,false);

        ib_backButton.setOnClickListener(v -> onBackPressed());

        bt_send_password.setOnClickListener(v -> {
            if (isNotEmpty(et_pass_field)) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("passwordAdmin",et_pass_field.getText().toString());
                jsonObject.addProperty("idmerchant",loginResponse.getIdmerchant());
                viewModel.changePass(jsonObject);
            } else {
                Toast.makeText(ChangePasswordActivity.this, "password belum diketik", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initLiveData() {
        viewModel.getsendPassRes().observe(this, s -> {
            if (s != null) {
                popupRegisterFragment = PopupRegisterFragment.newInstance(
                        "Password Sudah Dikirim !",
                        "Selamat! Password anda sudah diganti.");
                popupRegisterFragment.show(getSupportFragmentManager(),"change Pass");
                popupRegisterFragment.setListener(popupRegisterListener);
            }
        });
    }
}