package com.example.dahkelarblom.view.menuUser.booking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.BookingModel;

import java.util.ArrayList;
import java.util.List;

public class BookingFragment extends Fragment implements BookingListAdapter.BookingListOnClickListener {

    private BookingViewModel bookingViewModel;
    private TextView textView;
    private RecyclerView rv_booking_list;
    private List<BookingModel> bookingList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookingViewModel = ViewModelProviders.of(this).get(BookingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_booking, container, false);

        bookingViewModel.fetchBookingData(true);

        textView = root.findViewById(R.id.text_booking);
        rv_booking_list = root.findViewById(R.id.rv_booking_list);

        initLiveData();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initLiveData() {
        bookingViewModel.getBookingList().observe(getViewLifecycleOwner(), new Observer<List<BookingModel>>() {
            @Override
            public void onChanged(List<BookingModel> bookingModels) {
                if (bookingModels != null) {
                    bookingList = bookingModels;
                    updateRV(bookingList);
                }
            }
        });

        bookingViewModel.getmText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    if (!s.isEmpty()) {
                        textView.setText(s);
                    }
                }
            }
        });
    }

    private void updateRV(List<BookingModel> temp) {
        rv_booking_list.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_booking_list.setAdapter(new BookingListAdapter(temp,this));
    }

    @Override
    public void onClick(int pos) {
        Toast.makeText(getContext(), "this is " + bookingList.get(pos).getBookingCode(), Toast.LENGTH_SHORT).show();
    }
}
