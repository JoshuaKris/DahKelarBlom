package com.example.dahkelarblom.model.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Parcelable {

    @Expose
    @SerializedName("operationalMerchant")
    private String operationalMerchant;
    @Expose
    @SerializedName("idmerchant")
    private int idmerchant;

    protected LoginResponse(Parcel in) {
        operationalMerchant = in.readString();
        idmerchant = in.readInt();
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    public String getOperationalMerchant() {
        return operationalMerchant;
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
        dest.writeString(operationalMerchant);
        dest.writeInt(idmerchant);
    }
}
