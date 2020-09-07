package com.example.dahkelarblom.view.menuAdmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahkelarblom.BaseVMF;
import com.example.dahkelarblom.model.responses.LoginResponse;
import com.example.dahkelarblom.model.responses.ViewAllOrderResponse;
import com.example.dahkelarblom.popup.PopupAdminOrderFragment;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.BookingModel;
import com.example.dahkelarblom.utils.HeaderFragment;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminMenuActivity extends AppCompatActivity implements AdminOrderListAdapter.AdminOrderListOnClickListener, PopupAdminOrderFragment.PopupAdminOrderListener {

    private final static int ADD_ORDER_ACTIVITY = 1001;

    private AdminMenuViewModel viewModel;
    private LoginResponse loginResponse;
    private RecyclerView rv_order_list;
    private RelativeLayout rl_add_order;
    private List<ViewAllOrderResponse> orderList = new ArrayList<>();
    private TextView text_admin_order;
    private PopupAdminOrderFragment popupAdminOrderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        loginResponse = getIntent().getParcelableExtra("merchantData");

        BaseVMF factory = new BaseVMF<>(new AdminMenuViewModel(AdminMenuActivity.this));
        viewModel = ViewModelProviders.of(this,factory).get(AdminMenuViewModel.class);
        initLiveData();
        HeaderFragment headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.f_header);
        ImageButton ib_backButton = Objects.requireNonNull(headerFragment.getView()).findViewById(R.id.ib_backButton);
        ImageButton ib_rightButton = Objects.requireNonNull(headerFragment.getView()).findViewById(R.id.ib_rightButton);
        text_admin_order = findViewById(R.id.text_admin_order);
        rv_order_list = findViewById(R.id.rv_order_list);
        rl_add_order = findViewById(R.id.rl_add_order);

        headerFragment.headerV2("Welcome " + getIntent().getStringExtra("merchantAdmin"),false,true);

        ib_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ib_rightButton.setImageDrawable(getDrawable(R.drawable.ic_baseline_settings_24));
        ib_rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuActivity.this,SettingActivity.class);
                intent.putExtra("merchantData", (Parcelable) getIntent().getParcelableExtra("merchantData"));
                startActivity(intent);
            }
        });
        updateFetchRV(String.valueOf(loginResponse.getIdmerchant()));

        rl_add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMenuActivity.this,AdminAddOrderActivity.class);
                intent.putExtra("merchantId", String.valueOf(loginResponse.getIdmerchant()));
                startActivityForResult(intent,ADD_ORDER_ACTIVITY);
            }
        });
    }

    private void initLiveData() {
//        viewModel.getOrderList().observe(this, new Observer<List<BookingModel>>() {
//            @Override
//            public void onChanged(List<BookingModel> bookingModels) {
//                if (bookingModels != null) {
//                    orderList = bookingModels;
//                    updateRV(orderList);
//                }
//            }
//        });

        viewModel.getmText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty()) {
                    text_admin_order.setText(s);
                }

            }
        });

        viewModel.getAdminOrderList().observe(this, new Observer<List<ViewAllOrderResponse>>() {
            @Override
            public void onChanged(List<ViewAllOrderResponse> viewAllOrderResponses) {
                if (viewAllOrderResponses != null) {
                    orderList = viewAllOrderResponses;
                    updateRV(orderList);
                }
            }
        });

        viewModel.getIsListEmpty().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isListEmpty) {
                if (isListEmpty) {
                    text_admin_order.setVisibility(View.VISIBLE);
                } else {
                    text_admin_order.setVisibility(View.GONE);
                }
            }
        });
    }

    private void updateRV(List<ViewAllOrderResponse> temp) {
        rv_order_list.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rv_order_list.setAdapter(new AdminOrderListAdapter(temp,this));
    }

    private void updateFetchRV(String id) {
        JsonObject refresh = new JsonObject();
        refresh.addProperty("merchantId",id);
        viewModel.fetchAdminOrderList(refresh);
    }

    @Override
    public void onClick(int pos) {
        popupAdminOrderFragment = PopupAdminOrderFragment.newInstance(orderList.get(pos));
        popupAdminOrderFragment.show(getSupportFragmentManager(),"open order");
        popupAdminOrderFragment.setListener(this);
    }

    @Override
    public void okClicked(boolean isClicked, ViewAllOrderResponse item) {
        if (isClicked) {
            for (ViewAllOrderResponse model : orderList) {
                if (item.getCodeBooking().equalsIgnoreCase(model.getCodeBooking())) {
                    model.setStatus(item.getStatus());
                    updateRV(orderList);

                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("statusOrder",item.getStatus());
                    jsonObject.addProperty("idOrder",item.getIdorder());
                    viewModel.changeStatusOrder(jsonObject);

                    updateFetchRV(getIntent().getStringExtra("merchantId"));
                    break;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ORDER_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                updateFetchRV(getIntent().getStringExtra("merchantId"));
            }
        }
    }
}
