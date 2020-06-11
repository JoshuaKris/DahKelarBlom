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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.dahkelarblom.R;

import java.util.Objects;

public class EmptyTrackingDialogFragment extends DialogFragment {


    private Button
            bt_ok;
    private TextView
            tv_empty_tracking_title,
            tv_empty_tracking_message;
    private EmptyTrackingPopupListener
            mEmptyTrackingPopupListener;

    public EmptyTrackingDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Objects.requireNonNull(getDialog().getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCancelable(false);
        View fragmentView = inflater.inflate(R.layout.fragment_empty_tracking_dialog, container, false);

        bt_ok = fragmentView.findViewById(R.id.bt_ok);
        tv_empty_tracking_title = fragmentView.findViewById(R.id.tv_empty_tracking_title);
        tv_empty_tracking_message = fragmentView.findViewById(R.id.tv_empty_tracking_message);

        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
                if (mEmptyTrackingPopupListener != null) {
                    mEmptyTrackingPopupListener.okClicked(true);
                }
            }
        });

    }

    public void setListener(EmptyTrackingPopupListener mEmptyTrackingPopupListener) {
        this.mEmptyTrackingPopupListener = mEmptyTrackingPopupListener;
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = Objects.requireNonNull(window).getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawableResource(R.drawable.dialog_background);
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog().getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public interface EmptyTrackingPopupListener {
        void okClicked(boolean isClicked);
    }
}
