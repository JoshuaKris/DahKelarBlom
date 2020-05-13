package com.example.dahkelarblom.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Merchant implements Parcelable {
    private String merchantName;
    private String merchantAddress;
    private String merchantPhoneNum;
    private int merchantImage;

    public Merchant(String merchantName, String merchantAddress, String merchantPhoneNum, int merchantImage) {
        this.merchantName = merchantName;
        this.merchantAddress = merchantAddress;
        this.merchantPhoneNum = merchantPhoneNum;
        this.merchantImage = merchantImage;
    }

    private Merchant(Parcel in) {
        merchantName = in.readString();
        merchantAddress = in.readString();
        merchantPhoneNum = in.readString();
        merchantImage = in.readInt();
    }

    public static final Creator<Merchant> CREATOR = new Creator<Merchant>() {
        @Override
        public Merchant createFromParcel(Parcel in) {
            return new Merchant(in);
        }

        @Override
        public Merchant[] newArray(int size) {
            return new Merchant[size];
        }
    };

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getMerchantPhoneNum() {
        return merchantPhoneNum;
    }

    public void setMerchantPhoneNum(String merchantPhoneNum) {
        this.merchantPhoneNum = merchantPhoneNum;
    }

    public int getMerchantImage() {
        return merchantImage;
    }

    public void setMerchantImage(int merchantImage) {
        this.merchantImage = merchantImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(merchantName);
        dest.writeString(merchantAddress);
        dest.writeString(merchantPhoneNum);
        dest.writeInt(merchantImage);
    }
}