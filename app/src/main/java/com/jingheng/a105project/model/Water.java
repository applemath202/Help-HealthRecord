package com.jingheng.a105project.model;

import java.util.ArrayList;

public class Water {
    private String pee;
    private String createDate;

    private ArrayList<Water> waters;

    public Water(String pee, String createDate) {
        this.pee = pee;
        this.createDate = createDate;
    }

    public Water(){waters = new ArrayList<>();}

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

    public ArrayList<Water> getWaters() {
        return waters;
    }

    public void setWaters(ArrayList<Water> waters) {
        this.waters = waters;
    }
}