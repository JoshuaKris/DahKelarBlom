package com.example.dahkelarblom.view.register;


import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dahkelarblom.BaseVMF;
import com.example.dahkelarblom.DialogChooseFragment;
import com.example.dahkelarblom.model.DialogItem;
import com.example.dahkelarblom.utils.BaseActivity;
import com.example.dahkelarblom.utils.HeaderFragment;
import com.example.dahkelarblom.popup.PopupRegisterFragment;
import com.example.dahkelarblom.R;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class RegisterActivity extends BaseActivity implements DialogChooseFragment.OnInputListener {

    private RegisterViewModel
            viewModel;
    private HeaderFragment
            headerFragment;
    private DialogChooseFragment
            dialogChooseFragment;
    private ImageButton
            ib_backButton;
    private MaterialCardView
            cv_button_register;
    private EditText
            et_merchant_loc_field,
            et_merchant_name_field,
            et_merchant_id_field,
            et_merchant_password_field,
            et_merchant_email_field,
            et_merchant_phone_field,
            et_merchant_address_field;
    private final ArrayList<DialogItem> dialogItemList = new ArrayList<>();
    private String dialogInput = "";
    private String locID = "";
    private HashMap<Integer, String> locationListTemp = new HashMap<>();

    private PopupRegisterFragment popupRegisterFragment;
    private final PopupRegisterFragment.PopupRegisterListener popupRegisterListener =
            new PopupRegisterFragment.PopupRegisterListener() {
        @Override
        public void okClicked(boolean isClicked) {
            if (isClicked) {
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        BaseVMF factory = new BaseVMF<>(new RegisterViewModel(RegisterActivity.this));
        viewModel = ViewModelProviders.of(this,factory).get(RegisterViewModel.class);
        initLiveData();
        viewModel.fetchLocationData();

        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.f_header);
        ib_backButton = Objects.requireNonNull(headerFragment.getView()).findViewById(R.id.ib_backButton);

        cv_button_register = findViewById(R.id.cv_button_register);
        et_merchant_loc_field = findViewById(R.id.et_merchant_loc_field);
        et_merchant_name_field = findViewById(R.id.et_merchant_name_field);
        et_merchant_id_field = findViewById(R.id.et_merchant_id_field);
        et_merchant_password_field = findViewById(R.id.et_merchant_password_field);
        et_merchant_email_field = findViewById(R.id.et_merchant_email_field);
        et_merchant_phone_field = findViewById(R.id.et_merchant_phone_field);
        et_merchant_address_field = findViewById(R.id.et_merchant_address_field);

        headerFragment.headerV2("Daftar",false,false);

        ib_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        et_merchant_loc_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogChooseFragment = DialogChooseFragment.newInstance(dialogItemList,"Pilih Wilayah");
                dialogChooseFragment.show(getSupportFragmentManager(), "dialogChooseOrder");
                dialogChooseFragment.setListener(RegisterActivity.this);
            }
        });

        cv_button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotEmpty(et_merchant_loc_field) && isNotEmpty(et_merchant_name_field) && isNotEmpty(et_merchant_id_field) && isNotEmpty(et_merchant_password_field) &&
                        isNotEmpty(et_merchant_email_field) && (Patterns.EMAIL_ADDRESS.matcher(et_merchant_email_field.getText().toString()).matches())
                        && isNotEmpty(et_merchant_phone_field) && isNotEmpty(et_merchant_address_field)) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("merchantName",et_merchant_name_field.getText().toString());
                    jsonObject.addProperty("merchantLocation",et_merchant_address_field.getText().toString());
                    jsonObject.addProperty("merchantLocationId",locID);
                    jsonObject.addProperty("usernameAdm",et_merchant_id_field.getText().toString());
                    jsonObject.addProperty("password",et_merchant_password_field.getText().toString());
                    jsonObject.addProperty("merchantEmail",et_merchant_email_field.getText().toString());
                    jsonObject.addProperty("noHpRegist",et_merchant_phone_field.getText().toString());
                    viewModel.registerAccount(jsonObject);
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Data masih ada yang belum diisi atau masih salah, silakan isi dahulu sebelum mendaftar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initLiveData() {
        viewModel.getLocationList().observe(this, new Observer<HashMap<Integer, String>>() {
            @Override
            public void onChanged(HashMap<Integer, String> locationList) {
                if (locationList != null) {
                    locationListTemp = locationList;
                    DialogItem item;
                    for (int i = 0; i < locationList.size(); i++) {
                        item = new DialogItem(locationList.get(i+1),false);
                        dialogItemList.add(item);
                    }
                }
            }
        });

        viewModel.getRegisterData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equalsIgnoreCase("pendaftaran berhasil")) {
                    popupRegisterFragment = PopupRegisterFragment.newInstance();
                    popupRegisterFragment.show(getSupportFragmentManager(),"register success");
                    popupRegisterFragment.setListener(popupRegisterListener);
                } else {
                    Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void sendInput(String input) {
        dialogInput = input;
        String locId = "1";
        et_merchant_loc_field.setText(dialogInput);
        Iterator iterator = locationListTemp.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = (Integer) iterator.next();
            String val = locationListTemp.get(key);
            if (dialogInput.equalsIgnoreCase(val)) {
                locId = String.valueOf(key);
                locID = locId;
                break;
            }
        }

    }
}
