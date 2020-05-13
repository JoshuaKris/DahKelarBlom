package com.example.dahkelarblom.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DialogItem implements Parcelable {

    private String ItemTitle;
    private boolean selected;

    public DialogItem(String itemTitle, boolean selected) {
        ItemTitle = itemTitle;
        this.selected = selected;
    }

    protected DialogItem(Parcel in) {
        ItemTitle = in.readString();
        selected = in.readByte() != 0;
    }

    public static final Creator<DialogItem> CREATOR = new Creator<DialogItem>() {
        @Override
        public DialogItem createFromParcel(Parcel in) {
            return new DialogItem(in);
        }

        @Override
        public DialogItem[] newArray(int size) {
            return new DialogItem[size];
        }
    };

    public String getItemTitle() {
        return ItemTitle;
    }

    public void setItemTitle(String itemTitle) {
        ItemTitle = itemTitle;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ItemTitle);
        dest.writeByte((byte) (selected ? 1 : 0));
    }
}
