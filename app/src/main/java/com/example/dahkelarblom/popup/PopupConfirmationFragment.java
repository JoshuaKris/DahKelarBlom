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

public class PopupConfirmationFragment extends DialogFragment {

    private PopupConfirmationListener mPopupListener;

    private TextView tv_confirmation_title, tv_confirmation_message;
    private Button bt_confirmation_ok,bt_confirmation_cancel;

    public PopupConfirmationFragment() {
        // Required empty public constructor
    }

    public static PopupConfirmationFragment newInstance() {
        PopupConfirmationFragment fragment = new PopupConfirmationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static PopupConfirmationFragment newInstance(String title, String subtitle) {
        PopupConfirmationFragment fragment = new PopupConfirmationFragment();
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
        View fragmentView = inflater.inflate(R.layout.fragment_popup_confirmation, container, false);

        bt_confirmation_ok = fragmentView.findViewById(R.id.bt_confirmation_ok);
        bt_confirmation_cancel = fragmentView.findViewById(R.id.bt_confirmation_cancel);
        tv_confirmation_title = fragmentView.findViewById(R.id.tv_confirmation_title);
        tv_confirmation_message = fragmentView.findViewById(R.id.tv_confirmation_message);

        bt_confirmation_cancel.setOnClickListener(view -> {
            getDialog().dismiss();
            if (mPopupListener != null) {
                mPopupListener.cancelClicked(true);
            }
        });

        bt_confirmation_ok.setOnClickListener(v -> {
            getDialog().dismiss();
            if (mPopupListener != null) {
                mPopupListener.okClicked(true);
            }
        });

        if (getArguments() != null) {
            if (getArguments().getString("title") != null) {
                tv_confirmation_title.setText(getArguments().getString("title"));
            }
            if (getArguments().getString("subtitle") != null) {
                tv_confirmation_message.setText(getArguments().getString("subtitle"));
            }
        }

        return fragmentView;
    }
    public void setListener(PopupConfirmationListener mPopupListener) {
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

    public interface PopupConfirmationListener {
        void cancelClicked(boolean isCancelClicked);
        void okClicked(boolean isOkClicked);
    }
}
