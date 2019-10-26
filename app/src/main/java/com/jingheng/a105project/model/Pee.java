package com.jingheng.a105project.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Pee implements Parcelable {

    private int peeId;
    private String pee;
    private String createDate;

    private ArrayList<Pee> pees;

    public Pee(String pee , String createDate){
        this.pee = pee;
        this.createDate = createDate;
    }

    public Pee(){
        pees = new ArrayList<>();
    }

    protected Pee(Parcel in) {
        peeId = in.readInt();
        pee = in.readString();
        createDate = in.readString();
        pees = in.createTypedArrayList(Pee.CREATOR);
    }

    public static final Creator<Pee> CREATOR = new Creator<Pee>() {
        @Override
        public Pee createFromParcel(Parcel in) {
            return new Pee(in);
        }

        @Override
        public Pee[] newArray(int size) {
            return new Pee[size];
        }
    };

    public int getPeeId() {
        return peeId;
    }

    public void setPeeId(int peeId) {
        this.peeId = peeId;
    }

    public String getPee() {
        return pee;
    }

    public void setPee(String pee) {
        this.pee = pee;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public ArrayList<Pee> getPees() {
        return pees;
    }

    public void setPees(ArrayList<Pee> pees) {
        this.pees = pees;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(peeId);
        parcel.writeString(pee);
        parcel.writeString(createDate);
        parcel.writeTypedList(pees);
    }
}
