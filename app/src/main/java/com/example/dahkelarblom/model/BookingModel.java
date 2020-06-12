package com.example.dahkelarblom.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BookingModel implements Parcelable {

    private String bookingCode;
    private String bookingPrice;
    private String bookingPickupTime;
    private String bookingStatus;
    private Merchant merchantToBook;
    private Customer customer;
    private String customerPaymentStatus;

    protected BookingModel(Parcel in) {
        bookingCode = in.readString();
        bookingPrice = in.readString();
        bookingPickupTime = in.readString();
        bookingStatus = in.readString();
        merchantToBook = in.readParcelable(Merchant.class.getClassLoader());
        customer = in.readParcelable(Customer.class.getClassLoader());
        customerPaymentStatus = in.readString();
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerPaymentStatus() {
        return customerPaymentStatus;
    }

    public void setCustomerPaymentStatus(String customerPaymentStatus) {
        this.customerPaymentStatus = customerPaymentStatus;
    }

    public BookingModel(String bookingCode, String bookingPrice, String bookingPickupTime, String bookingStatus, Merchant merchantToBook, Customer customer, String customerPaymentStatus) {
        this.bookingCode = bookingCode;
        this.bookingPrice = bookingPrice;
        this.bookingPickupTime = bookingPickupTime;
        this.bookingStatus = bookingStatus;
        this.merchantToBook = merchantToBook;
        this.customer = customer;
        this.customerPaymentStatus = customerPaymentStatus;
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
        dest.writeParcelable(customer, flags);
        dest.writeString(customerPaymentStatus);
    }
}
