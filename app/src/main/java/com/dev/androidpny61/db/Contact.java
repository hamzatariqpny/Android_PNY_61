package com.dev.androidpny61.db;

public class Contact {

    private String  mName;
    private String mNumber;
    private  int mImage;

    public Contact(String mName, String mNumber, int mImage) {
        this.mName = mName;
        this.mNumber = mNumber;
        this.mImage = mImage;
    }

    public String getmName() {
        return mName;
    }

    public String getmNumber() {
        return mNumber;
    }

    public int getmImage() {
        return mImage;
    }
}
