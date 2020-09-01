package com.example.dahkelarblom.view.menuAdmin;


import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dahkelarblom.BaseVMF;
import com.example.dahkelarblom.DialogChooseFragment;
import com.example.dahkelarblom.utils.BaseActivity;
import com.example.dahkelarblom.utils.Constants;
import com.example.dahkelarblom.utils.HeaderFragment;
import com.example.dahkelarblom.popup.PopupSuccessFragment;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.DialogItem;
import com.example.dahkelarblom.utils.RangeTimePickerDialog;
import com.example.dahkelarblom.view.menuUser.OrderActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AdminAddOrderActivity extends BaseActivity implements DialogChooseFragment.OnInputListener, PopupSuccessFragment.PopupListener{

    private AdminAddOrderViewModel viewModel;
    private HeaderFragment headerFragment;
    private ImageButton ib_backButton;
    private MaterialCardView cv_button_add_order;
    private RelativeLayout rl_order_choose;

    private String dialogInput = "";
    private int tHour,tMinute;
    private TextView tv_hint_order_choose;
    private EditText
            et_order_name_field,
            et_order_phone_field,
            et_order_info_field,
            et_order_pickup_field;

    private final ArrayList<DialogItem> dialogItemList = new ArrayList<>();
    private DialogChooseFragment dialogChooseFragment;
    private PopupSuccessFragment popupSuccessFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_order);
        BaseVMF factory = new BaseVMF<>(new AdminAddOrderViewModel(AdminAddOrderActivity.this));
        viewModel = ViewModelProviders.of(this,factory).get(AdminAddOrderViewModel.class);
        initLiveData();
        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.f_header);
        ib_backButton = Objects.requireNonNull(headerFragment.getView()).findViewById(R.id.ib_backButton);
        cv_button_add_order = findViewById(R.id.cv_button_add_order);
        rl_order_choose = findViewById(R.id.rl_order_choose);
        tv_hint_order_choose = findViewById(R.id.tv_hint_order_choose);
        et_order_name_field = findViewById(R.id.et_order_name_field);
        et_order_phone_field = findViewById(R.id.et_order_phone_field);
        et_order_info_field = findViewById(R.id.et_order_info_field);
        et_order_pickup_field = findViewById(R.id.et_order_pickup_field);

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
                dialogChooseFragment = DialogChooseFragment.newInstance(Constants.order,"Jenis Order");
                dialogChooseFragment.show(getSupportFragmentManager(), "dialogChooseOrder");
                dialogChooseFragment.setListener(AdminAddOrderActivity.this);
            }
        });

        et_order_pickup_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RangeTimePickerDialog dialog = new RangeTimePickerDialog(
                        AdminAddOrderActivity.this,
                        R.style.TimePicker,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                tHour = hourOfDay;
                                tMinute = minute;
                                String time = tHour+":"+tMinute;
                                SimpleDateFormat f24hours = new SimpleDateFormat("HH:mm", Locale.getDefault());
                                try {
                                    Date date = f24hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa",Locale.getDefault());
                                    et_order_pickup_field.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,true);
                dialog.setMin(8,0);
                dialog.setMax(20,1);
//                dialog.updateTime(tHour,tMinute);
                dialog.show();
            }
        });

        cv_button_add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEquals(tv_hint_order_choose,DEFAULT_ORDER_TEXT_KEY)
                        && isNotEmpty(et_order_name_field) &&
                        isNotEmpty(et_order_phone_field) &&
                        isNotEmpty(et_order_info_field) &&
                        isNotEmpty(et_order_pickup_field)) {
                    JsonObject object = new JsonObject();
                    object.addProperty("jnsOrder",tv_hint_order_choose.getText().toString());
                    object.addProperty("keteranganOrderAdm",et_order_info_field.getText().toString());
                    object.addProperty("username",et_order_name_field.getText().toString());
                    object.addProperty("noHp",et_order_phone_field.getText().toString());
                    object.addProperty("pengambilanOrder",et_order_pickup_field.getText().toString());
                    object.addProperty("merchantId",getIntent().getStringExtra("merchantId"));
                    object.addProperty("status","file belum diterima");
                    viewModel.postAdminOrder(object);
                }
                else {
                    Toast.makeText(AdminAddOrderActivity.this, "Data masih ada yang kosong, silakan isi dahulu yang masih kosong.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initLiveData() {
        viewModel.getAdminAddOrder().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    popupSuccessFragment = PopupSuccessFragment.newInstance(s.toUpperCase());
                    popupSuccessFragment.show(getSupportFragmentManager(), "popupSuccess");
                    popupSuccessFragment.setListener(AdminAddOrderActivity.this);
                }
            }
        });
    }

    @Override
    public void sendInput(String input) {
        dialogInput = input;
        if (dialogInput == null) {
            tv_hint_order_choose.setText("jenis order");
            tv_hint_order_choose.setTextColor(getColor(R.color.greyDarkerDefault));
        } else {
            tv_hint_order_choose.setText(dialogInput);
            tv_hint_order_choose.setTextColor(getColor(R.color.textColorLabel));
        }
    }

    @Override
    public void okClicked(boolean isClicked) {
        if (isClicked) {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
    }
}
