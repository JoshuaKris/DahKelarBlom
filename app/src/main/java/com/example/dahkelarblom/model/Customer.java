package com.example.dahkelarblom.model;

import android.os.Parcel;

public class Customer extends PersonModel{

    public Customer(String name, String phoneNum) {
        super(name, phoneNum);
    }

    private Customer(Parcel in) {
        super(in);
    }

    //need to copy this
    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };


}
