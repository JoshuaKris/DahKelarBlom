package com.example.dahkelarblom.model;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class PersonModel implements Parcelable{
    private String name;
    private String phoneNum;

    PersonModel(String name, String phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }

    PersonModel(Parcel in) {
        name = in.readString();
        phoneNum = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNum);
    }
}
