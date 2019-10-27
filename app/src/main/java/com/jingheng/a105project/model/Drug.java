package com.jingheng.a105project.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Drug implements Parcelable {
    private static final Drug ourInstance = new Drug();
    private int drugId;
    private String drugName;
    private String drugQuantity;
    private String drugTime;
    private String createDate;

    private ArrayList<Drug> drugs;

    public Drug(String drugName, String drugQuantity, String drugTime , String createDate) {
        this.drugName = drugName;
        this.drugQuantity = drugQuantity;
        this.drugTime = drugTime;
        this.createDate = createDate;
    }

    public Drug() {
        drugs = new ArrayList<>();
    }

    protected Drug(Parcel in) {
        drugId = in.readInt();
        drugName = in.readString();
        drugQuantity = in.readString();
        createDate = in.readString();
        drugs = in.createTypedArrayList(Drug.CREATOR);
    }

    public static final Creator<Drug> CREATOR = new Creator<Drug>() {
        @Override
        public Drug createFromParcel(Parcel in) {
            return new Drug(in);
        }

        @Override
        public Drug[] newArray(int size) {
            return new Drug[size];
        }
    };

    public static Drug getOurInstance() {
        return ourInstance;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugQuantity() {
        return drugQuantity;
    }

    public void setDrugQuantity(String drugQuantity) {
        this.drugQuantity = drugQuantity;
    }

    public String getDrugTime() {
        return drugTime;
    }

    public void setDrugTime(String drugTime) {
        this.drugTime = drugTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(ArrayList<Drug> drugs) {
        this.drugs = drugs;
    }

    public static Creator<Drug> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(drugId);
        parcel.writeString(drugName);
        parcel.writeString(drugQuantity);
        parcel.writeString(createDate);
        parcel.writeTypedList(drugs);
    }
}
