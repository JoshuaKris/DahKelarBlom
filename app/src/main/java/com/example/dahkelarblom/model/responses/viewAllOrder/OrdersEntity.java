package com.example.dahkelarblom.model.responses.viewAllOrder;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrdersEntity implements Parcelable {
    @Expose
    @SerializedName("merchantId")
    private int merchantId;
    @Expose
    @SerializedName("codeBooking")
    private String codeBooking;
    @Expose
    @SerializedName("pengambilanOrder")
    private String pengambilanOrder;
    @Expose
    @SerializedName("noHp")
    private String noHp;
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("uploadFile")
    private String uploadFile;
    @Expose
    @SerializedName("ukrKertas")
    private String ukrKertas;
    @Expose
    @SerializedName("priceOrder")
    private String priceOrder;
    @Expose
    @SerializedName("halOrder")
    private String halOrder;
    @Expose
    @SerializedName("jnsOrder")
    private String jnsOrder;
    @Expose
    @SerializedName("idorder")
    private int idorder;

    protected OrdersEntity(Parcel in) {
        merchantId = in.readInt();
        codeBooking = in.readString();
        pengambilanOrder = in.readString();
        noHp = in.readString();
        username = in.readString();
        uploadFile = in.readString();
        ukrKertas = in.readString();
        priceOrder = in.readString();
        halOrder = in.readString();
        jnsOrder = in.readString();
        idorder = in.readInt();
    }

    public static final Creator<OrdersEntity> CREATOR = new Creator<OrdersEntity>() {
        @Override
        public OrdersEntity createFromParcel(Parcel in) {
            return new OrdersEntity(in);
        }

        @Override
        public OrdersEntity[] newArray(int size) {
            return new OrdersEntity[size];
        }
    };

    public int getMerchantId() {
        return merchantId;
    }

    public String getCodeBooking() {
        return codeBooking;
    }

    public String getPengambilanOrder() {
        return pengambilanOrder;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getUsername() {
        return username;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public String getUkrKertas() {
        return ukrKertas;
    }

    public String getPriceOrder() {
        return priceOrder;
    }

    public String getHalOrder() {
        return halOrder;
    }

    public String getJnsOrder() {
        return jnsOrder;
    }

    public int getIdorder() {
        return idorder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(merchantId);
        dest.writeString(codeBooking);
        dest.writeString(pengambilanOrder);
        dest.writeString(noHp);
        dest.writeString(username);
        dest.writeString(uploadFile);
        dest.writeString(ukrKertas);
        dest.writeString(priceOrder);
        dest.writeString(halOrder);
        dest.writeString(jnsOrder);
        dest.writeInt(idorder);
    }
}
