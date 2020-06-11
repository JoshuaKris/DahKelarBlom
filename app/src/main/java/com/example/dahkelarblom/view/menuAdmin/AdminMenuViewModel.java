package com.example.dahkelarblom.view.menuAdmin;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.model.BookingModel;
import com.example.dahkelarblom.model.Merchant;

import java.util.ArrayList;
import java.util.List;

public class AdminMenuViewModel extends ViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>();
    private MutableLiveData<List<BookingModel>> orderList = new MutableLiveData<>();

    public AdminMenuViewModel() {
    }

    public LiveData<String> getmText() {
        return mText;
    }

    public LiveData<List<BookingModel>> getOrderList() {
        return orderList;
    }

    @SuppressLint("DefaultLocale")
    public void fetchOrderData(boolean exist) {
        List<BookingModel> temp = new ArrayList<>();
        BookingModel model;
        Merchant merchant = new Merchant(
                "Zenta Print",
                "Jl. Anggrek Cakra No.16 RT.2/RW.9 (Binus Anggrek B Floor)",
                "(021) 53660671",
                0);

        if (exist) {
            for (int i = 1; i < 4; i++) {
                model = new BookingModel(
                        String.format("ZT%03d",i),
                        "15.000",
                        "17:3" + i*2,
                        "sedang diproses",
                        merchant);
                temp.add(model);
            }
            orderList.setValue(temp);
        } else {
            mText.setValue("No Booking appointment");
        }
    }
}