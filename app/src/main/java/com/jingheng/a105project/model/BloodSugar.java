package com.jingheng.a105project.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class BloodSugar implements Parcelable {

    private int bloodsugarId;
    private String bloodsugar;
    private String createDate;

    private ArrayList<BloodSugar> bloodsugars;

    public BloodSugar(String bloodsugar , String createDate){
        this.bloodsugar = bloodsugar;
        this.createDate = createDate;
    }

    public BloodSugar(){
        bloodsugars = new ArrayList<>();
    }

    protected BloodSugar(Parcel in) {
        bloodsugarId=in.readInt();
        bloodsugar = in.readString();
        createDate = in.readString();
        bloodsugars = in.createTypedArrayList(BloodSugar.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bloodsugar);
        dest.writeString(createDate);
        dest.writeTypedList(bloodsugars);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BloodSugar> CREATOR = new Creator<BloodSugar>() {
        @Override
        public BloodSugar createFromParcel(Parcel in) {
            return new BloodSugar(in);
        }

        @Override
        public BloodSugar[] newArray(int size) {
            return new BloodSugar[size];
        }
    };

    public String getBloodsugar() {
        return bloodsugar;
    }

    public int getBloodsugarId() {
        return bloodsugarId;
    }

    public void setBloodsugarId(int bloodsugarId) {
        this.bloodsugarId = bloodsugarId;
    }

    public static Creator<BloodSugar> getCREATOR() {
        return CREATOR;
    }

    public void setBloodsugar(String bloodsugar) {
        this.bloodsugar = bloodsugar;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public ArrayList<BloodSugar> getBloodsugars() {
        return bloodsugars;
    }

    public void setBloodsugars(ArrayList<BloodSugar> bloodsugars) {
        this.bloodsugars = bloodsugars;
    }
}
