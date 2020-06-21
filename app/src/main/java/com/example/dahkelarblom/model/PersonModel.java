package com.example.dahkelarblom.model;


import android.os.Parcel;
import android.os.Parcelable;

public abstract class PersonModel implements Parcelable {
    private String name;
    private String phoneNum;
    private String email;

     public PersonModel(String name, String phoneNum, String email) {
         this.name = name;
         this.phoneNum = phoneNum;
         this.email = email;
     }

     public PersonModel(Parcel in) {
         name = in.readString();
         phoneNum = in.readString();
         email = in.readString();
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

     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNum);
        dest.writeString(email);
    }
}
