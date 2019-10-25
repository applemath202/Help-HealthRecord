package com.jingheng.a105project.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Weight implements Parcelable {
    private int weightId;
    private String weight;
    private String createDate;

    private ArrayList<Weight> weights;

    public Weight(String weight , String createDate){
        this.weight = weight;
        this.createDate = createDate;
    }

    public Weight(){
        weights = new ArrayList<>();
    }

    protected Weight(Parcel in) {
        weightId = in.readInt();
        weight = in.readString();
        createDate = in.readString();
        weights = in.createTypedArrayList(Weight.CREATOR);
    }

    public static final Creator<Weight> CREATOR = new Creator<Weight>() {
        @Override
        public Weight createFromParcel(Parcel in) {
            return new Weight(in);
        }

        @Override
        public Weight[] newArray(int size) {
            return new Weight[size];
        }
    };

    public int getWeightId() {
        return weightId;
    }

    public void setWeightId(int weightId) {
        this.weightId = weightId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public ArrayList<Weight> getWeights() {
        return weights;
    }

    public void setWeights(ArrayList<Weight> weights) {
        this.weights = weights;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(weightId);
        parcel.writeString(weight);
        parcel.writeString(createDate);
        parcel.writeTypedList(weights);
    }
}
