package com.example.dahkelarblom.model.responses.viewAllOrder;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewAllOrderResponse implements Parcelable {

    @Expose
    @SerializedName("orders")
    private List<OrdersEntity> orders;

    protected ViewAllOrderResponse(Parcel in) {
        orders = in.createTypedArrayList(OrdersEntity.CREATOR);
    }

    public static final Creator<ViewAllOrderResponse> CREATOR = new Creator<ViewAllOrderResponse>() {
        @Override
        public ViewAllOrderResponse createFromParcel(Parcel in) {
            return new ViewAllOrderResponse(in);
        }

        @Override
        public ViewAllOrderResponse[] newArray(int size) {
            return new ViewAllOrderResponse[size];
        }
    };

    public List<OrdersEntity> getOrders() {
        return orders;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(orders);
    }
}
