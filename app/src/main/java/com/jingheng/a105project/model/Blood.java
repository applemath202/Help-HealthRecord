package com.jingheng.a105project.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Blood implements Parcelable {
    private int bloodId;
    private String bloodPressure;
    private String bloodPressure_2;
    private String createDate;

    private ArrayList<Blood> bloods;

    public Blood(String bloodPressure, String bloodPressure_2, String createDate) {
        this.bloodPressure = bloodPressure;
        this.bloodPressure_2 = bloodPressure_2;
        this.createDate = createDate;
    }

    public Blood() {
        bloods = new ArrayList<>();
    }

    protected Blood(Parcel in) {
        bloodId = in.readInt();
        bloodPressure = in.readString();
        bloodPressure_2 = in.readString();
        createDate = in.readString();
        bloods = in.createTypedArrayList(Blood.CREATOR);
    }

    public static final Creator<Blood> CREATOR = new Creator<Blood>() {
        @Override
        public Blood createFromParcel(Parcel in) {
            return new Blood(in);
        }

        @Override
        public Blood[] newArray(int size) {
            return new Blood[size];
        }
    };

    public int getBloodId() {
        return bloodId;
    }

    public void setBloodId(int bloodId) {
        this.bloodId = bloodId;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getBloodPressure_2() {
        return bloodPressure_2;
    }

    public void setBloodPressure_2(String bloodPressure_2) {
        this.bloodPressure_2 = bloodPressure_2;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public ArrayList<Blood> getBloods() {
        return bloods;
    }

    public void setBloods(ArrayList<Blood> bloods) {
        this.bloods = bloods;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(bloodId);
        parcel.writeString(bloodPressure);
        parcel.writeString(bloodPressure_2);
        parcel.writeString(createDate);
        parcel.writeTypedList(bloods);
    }
}
