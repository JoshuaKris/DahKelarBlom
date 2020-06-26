package com.example.dahkelarblom.view.menuAdmin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dahkelarblom.model.BookingModel;
import com.example.dahkelarblom.model.Customer;
import com.example.dahkelarblom.model.Merchant;
import com.example.dahkelarblom.model.responses.ViewAllOrderResponse;
import com.example.dahkelarblom.service.InternetService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminMenuViewModel extends ViewModel {

    private final InternetService internetService;
    private Call<String> apiCall;
    private Context context;

    private MutableLiveData<String> mText = new MutableLiveData<>();
    private MutableLiveData<List<BookingModel>> orderList = new MutableLiveData<>();
    private MutableLiveData<String> changeStatusOrder = new MutableLiveData<>();
    private MutableLiveData<List<ViewAllOrderResponse>> adminOrderList = new MutableLiveData<>();

    public AdminMenuViewModel(Context context) {
        this.context = context;
        internetService = new InternetService(context);
    }

    public LiveData<String> getmText() {
        return mText;
    }

    public LiveData<List<BookingModel>> getOrderList() {
        return orderList;
    }

    public LiveData<String> getChangeStatusOrder() {
        return changeStatusOrder;
    }

    public LiveData<List<ViewAllOrderResponse>> getAdminOrderList() {
        return adminOrderList;
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

//    public void getOrder(JsonObject jsonObject) {
//        Call<ViewAllOrderResponse> apiCall = InternetService.getServicesApi().getAllOrder(jsonObject);
//        apiCall.enqueue(new Callback<ViewAllOrderResponse>() {
//            @Override
//            public void onResponse(Call<ViewAllOrderResponse> call, Response<ViewAllOrderResponse> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<ViewAllOrderResponse> call, Throwable t) {
//
//            }
//        });
//    }

    public void changeStatusOrder(JsonObject jsonObject) {
        apiCall = InternetService.getServicesApi().changeBookingStatus(jsonObject);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.d("getChangeStatusOrder", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void fetchAdminOrderList(JsonObject jsonObject) {
        apiCall = InternetService.getServicesApi().getAdminOrderList(jsonObject);
        apiCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String s = response.body();
                    Type listType = new TypeToken<ArrayList<ViewAllOrderResponse>>(){}.getType();
                    List<ViewAllOrderResponse> temp = new GsonBuilder().create().fromJson(s,listType);
                    adminOrderList.setValue(temp);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}