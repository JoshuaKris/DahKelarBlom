package com.example.dahkelarblom.view.menuUser.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dahkelarblom.BaseVMF;
import com.example.dahkelarblom.model.Customer;
import com.example.dahkelarblom.model.responses.TrackBookingResponse;
import com.example.dahkelarblom.popup.EmptyTrackingDialogFragment;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.popup.TrackingDialogFragment;
import com.example.dahkelarblom.model.BookingModel;
import com.example.dahkelarblom.model.Merchant;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class TrackingFragment extends Fragment {

    private TrackingViewModel trackingViewModel;
    private EditText et_booking_code_field;
    private Button bt_tracking_booking;
    private TrackingDialogFragment trackingDialogFragment;
    private EmptyTrackingDialogFragment emptyTrackingDialogFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BaseVMF factory = new BaseVMF<>(new TrackingViewModel(getContext()));
        trackingViewModel = ViewModelProviders.of(this,factory).get(TrackingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tracking, container, false);
        et_booking_code_field = root.findViewById(R.id.et_booking_code_field);
        bt_tracking_booking = root.findViewById(R.id.bt_tracking_booking);

        initLiveData();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt_tracking_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".contentEquals(et_booking_code_field.getText())) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("codeBooking",et_booking_code_field.getText().toString());
                    trackingViewModel.fetchBooking(jsonObject);
                } else {
                    emptyTrackingDialogFragment = new EmptyTrackingDialogFragment();
                    emptyTrackingDialogFragment.show(getChildFragmentManager(), "popupEmpty");
                }

            }
        });
    }

    private void initLiveData() {

        trackingViewModel.getTrackResponse().observe(getViewLifecycleOwner(), new Observer<ArrayList<TrackBookingResponse>>() {
            @Override
            public void onChanged(ArrayList<TrackBookingResponse> trackBookingResponses) {
                if (trackBookingResponses != null) {
                    if (!trackBookingResponses.isEmpty()) {
                        TrackBookingResponse response = trackBookingResponses.get(0);
                        Log.d("getTrackBookingResponse", "onChanged: " + response);
                        trackingDialogFragment = TrackingDialogFragment.newInstance(response,et_booking_code_field.getText().toString());
                        trackingDialogFragment.show(getChildFragmentManager(), "popupSuccess");
                    } else {
                        emptyTrackingDialogFragment = new EmptyTrackingDialogFragment();
                        emptyTrackingDialogFragment.show(getChildFragmentManager(), "popupEmpty");
                    }
                }
            }
        });
    }

}
