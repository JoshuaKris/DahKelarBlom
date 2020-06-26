package com.example.dahkelarblom.model.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackBookingResponse implements Parcelable {

    @Expose
    @SerializedName("phoneNum")
    private String phoneNum;
    @Expose
    @SerializedName("StatusPembayaran")
    private String StatusPembayaran;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("pengambilanOrder")
    private String pengambilanOrder;
    @Expose
    @SerializedName("merchantEmail")
    private String merchantEmail;
    @Expose
    @SerializedName("merchantLocation")
    private String merchantLocation;
    @Expose
    @SerializedName("merchantName")
    private String merchantName;
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("idOrder")
    private int idOrder;

    protected TrackBookingResponse(Parcel in) {
        phoneNum = in.readString();
        StatusPembayaran = in.readString();
        status = in.readString();
        pengambilanOrder = in.readString();
        merchantEmail = in.readString();
        merchantLocation = in.readString();
        merchantName = in.readString();
        username = in.readString();
        idOrder = in.readInt();
    }

    public static final Creator<TrackBookingResponse> CREATOR = new Creator<TrackBookingResponse>() {
        @Override
        public TrackBookingResponse createFromParcel(Parcel in) {
            return new TrackBookingResponse(in);
        }

        @Override
        public TrackBookingResponse[] newArray(int size) {
            return new TrackBookingResponse[size];
        }
    };

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getStatusPembayaran() {
        return StatusPembayaran;
    }

    public String getStatus() {
        return status;
    }

    public String getPengambilanOrder() {
        return pengambilanOrder;
    }

    public String getMerchantEmail() {
        return merchantEmail;
    }

    public String getMerchantLocation() {
        return merchantLocation;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getUsername() {
        return username;
    }

    public int getIdOrder() {
        return idOrder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phoneNum);
        dest.writeString(StatusPembayaran);
        dest.writeString(status);
        dest.writeString(pengambilanOrder);
        dest.writeString(merchantEmail);
        dest.writeString(merchantLocation);
        dest.writeString(merchantName);
        dest.writeString(username);
        dest.writeInt(idOrder);
    }
}
