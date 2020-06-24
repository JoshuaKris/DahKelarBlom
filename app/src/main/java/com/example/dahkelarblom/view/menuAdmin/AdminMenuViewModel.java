package com.example.dahkelarblom.view.menuAdmin;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.model.BookingModel;
import com.example.dahkelarblom.model.Customer;
import com.example.dahkelarblom.model.Merchant;
import com.example.dahkelarblom.model.PersonModel;
import com.example.dahkelarblom.model.responses.viewAllOrder.ViewAllOrderResponse;
import com.example.dahkelarblom.service.InternetService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminMenuViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;

    private MutableLiveData<String> mText = new MutableLiveData<>();
    private MutableLiveData<List<BookingModel>> orderList = new MutableLiveData<>();

    public AdminMenuViewModel(Context context) {
        internetService = new InternetService(context);
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

        Customer customer = new Customer("Budi","081808280838","budi.budiawan@gmail.com");

        Merchant merchant = new Merchant(
                "Zenta Admin",
                "(021) 53660671",
                "zenta.print@gmail.com",
                "Zenta Print",
                "Zenta123",
                "Jl. Anggrek Cakra No.16 RT.2/RW.9 (Binus Anggrek B Floor)",
                0);

        if (exist) {
            for (int i = 1; i < 4; i++) {
                model = new BookingModel(
                        String.format("ZT%03d",i),
                        "15.000",
                        "17:3" + i*2,
                        "sedang diproses",
                        merchant,
                        customer,
                        "Lunas");
                temp.add(model);
            }
            orderList.setValue(temp);
        } else {
            mText.setValue("No Booking appointment");
        }
    }

    public void getOrder() {
        Call<ViewAllOrderResponse> apiCall = InternetService.getServicesApi().getAllOrder();
        apiCall.enqueue(new Callback<ViewAllOrderResponse>() {
            @Override
            public void onResponse(Call<ViewAllOrderResponse> call, Response<ViewAllOrderResponse> response) {

            }

            @Override
            public void onFailure(Call<ViewAllOrderResponse> call, Throwable t) {

            }
        });
    }
}