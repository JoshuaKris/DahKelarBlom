package com.example.dahkelarblom;

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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.dahkelarblom.model.BookingModel;

import java.util.Objects;

public class TrackingDialogFragment extends DialogFragment {

    private static String DIALOG_BOOKING_ = "dialog_booking";

    private Button
            bt_ok;
    private TextView
            tv_popup_title,
            tv_merchant_name,
            tv_merchant_address,
            tv_merchant_phonenum,
            tv_booking_status,
            tv_booking_price,
            tv_booking_pickup;
    private PopupListener
            mPopupListener;
    private BookingModel
            mBooking;

    public TrackingDialogFragment() {
        // Required empty public constructor
    }

    public static TrackingDialogFragment newInstance(BookingModel mBooking) {
        TrackingDialogFragment fragment = new TrackingDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(DIALOG_BOOKING_, mBooking);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBooking = getArguments().getParcelable(DIALOG_BOOKING_);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Objects.requireNonNull(getDialog().getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCancelable(false);
        View fragmentView = inflater.inflate(R.layout.fragment_tracking_dialog, container, false);

        bt_ok = fragmentView.findViewById(R.id.bt_popup_ok);
        tv_popup_title = fragmentView.findViewById(R.id.tv_popup_title);
        tv_merchant_name = fragmentView.findViewById(R.id.tv_merchant_name);
        tv_merchant_address = fragmentView.findViewById(R.id.tv_merchant_address);
        tv_merchant_phonenum = fragmentView.findViewById(R.id.tv_merchant_phonenum);
        tv_booking_status = fragmentView.findViewById(R.id.tv_booking_status);
        tv_booking_price = fragmentView.findViewById(R.id.tv_booking_price);
        tv_booking_pickup = fragmentView.findViewById(R.id.tv_booking_pickup);

        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_popup_title.setText(String.format("Status Order %1$s",mBooking.getBookingCode()));
        tv_merchant_name.setText(mBooking.getMerchantToBook().getMerchantName());
        tv_merchant_address.setText(mBooking.getMerchantToBook().getMerchantAddress());
        tv_merchant_phonenum.setText(mBooking.getMerchantToBook().getMerchantPhoneNum());
        tv_booking_status.setText(mBooking.getBookingStatus());
        tv_booking_price.setText(mBooking.getBookingPrice());
        tv_booking_pickup.setText(mBooking.getBookingPickupTime());

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
                if (mPopupListener != null) {
                    mPopupListener.okClicked(true);
                }
            }
        });

    }

    public void setListener(PopupListener mPopupListener) {
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

    public interface PopupListener {
        void okClicked(boolean isClicked);
    }
}
