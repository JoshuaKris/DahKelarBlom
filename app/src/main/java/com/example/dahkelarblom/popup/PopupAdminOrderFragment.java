package com.example.dahkelarblom.popup;

import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.BookingModel;

import java.util.Objects;

public class PopupAdminOrderFragment extends DialogFragment {

    private PopupAdminOrderListener mPopupListener;
    private BookingModel mItem;

    private ImageView iv_close;
    private TextView
            tv_bookingCode,
            tv_bookingPickup,
            tv_bookingPrice,
            tv_userName,
            tv_userPhone,
            tv_payment_status,
            tv_order_status;
    private Button bt_file;


    public PopupAdminOrderFragment() {
        // Required empty public constructor
    }

    public static PopupAdminOrderFragment newInstance(BookingModel item) {
        PopupAdminOrderFragment fragment = new PopupAdminOrderFragment();
        Bundle args = new Bundle();
        args.putParcelable("item",item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Objects.requireNonNull(getDialog().getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCancelable(false);
        View fragmentView = inflater.inflate(R.layout.fragment_popup_admin_order, container, false);

        iv_close = fragmentView.findViewById(R.id.iv_close);
        tv_bookingCode = fragmentView.findViewById(R.id.tv_bookingCode);
        tv_bookingPickup = fragmentView.findViewById(R.id.tv_bookingPickup);
        tv_bookingPrice = fragmentView.findViewById(R.id.tv_bookingPrice);
        tv_userName = fragmentView.findViewById(R.id.tv_userName);
        tv_userPhone = fragmentView.findViewById(R.id.tv_userPhone);
        tv_payment_status = fragmentView.findViewById(R.id.tv_payment_status);
        tv_order_status = fragmentView.findViewById(R.id.tv_order_status);
        bt_file = fragmentView.findViewById(R.id.bt_file);

        if (getArguments() != null) {
            mItem = getArguments().getParcelable("item");
            if (mItem != null) {
                tv_bookingCode.setText(mItem.getBookingCode());
                tv_bookingPickup.setText(mItem.getBookingPickupTime());
                tv_bookingPrice.setText(mItem.getBookingPrice());
                tv_userName.setText("User Model not Found");
                tv_userPhone.setText("User Model not Found");
                tv_payment_status.setText("Lunas");
                tv_order_status.setText(mItem.getBookingStatus());
            }
        }

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getDialog()).dismiss();
                if (mPopupListener != null) {
                    mPopupListener.okClicked(true);
                }
            }
        });

        bt_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "what file", Toast.LENGTH_SHORT).show();
            }
        });

        return fragmentView;
    }

    public void setListener(PopupAdminOrderListener mPopupListener) {
        this.mPopupListener = mPopupListener;
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = Objects.requireNonNull(window).getWindowManager().getDefaultDisplay();
        display.getSize(size);
        int height = size.y;
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawableResource(R.drawable.dialog_background);
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog().getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public interface PopupAdminOrderListener {
        void okClicked(boolean isClicked);
    }
}
