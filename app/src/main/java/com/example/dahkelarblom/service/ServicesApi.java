package com.example.dahkelarblom.service;

import com.example.dahkelarblom.model.responses.viewAllOrder.ViewAllOrderResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServicesApi {

   //get all order
    @GET("orders/viewallorder")
    Call<ViewAllOrderResponse> getAllOrder();

    //get all merchant
    @GET("/merchants/viewall")
    Call<String> getAllMerchant();

    //track booking
    @POST()
    @FormUrlEncoded
    Call<String> trackMyBooking(
            @Field("kode") String kode
    );

    //login admin
    @POST()
    @FormUrlEncoded
    Call<String> loginAdmin(
            @Field("username") String username,
            @Field("password") String password
    );

    //register admin
    @POST()
    @FormUrlEncoded
    Call<String> registerAdmin(
            @Field("merchantName") String merchantName,
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("merchantPhoneNum") String merchantPhoneNum,
            @Field("merchantAddress") String merchantAddress
    );

    //get order of admin
    @GET()
    Call<String> getAdminOrderList(
            @Query("usename") String username,
            @Query("id") String id
    );

    //change status of admin booking
    @POST()
    @FormUrlEncoded
    Call<String> changeBookingStatus(
            @Field("id") String bookingId,
            @Field("username") String username,
            @Field("status") String status
    );


    //order from admin
    @POST()
    @FormUrlEncoded
    Call<String> adminAddOrder(
            @Field("orderType") String orderType,
            @Field("name") String name,
            @Field("phoneNum") String phoneNum,
            @Field("price") String price,
            @Field("estimation") String estimation,
            @Field("paymentStatus") String paymentStatus
    );

    //order from user
    @POST()
    @FormUrlEncoded
    Call<String> userAddOrder(
            @Field("orderType") String orderType,
            @Field("name") String name,
            @Field("phoneNum") String phoneNum,
            @Field("estimation") String estimation,
            @Field("info") String info
    );

}
