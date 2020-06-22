package com.example.dahkelarblom.view.register;


import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dahkelarblom.utils.BaseActivity;
import com.example.dahkelarblom.utils.HeaderFragment;
import com.example.dahkelarblom.popup.PopupRegisterFragment;
import com.example.dahkelarblom.R;
import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

public class RegisterActivity extends BaseActivity {

    private HeaderFragment
            headerFragment;
    private ImageButton
            ib_backButton;
    private MaterialCardView
            cv_button_register;
    private EditText
            et_merchant_name_field,
            et_merchant_id_field,
            et_merchant_password_field,
            et_merchant_email_field,
            et_merchant_phone_field,
            et_merchant_address_field;

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

        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.f_header);
        ib_backButton = Objects.requireNonNull(headerFragment.getView()).findViewById(R.id.ib_backButton);

        cv_button_register = findViewById(R.id.cv_button_register);
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

        cv_button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotEmpty(et_merchant_name_field) && isNotEmpty(et_merchant_id_field) && isNotEmpty(et_merchant_password_field) &&
                        isNotEmpty(et_merchant_email_field) && (Patterns.EMAIL_ADDRESS.matcher(et_merchant_email_field.getText().toString()).matches())
                        && isNotEmpty(et_merchant_phone_field) && isNotEmpty(et_merchant_address_field)) {
                    popupRegisterFragment = PopupRegisterFragment.newInstance();
                    popupRegisterFragment.show(getSupportFragmentManager(),"register success");
                    popupRegisterFragment.setListener(popupRegisterListener);
                      }
                else {
                    Toast.makeText(RegisterActivity.this, "Data masih ada yang belum diisi atau masih salah, silakan isi dahulu sebelum mendaftar", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
