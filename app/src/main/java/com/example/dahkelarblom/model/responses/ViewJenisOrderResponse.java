package com.example.dahkelarblom.model.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewJenisOrderResponse implements Parcelable {

    @Expose
    @SerializedName("jenisOrder")
    private String jenisOrder;
    @Expose
    @SerializedName("idjenisOrder")
    private int idjenisOrder;

    protected ViewJenisOrderResponse(Parcel in) {
        jenisOrder = in.readString();
        idjenisOrder = in.readInt();
    }

    public static final Creator<ViewJenisOrderResponse> CREATOR = new Creator<ViewJenisOrderResponse>() {
        @Override
        public ViewJenisOrderResponse createFromParcel(Parcel in) {
            return new ViewJenisOrderResponse(in);
        }

        @Override
        public ViewJenisOrderResponse[] newArray(int size) {
            return new ViewJenisOrderResponse[size];
        }
    };

    public String getJenisOrder() {
        return jenisOrder;
    }

    public int getIdjenisOrder() {
        return idjenisOrder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(jenisOrder);
        dest.writeInt(idjenisOrder);
    }
}
