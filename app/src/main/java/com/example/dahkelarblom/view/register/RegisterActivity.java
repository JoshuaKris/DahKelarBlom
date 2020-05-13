package com.example.dahkelarblom.view.register;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dahkelarblom.utils.HeaderFragment;
import com.example.dahkelarblom.popup.PopupRegisterFragment;
import com.example.dahkelarblom.R;
import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private HeaderFragment headerFragment;
    private ImageButton ib_backButton;
    private MaterialCardView cv_button_sign_in;
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

        cv_button_sign_in = findViewById(R.id.cv_button_sign_in);
        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.f_header);
        ib_backButton = Objects.requireNonNull(headerFragment.getView()).findViewById(R.id.ib_backButton);

        headerFragment.headerV2("Register",false,false);

        ib_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        cv_button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupRegisterFragment = PopupRegisterFragment.newInstance();
                popupRegisterFragment.show(getSupportFragmentManager(),"register success");
                popupRegisterFragment.setListener(popupRegisterListener);
            }
        });
    }
}
