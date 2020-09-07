package com.example.dahkelarblom.view.menuAdmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dahkelarblom.BaseVMF;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.responses.LoginResponse;
import com.example.dahkelarblom.popup.PopupConfirmationFragment;
import com.example.dahkelarblom.utils.HeaderFragment;
import com.example.dahkelarblom.view.change.ChangePasswordActivity;
import com.google.gson.JsonObject;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity {

    private SettingViewModel viewModel;
    private LoginResponse loginResponse;

    private RelativeLayout rl_gantiPassword;
    private SwitchCompat sw_bukaToko;
    private TextView tv_bukaToko;
    private PopupConfirmationFragment popupConfirmationFragment;

    private PopupConfirmationFragment.PopupConfirmationListener popupConfirmationListener = new PopupConfirmationFragment.PopupConfirmationListener() {
        @Override
        public void cancelClicked(boolean isCancelClicked) {
            if (isCancelClicked) {
                if (!sw_bukaToko.isChecked()) {
                    sw_bukaToko.setChecked(true);
                } else {
                    sw_bukaToko.setChecked(false);
                }
            }
        }

        @Override
        public void okClicked(boolean isOkClicked) {
            if (isOkClicked) {
                JsonObject object = new JsonObject();
                object.addProperty("testoperationalMerchant",String.valueOf(sw_bukaToko.isChecked()));
                object.addProperty("testidmerchant",String.valueOf(loginResponse.getIdmerchant()));
                viewModel.updateOperationalTime(object);

                if (sw_bukaToko.isChecked()) {
                    sw_bukaToko.setChecked(true);
                } else {
                    sw_bukaToko.setChecked(false);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        loginResponse = getIntent().getParcelableExtra("merchantData");
        BaseVMF factory = new BaseVMF<>(new SettingViewModel(SettingActivity.this));
        viewModel = ViewModelProviders.of(this,factory).get(SettingViewModel.class);
        initLiveData();

        HeaderFragment headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.f_header);
        ImageButton ib_backButton = Objects.requireNonNull(headerFragment.getView()).findViewById(R.id.ib_backButton);
        rl_gantiPassword = findViewById(R.id.rl_gantiPassword);
        sw_bukaToko = findViewById(R.id.sw_bukaToko);
        tv_bukaToko = findViewById(R.id.tv_bukaToko);

        sw_bukaToko.setChecked(Boolean.parseBoolean(loginResponse.getOperationalMerchant()));

        sw_bukaToko.setOnClickListener(v -> {
            if (sw_bukaToko.isChecked()) {
                popupConfirmationFragment = PopupConfirmationFragment.newInstance(
                        "Buka Toko Sekarang?",
                        "Apakah anda yakin akan membuka toko? \n Anda akan mendapatkan order.");
                popupConfirmationFragment.show(getSupportFragmentManager(),"confirm open");
                popupConfirmationFragment.setListener(popupConfirmationListener);
            } else {

                popupConfirmationFragment = PopupConfirmationFragment.newInstance(
                        "Tutup Toko Sekarang?",
                        "Apakah anda yakin akan menutup toko? \n Anda tidak akan mendapatkan order.");
                popupConfirmationFragment.show(getSupportFragmentManager(),"confirm close");
                popupConfirmationFragment.setListener(popupConfirmationListener);
            }
        });

        tv_bukaToko.setText("Buka Toko");

        headerFragment.headerV2("Setting",false,false);

        ib_backButton.setOnClickListener(v -> onBackPressed());

        rl_gantiPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ChangePasswordActivity.class);
                intent.putExtra("merchantData", (Parcelable) getIntent().getParcelableExtra("merchantData"));
                startActivity(intent);
            }
        });
    }

    private void initLiveData() {
        viewModel.getisStoreOpen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean != null) {
                    sw_bukaToko.setChecked(aBoolean);
                }
            }
        });
    }
}