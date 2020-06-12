package com.example.dahkelarblom.view.menuAdmin;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dahkelarblom.DialogChooseFragment;
import com.example.dahkelarblom.utils.BaseActivity;
import com.example.dahkelarblom.utils.HeaderFragment;
import com.example.dahkelarblom.popup.PopupSuccessFragment;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.DialogItem;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Objects;

public class AdminAddOrderActivity extends BaseActivity {

    private HeaderFragment headerFragment;
    private ImageButton ib_backButton;
    private MaterialCardView cv_button_add_order;
    private RelativeLayout rl_order_choose;

    private final String RESET_KEY = "reset";
    private String dialogInput = "";
    private String state;
    private TextView tv_hint_order_choose;
    private EditText
            et_order_name_field,
            et_order_phone_field,
            et_order_price_field,
            et_order_pickup_field,
            et_order_payment_status_field;

    private final ArrayList<DialogItem> dialogItemList = new ArrayList<>();
    private DialogChooseFragment dialogChooseFragment;
    private final DialogChooseFragment.OnInputListener dialogChooseListener = new DialogChooseFragment.OnInputListener()  {
        @Override
        public void sendInput(String input) {
            dialogInput = input;
            if (dialogInput == null) {
                tv_hint_order_choose.setText("jenis order");
                tv_hint_order_choose.setTextColor(getColor(R.color.greyDarkerDefault));
            } else {
                tv_hint_order_choose.setText(dialogInput);
                tv_hint_order_choose.setTextColor(getColor(R.color.textColorLabel));
                state = "";
            }
        }
    };

    private PopupSuccessFragment popupSuccessFragment;
    private final PopupSuccessFragment.PopupListener popupListener = new PopupSuccessFragment.PopupListener() {
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
        setContentView(R.layout.activity_admin_add_order);

        createItem();

        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.f_header);
        ib_backButton = Objects.requireNonNull(headerFragment.getView()).findViewById(R.id.ib_backButton);
        cv_button_add_order = findViewById(R.id.cv_button_add_order);
        rl_order_choose = findViewById(R.id.rl_order_choose);
        tv_hint_order_choose = findViewById(R.id.tv_hint_order_choose);
        et_order_name_field = findViewById(R.id.et_order_name_field);
        et_order_phone_field = findViewById(R.id.et_order_phone_field);
        et_order_price_field = findViewById(R.id.et_order_price_field);
        et_order_pickup_field = findViewById(R.id.et_order_pickup_field);
        et_order_payment_status_field = findViewById(R.id.et_order_payment_status_field);

        headerFragment.headerV2("Order Baru",false,false);

        ib_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rl_order_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogChooseFragment = DialogChooseFragment.newInstance(dialogItemList,"Jenis Order");

                dialogChooseFragment.show(getSupportFragmentManager(), "dialogChooseOrder");
                dialogChooseFragment.setListener(dialogChooseListener);
            }
        });

        cv_button_add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEquals(tv_hint_order_choose,DEFAULT_ORDER_TEXT_KEY)
                        && isNotEmpty(et_order_name_field) &&
                        isNotEmpty(et_order_phone_field) &&
                        isNotEmpty(et_order_price_field) &&
                        isNotEmpty(et_order_pickup_field) &&
                        isNotEmpty(et_order_payment_status_field)) {
                    popupSuccessFragment = PopupSuccessFragment.newInstance("WD420");
                    popupSuccessFragment.show(getSupportFragmentManager(), "popupSuccess");
                    popupSuccessFragment.setListener(popupListener);
                }
                else {
                    Toast.makeText(AdminAddOrderActivity.this, "Data masih ada yang kosong, silakan isi dahulu yang masih kosong.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void createItem() {
        DialogItem item;
        for (int i = 1; i < 10; i ++) {
            item = new DialogItem("Paket " + i,false);
            dialogItemList.add(item);
        }

    }
}
