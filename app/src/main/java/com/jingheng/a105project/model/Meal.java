package com.jingheng.a105project.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Meal implements Parcelable {

    private int id;
    private String name;
    private int picture;
    private int hot;
    private int amount;
    private ArrayList<Meal> meals;

    public Meal() {
        meals = new ArrayList<>();
    }

    public Meal(Parcel in) {
        id = in.readInt();
        name = in.readString();
        picture = in.readInt();
        hot = in.readInt();
        amount = in.readInt();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(picture);
        parcel.writeInt(hot);
        parcel.writeInt(amount);
    }
}
