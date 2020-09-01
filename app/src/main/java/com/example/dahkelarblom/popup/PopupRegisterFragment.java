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

public class PopupRegisterFragment extends DialogFragment {

    private Button bt_ok;
    private String popupBookingCode;
    private PopupRegisterListener mPopupListener;

    private TextView tv_popup_title, tv_popup_message;

    public PopupRegisterFragment() {
        // Required empty public constructor
    }

    public static PopupRegisterFragment newInstance() {
        PopupRegisterFragment fragment = new PopupRegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static PopupRegisterFragment newInstance(String title, String subtitle) {
        PopupRegisterFragment fragment = new PopupRegisterFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putString("subtitle",subtitle);
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
        View fragmentView = inflater.inflate(R.layout.fragment_popup_register, container, false);

        bt_ok = fragmentView.findViewById(R.id.bt_popup_ok);
        tv_popup_title = fragmentView.findViewById(R.id.tv_popup_title);
        tv_popup_message = fragmentView.findViewById(R.id.tv_popup_message);

        bt_ok.setOnClickListener(view -> {
            getDialog().dismiss();
            if (mPopupListener != null) {
                mPopupListener.okClicked(true);
            }
        });

        if (getArguments() != null) {
            if (getArguments().getString("title") != null) {
                tv_popup_title.setText(getArguments().getString("title"));
            }
            if (getArguments().getString("subtitle") != null) {
                tv_popup_message.setText(getArguments().getString("subtitle"));
            }
        }

        return fragmentView;
    }
    public void setListener(PopupRegisterListener mPopupListener) {
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

    public interface PopupRegisterListener {
        void okClicked(boolean isClicked);
    }
}
