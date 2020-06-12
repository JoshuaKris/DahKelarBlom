package com.example.dahkelarblom.view.menuUser.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dahkelarblom.model.Customer;
import com.example.dahkelarblom.popup.EmptyTrackingDialogFragment;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.popup.TrackingDialogFragment;
import com.example.dahkelarblom.model.BookingModel;
import com.example.dahkelarblom.model.Merchant;

public class TrackingFragment extends Fragment {

    private TrackingViewModel trackingViewModel;
    private TextView textView;
    private TextView tv_enter_booking_code;
    private EditText et_booking_code_field;
    private Button bt_tracking_booking;
    private TrackingDialogFragment trackingDialogFragment;
    private EmptyTrackingDialogFragment emptyTrackingDialogFragment;
    private BookingModel bookingTemp;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        trackingViewModel = ViewModelProviders.of(this).get(TrackingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tracking, container, false);
        textView = root.findViewById(R.id.text_tracking);
        tv_enter_booking_code = root.findViewById(R.id.tv_enter_booking_code);
        et_booking_code_field = root.findViewById(R.id.et_booking_code_field);
        bt_tracking_booking = root.findViewById(R.id.bt_tracking_booking);

        initLiveData();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createBookingTemp();

        bt_tracking_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".contentEquals(et_booking_code_field.getText())) {
                    trackingDialogFragment = TrackingDialogFragment.newInstance(bookingTemp);
                    trackingDialogFragment.show(getChildFragmentManager(), "popupSuccess");
                } else {
                    emptyTrackingDialogFragment = new EmptyTrackingDialogFragment();
                    emptyTrackingDialogFragment.show(getChildFragmentManager(), "popupEmpty");
                }

            }
        });
    }

    private void initLiveData() {
        trackingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
    }

    private void createBookingTemp() {
        Customer customer = new Customer("Budi","081808280838");

        Merchant merchant = new Merchant(
                "Zenta Admin",
                "(021) 53660671",
                "Zenta Print",
                "Zenta123",
                "zenta.print@gmail.com",
                "Jl. Anggrek Cakra No.16 RT.2/RW.9 (Binus Anggrek B Floor)",
                0);

        bookingTemp = new BookingModel(
                "ZT007",
                "12.000",
                "18:30",
                "proses",
                merchant,
                customer,
                "Lunas");
    }

}
