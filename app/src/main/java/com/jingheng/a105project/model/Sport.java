package com.jingheng.a105project.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Sport implements Parcelable {
    private int sportID;
    private String sportName;
    private String sportTime;
    private String createDate;

    private ArrayList<Sport> sports;

    public Sport(String sportName, String sportTime, String createDate) {
        this.sportName = sportName;
        this.sportTime = sportTime;
        this.createDate = createDate;
    }

    public Sport(){
        sports = new ArrayList<>();
    }

    public int getSportID() {
        return sportID;
    }

    public void setSportID(int sportID) {
        this.sportID = sportID;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSportTime() {
        return sportTime;
    }

    public void setSportTime(String sportTime) {
        this.sportTime = sportTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public ArrayList<Sport> getSports() {
        return sports;
    }

    public void setSports(ArrayList<Sport> sports) {
        this.sports = sports;
    }

    public static Creator<Sport> getCREATOR() {
        return CREATOR;
    }

    protected Sport(Parcel in) {
        sportID = in.readInt();
        sportName = in.readString();
        sportTime = in.readString();
        createDate = in.readString();
        sports = in.createTypedArrayList(Sport.CREATOR);
    }

    public static final Creator<Sport> CREATOR = new Creator<Sport>() {
        @Override
        public Sport createFromParcel(Parcel in) {
            return new Sport(in);
        }

        @Override
        public Sport[] newArray(int size) {
            return new Sport[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(sportID);
        parcel.writeString(sportName);
        parcel.writeString(sportTime);
        parcel.writeString(createDate);
        parcel.writeTypedList(sports);
    }
}
