package com.example.dahkelarblom.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServicesApi {

    //get all merchant
    @GET("/merchants/viewall")
    Call<String> getAllMerchant();

    //login admin
    @Headers("Content-Type: application/json")
    @POST("/admins/testingLogin")
    Call<String> loginAdmin(
            @Body JsonObject requestBody
    );

    //register admin
    @Headers("Content-Type: application/json")
    @POST("/admins/registrasi")
    Call<String> registerAdmin(
            @Body JsonObject requestBody
    );

    //get order of admin
    @Headers("Content-Type: application/json")
    @POST("/admins/viewordermerchant")
    Call<String> getAdminOrderList(
            @Body JsonObject requestBody
    );

    //change status of admin booking
    @Headers("Content-Type: application/json")
    @POST("/admins/changeStatus")
    Call<String> changeBookingStatus(
            @Body JsonObject requestBody
    );


    //order from admin
    @Headers("Content-Type: application/json")
    @POST("/admins/inputsOrder")
    Call<String> adminAddOrder(
            @Body JsonObject requestBody
    );

    //order from user
    @Headers("Content-Type: application/json")
    @POST("/orders/inputOrder")
    Call<String> userAddOrder(
            @Body JsonObject requestBody
    );

    //track booking
    @Headers("Content-Type: application/json")
    @POST("/orders/tracking")
    Call<String> trackMyBooking(
            @Body JsonObject requestBody
    );
}
