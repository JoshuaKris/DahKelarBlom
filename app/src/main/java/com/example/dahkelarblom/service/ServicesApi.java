package com.example.dahkelarblom.service;

import com.example.dahkelarblom.model.responses.viewAllOrder.ViewAllOrderResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicesApi {

    @GET("orders/viewallorder")
    Call<ViewAllOrderResponse> getAllOrder();

}
