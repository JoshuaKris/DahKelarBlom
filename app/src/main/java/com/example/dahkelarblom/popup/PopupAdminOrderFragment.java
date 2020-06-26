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

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.DialogFragment;

import com.example.dahkelarblom.R;
import com.example.dahkelarblom.SpinnerStatusAdapter;
import com.example.dahkelarblom.model.BookingModel;
import com.example.dahkelarblom.model.SpinnerStatus;
import com.example.dahkelarblom.model.responses.ViewAllOrderResponse;

import java.util.ArrayList;
import java.util.Objects;

public class PopupAdminOrderFragment extends DialogFragment {

    private PopupAdminOrderListener mPopupListener;
    private ViewAllOrderResponse mItem;

    private ImageView iv_close;
    private TextView
            tv_bookingCode,
            tv_bookingPickup,
            tv_bookingInfo,
            tv_userName,
            tv_userPhone,
            tv_payment_status;
    private Button bt_file, bt_change_status;
    private AppCompatSpinner spinner_status;
    private ArrayList<SpinnerStatus> list;
    private SpinnerStatusAdapter spinnerAdapter;

    public PopupAdminOrderFragment() {
        // Required empty public constructor
    }

    public static PopupAdminOrderFragment newInstance(ViewAllOrderResponse item) {
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
        View fragmentView = inflater.inflate(R.layout.fragment_popup_admin_order, container, false);

        iv_close = fragmentView.findViewById(R.id.iv_close);
        tv_bookingCode = fragmentView.findViewById(R.id.tv_bookingCode);
        tv_bookingPickup = fragmentView.findViewById(R.id.tv_bookingPickup);
        tv_bookingInfo = fragmentView.findViewById(R.id.tv_bookingInfo);
        tv_userName = fragmentView.findViewById(R.id.tv_userName);
        tv_userPhone = fragmentView.findViewById(R.id.tv_userPhone);
        tv_payment_status = fragmentView.findViewById(R.id.tv_payment_status);
        bt_file = fragmentView.findViewById(R.id.bt_file);
        bt_change_status = fragmentView.findViewById(R.id.bt_change_status);
        spinner_status = fragmentView.findViewById(R.id.spinner_status);

        list = new ArrayList<>();
        list.add(new SpinnerStatus("file belum diterima",false));
        list.add(new SpinnerStatus("file sudah diterima",false));
        list.add(new SpinnerStatus("belum diproses",false));
        list.add(new SpinnerStatus("sedang diproses",false));
        list.add(new SpinnerStatus("sudah selesai",false));

        if (getArguments() != null) {
            mItem = getArguments().getParcelable("item");
            if (mItem != null) {
                tv_bookingCode.setText(mItem.getCodeBooking());
                tv_bookingPickup.setText(mItem.getPengambilanOrder());
                tv_bookingInfo.setText(mItem.getKeterangan());
                tv_userName.setText(mItem.getUsername());
                tv_userPhone.setText(mItem.getNoHp());
                tv_payment_status.setText("");

                spinnerAdapter = new SpinnerStatusAdapter(getContext(),list);
                spinner_status.setAdapter(spinnerAdapter);

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getStatus().equals(mItem.getStatus())) {
                        spinner_status.setSelection(i);
                    }
                }

            }
        }

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });

        bt_change_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getDialog()).dismiss();
                if (mPopupListener != null) {
                    mItem.setStatus(list.get(spinner_status.getSelectedItemPosition()).getStatus());
                    mPopupListener.okClicked(true, mItem);
                }
            }
        });

        //hide file button
        bt_file.setVisibility(View.GONE);
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
        void okClicked(boolean isClicked, ViewAllOrderResponse item);
    }
}
