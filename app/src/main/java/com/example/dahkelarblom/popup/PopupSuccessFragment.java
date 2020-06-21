package com.example.dahkelarblom.popup;

import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.dahkelarblom.R;

import java.util.Objects;

public class PopupSuccessFragment extends DialogFragment {

    private static String POPUP_BOOKING_CODE_ = "popup_booking_code";
    private static String POPUP_FROM_ADMIN_ = "popup_from_admin";

    private Button bt_ok;
    private TextView tv_booking_code, tv_email_text, tv_merchant_email;
    private String popupBookingCode, merchantEmail;
    private PopupListener mPopupListener;

    public PopupSuccessFragment() {
        // Required empty public constructor
    }

    public static PopupSuccessFragment newInstance(String title) {
        PopupSuccessFragment fragment = new PopupSuccessFragment();
        Bundle args = new Bundle();
        args.putString(POPUP_BOOKING_CODE_,title);
        fragment.setArguments(args);
        return fragment;
    }
    public static PopupSuccessFragment newInstance(String title, String merchantEmail) {
        PopupSuccessFragment fragment = new PopupSuccessFragment();
        Bundle args = new Bundle();
        args.putString(POPUP_BOOKING_CODE_,title);
        args.putString(POPUP_FROM_ADMIN_,merchantEmail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            popupBookingCode = getArguments().getString(POPUP_BOOKING_CODE_);
            merchantEmail = getArguments().getString(POPUP_FROM_ADMIN_);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Objects.requireNonNull(getDialog().getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCancelable(false);
        View fragmentView = inflater.inflate(R.layout.fragment_popup, container, false);

        bt_ok = fragmentView.findViewById(R.id.bt_popup_ok);
        tv_booking_code = fragmentView.findViewById(R.id.tv_booking_code);
        tv_email_text = fragmentView.findViewById(R.id.tv_email_text);
        tv_merchant_email = fragmentView.findViewById(R.id.tv_merchant_email);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        builder.append("Kode Booking mu adalah ").append(popupBookingCode,boldSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_booking_code.setText(builder);
        if (merchantEmail != null) {
            tv_merchant_email.setText(merchantEmail);
        } else {
            tv_email_text.setVisibility(View.GONE);
            tv_merchant_email.setVisibility(View.GONE);
        }

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
                if (mPopupListener != null) {
                    mPopupListener.okClicked(true);
                }
            }
        });

        return fragmentView;
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
