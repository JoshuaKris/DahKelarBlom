package com.example.dahkelarblom.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dahkelarblom.DialogChooseFragment;
import com.example.dahkelarblom.utils.HeaderFragment;
import com.example.dahkelarblom.popup.PopupFragment;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.DialogItem;
import com.example.dahkelarblom.utils.Loading;

import java.util.ArrayList;
import java.util.Objects;

public class OrderActivity extends AppCompatActivity {

    private HeaderFragment headerFragment;
    private ImageButton ib_backButton;
    private RelativeLayout rl_order_choose;
    private Button bt_order;

    private final String RESET_KEY = "reset";
    private String dialogInput = "";
    private String state;

    private TextView tv_hint_order_choose;

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

    private PopupFragment popupFragment;
    private final PopupFragment.PopupListener popupListener = new PopupFragment.PopupListener() {
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
        setContentView(R.layout.activity_order);

        createItem();

        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.f_header);
        ib_backButton = Objects.requireNonNull(headerFragment.getView()).findViewById(R.id.ib_backButton);
        rl_order_choose = findViewById(R.id.rl_order_choose);
        tv_hint_order_choose = findViewById(R.id.tv_hint_order_choose);
        bt_order = findViewById(R.id.bt_order);

        headerFragment.headerV2("Order",false,false);

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

        bt_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupFragment = PopupFragment.newInstance("WD420");
                popupFragment.show(getSupportFragmentManager(), "popupSuccess");
                popupFragment.setListener(popupListener);
            }
        });
    }

    private void showLoading(Boolean isLoading) {
        WebView webView = findViewById(R.id.htmlloading);
        webView.setBackgroundColor(Color.TRANSPARENT);
        Loading loading = new Loading(webView);
//        if (isLoading) {
//            layoutLoading.setVisibility(View.VISIBLE);
//            ll_data_filter.setVisibility(View.GONE);
//            loading.start();
//        } else {
//            layoutLoading.setVisibility(View.GONE);
//            ll_data_filter.setVisibility(View.VISIBLE);
//            loading.close();
//        }
    }

    private void createItem() {
        DialogItem item;
        for (int i = 1; i < 10; i ++) {
            item = new DialogItem("Paket " + i,false);
            dialogItemList.add(item);
        }

    }
}
