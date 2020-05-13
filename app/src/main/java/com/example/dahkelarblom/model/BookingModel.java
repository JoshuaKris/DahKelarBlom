package com.example.dahkelarblom.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BookingModel implements Parcelable {

    private String bookingCode;
    private String bookingPrice;
    private String bookingPickupTime;
    private String bookingStatus;
    private Merchant merchantToBook;

    public BookingModel(String bookingCode, String bookingPrice, String bookingPickupTime, String bookingStatus, Merchant merchantToBook) {
        this.bookingCode = bookingCode;
        this.bookingPrice = bookingPrice;
        this.bookingPickupTime = bookingPickupTime;
        this.bookingStatus = bookingStatus;
        this.merchantToBook = merchantToBook;
    }

    protected BookingModel(Parcel in) {
        bookingCode = in.readString();
        bookingPrice = in.readString();
        bookingPickupTime = in.readString();
        bookingStatus = in.readString();
        merchantToBook = in.readParcelable(Merchant.class.getClassLoader());
    }

    public static final Creator<BookingModel> CREATOR = new Creator<BookingModel>() {
        @Override
        public BookingModel createFromParcel(Parcel in) {
            return new BookingModel(in);
        }

        @Override
        public BookingModel[] newArray(int size) {
            return new BookingModel[size];
        }
    };

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(String bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public String getBookingPickupTime() {
        return bookingPickupTime;
    }

    public void setBookingPickupTime(String bookingPickupTime) {
        this.bookingPickupTime = bookingPickupTime;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Merchant getMerchantToBook() {
        return merchantToBook;
    }

    public void setMerchantToBook(Merchant merchantToBook) {
        this.merchantToBook = merchantToBook;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookingCode);
        dest.writeString(bookingPrice);
        dest.writeString(bookingPickupTime);
        dest.writeString(bookingStatus);
        dest.writeParcelable(merchantToBook, flags);
    }
}
