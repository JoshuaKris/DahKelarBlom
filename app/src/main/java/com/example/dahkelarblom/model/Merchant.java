package com.example.dahkelarblom.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Merchant extends PersonModel implements Parcelable{
    private String merchantStoreName;
    private String merchantPassword;
    private String merchantEmail;
    private String merchantAddress;
    private int merchantImage;

    public Merchant(String name, String phoneNum, String merchantStoreName, String merchantPassword, String merchantEmail, String merchantAddress, int merchantImage) {
        super(name, phoneNum);
        this.merchantStoreName = merchantStoreName;
        this.merchantPassword = merchantPassword;
        this.merchantEmail = merchantEmail;
        this.merchantAddress = merchantAddress;
        this.merchantImage = merchantImage;
    }

    public Merchant(Parcel in, String merchantStoreName, String merchantPassword, String merchantEmail, String merchantAddress, int merchantImage) {
        super(in);
        this.merchantStoreName = merchantStoreName;
        this.merchantPassword = merchantPassword;
        this.merchantEmail = merchantEmail;
        this.merchantAddress = merchantAddress;
        this.merchantImage = merchantImage;
    }

    protected Merchant(Parcel in) {
        super(in);
        merchantStoreName = in.readString();
        merchantPassword = in.readString();
        merchantEmail = in.readString();
        merchantAddress = in.readString();
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

    public String getMerchantStoreName() {
        return merchantStoreName;
    }

    public void setMerchantStoreName(String merchantStoreName) {
        this.merchantStoreName = merchantStoreName;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }

    public String getMerchantEmail() {
        return merchantEmail;
    }

    public void setMerchantEmail(String merchantEmail) {
        this.merchantEmail = merchantEmail;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
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
        dest.writeString(merchantStoreName);
        dest.writeString(merchantPassword);
        dest.writeString(merchantEmail);
        dest.writeString(merchantAddress);
        dest.writeInt(merchantImage);
    }
}