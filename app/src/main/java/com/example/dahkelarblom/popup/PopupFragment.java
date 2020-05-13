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
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.dahkelarblom.R;

import java.util.Objects;

public class PopupFragment extends DialogFragment {

    private static String POPUP_BOOKING_CODE_ = "popup_booking_code";

    private Button bt_ok;
    private TextView tv_booking_code;
    private String popupBookingCode;
    private PopupListener mPopupListener;

    public PopupFragment() {
        // Required empty public constructor
    }

    public static PopupFragment newInstance(String title) {
        PopupFragment fragment = new PopupFragment();
        Bundle args = new Bundle();
        args.putString(POPUP_BOOKING_CODE_,title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            popupBookingCode = getArguments().getString(POPUP_BOOKING_CODE_);
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

        tv_booking_code.setText(String.format("Kode Booking mu adalah %1$s",popupBookingCode));

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
