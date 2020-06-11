package com.example.dahkelarblom.view.menuAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahkelarblom.popup.PopupAdminOrderFragment;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.BookingModel;

import java.util.ArrayList;
import java.util.List;

public class AdminMenuActivity extends AppCompatActivity implements AdminOrderListAdapter.AdminOrderListOnClickListener {

    private AdminMenuViewModel viewModel;
    private RecyclerView rv_order_list;
    private RelativeLayout rl_add_order;
    private List<BookingModel> orderList = new ArrayList<>();
    private TextView text_admin_order;
    private PopupAdminOrderFragment popupAdminOrderFragment;
    private final PopupAdminOrderFragment.PopupAdminOrderListener popupAdminOrderListener =
            new PopupAdminOrderFragment.PopupAdminOrderListener() {
                @Override
                public void okClicked(boolean isClicked, BookingModel item) {
                    if (isClicked) {
                        for (BookingModel bookingModel : orderList) {
                            if (item.getBookingCode().equalsIgnoreCase(bookingModel.getBookingCode())) {
                                bookingModel.setBookingStatus(item.getBookingStatus());
                                updateRV(orderList);
                            }
                        }
                    }
                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        viewModel = ViewModelProviders.of(this).get(AdminMenuViewModel.class);
        viewModel.fetchOrderData(true);
        text_admin_order = findViewById(R.id.text_admin_order);
        rv_order_list = findViewById(R.id.rv_order_list);
        rl_add_order = findViewById(R.id.rl_add_order);

        initLiveData();

        rl_add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMenuActivity.this,AdminAddOrderActivity.class));
            }
        });
    }

    private void initLiveData() {
        viewModel.getOrderList().observe(this, new Observer<List<BookingModel>>() {
            @Override
            public void onChanged(List<BookingModel> bookingModels) {
                if (bookingModels != null) {
                    orderList = bookingModels;
                    updateRV(orderList);
                }
            }
        });

        viewModel.getmText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty()) {
                    text_admin_order.setText(s);
                }

            }
        });
    }

    private void updateRV(List<BookingModel> temp) {
        rv_order_list.setLayoutManager(new LinearLayoutManager(this));
        rv_order_list.setAdapter(new AdminOrderListAdapter(temp,this));
    }

    @Override
    public void onClick(int pos) {
        Toast.makeText(this, "this is order" + orderList.get(pos).getBookingCode(), Toast.LENGTH_SHORT).show();
        popupAdminOrderFragment = PopupAdminOrderFragment.newInstance(orderList.get(pos));
        popupAdminOrderFragment.show(getSupportFragmentManager(),"open order");
        popupAdminOrderFragment.setListener(popupAdminOrderListener);
    }
}
