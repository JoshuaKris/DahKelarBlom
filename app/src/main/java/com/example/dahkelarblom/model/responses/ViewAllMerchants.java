package com.example.dahkelarblom.model.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewAllMerchants implements Parcelable {

    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("usernameAdmin")
    private String usernameAdmin;
    @Expose
    @SerializedName("phoneNum")
    private String phoneNum;
    @Expose
    @SerializedName("merchantEmail")
    private String merchantEmail;
    @Expose
    @SerializedName("merchantLocationId")
    private String merchantLocationId;
    @Expose
    @SerializedName("merchantLocation")
    private String merchantLocation;
    @Expose
    @SerializedName("merchantName")
    private String merchantName;
    @Expose
    @SerializedName("idmerchant")
    private int idmerchant;

    private ViewAllMerchants(Parcel in) {
        password = in.readString();
        usernameAdmin = in.readString();
        phoneNum = in.readString();
        merchantEmail = in.readString();
        merchantLocationId = in.readString();
        merchantLocation = in.readString();
        merchantName = in.readString();
        idmerchant = in.readInt();
    }

    public static final Creator<ViewAllMerchants> CREATOR = new Creator<ViewAllMerchants>() {
        @Override
        public ViewAllMerchants createFromParcel(Parcel in) {
            return new ViewAllMerchants(in);
        }

        @Override
        public ViewAllMerchants[] newArray(int size) {
            return new ViewAllMerchants[size];
        }
    };

    public String getPassword() {
        return password;
    }

    public String getUsernameAdmin() {
        return usernameAdmin;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getMerchantEmail() {
        return merchantEmail;
    }

    public String getMerchantLocationId() {
        return merchantLocationId;
    }

    public String getMerchantLocation() {
        return merchantLocation;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public int getIdmerchant() {
        return idmerchant;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(password);
        dest.writeString(usernameAdmin);
        dest.writeString(phoneNum);
        dest.writeString(merchantEmail);
        dest.writeString(merchantLocationId);
        dest.writeString(merchantLocation);
        dest.writeString(merchantName);
        dest.writeInt(idmerchant);
    }
}
