package com.example.dahkelarblom.model.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewAllOrderResponse implements Parcelable {

    @Expose
    @SerializedName("StatusPembayaran")
    private String StatusPembayaran;
    @Expose
    @SerializedName("status")
    private String status;
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
    @SerializedName("keterangan")
    private String keterangan;
    @Expose
    @SerializedName("jnsOrder")
    private String jnsOrder;
    @Expose
    @SerializedName("idorder")
    private int idorder;

    protected ViewAllOrderResponse(Parcel in) {
        StatusPembayaran = in.readString();
        status = in.readString();
        merchantId = in.readInt();
        codeBooking = in.readString();
        pengambilanOrder = in.readString();
        noHp = in.readString();
        username = in.readString();
        keterangan = in.readString();
        jnsOrder = in.readString();
        idorder = in.readInt();
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

    public String getStatusPembayaran() {
        return StatusPembayaran;
    }

    public String getStatus() {
        return status;
    }

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

    public String getKeterangan() {
        return keterangan;
    }

    public String getJnsOrder() {
        return jnsOrder;
    }

    public int getIdorder() {
        return idorder;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        StatusPembayaran = statusPembayaran;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public void setCodeBooking(String codeBooking) {
        this.codeBooking = codeBooking;
    }

    public void setPengambilanOrder(String pengambilanOrder) {
        this.pengambilanOrder = pengambilanOrder;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setJnsOrder(String jnsOrder) {
        this.jnsOrder = jnsOrder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(StatusPembayaran);
        dest.writeString(status);
        dest.writeInt(merchantId);
        dest.writeString(codeBooking);
        dest.writeString(pengambilanOrder);
        dest.writeString(noHp);
        dest.writeString(username);
        dest.writeString(keterangan);
        dest.writeString(jnsOrder);
        dest.writeInt(idorder);
    }
}
