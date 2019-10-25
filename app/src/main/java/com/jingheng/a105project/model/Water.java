package com.jingheng.a105project.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Water implements Parcelable {

    private int waterId;
    private String water;
    private String createDate;

    private ArrayList<Water> waters;

    public Water(String water, String createDate) {
        this.water = water;
        this.createDate = createDate;
    }
    public Water(){
        waters = new ArrayList<>();
    }

    protected Water(Parcel in) {
        waterId = in.readInt();
        water = in.readString();
        createDate = in.readString();
        waters = in.createTypedArrayList(Water.CREATOR);
    }

    public static final Creator<Water> CREATOR = new Creator<Water>() {
        @Override
        public Water createFromParcel(Parcel in) {
            return new Water(in);
        }

        @Override
        public Water[] newArray(int size) {
            return new Water[size];
        }
    };

    public int getWaterId() {
        return waterId;
    }

    public void setWaterId(int waterId) {
        this.waterId = waterId;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public ArrayList<Water> getWaters() {
        return waters;
    }

    public void setWaters(ArrayList<Water> waters) {
        this.waters = waters;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(waterId);
        parcel.writeString(water);
        parcel.writeString(createDate);
        parcel.writeTypedList(waters);
    }
}