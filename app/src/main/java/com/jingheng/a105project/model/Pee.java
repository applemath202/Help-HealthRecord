package com.jingheng.a105project.model;

import java.util.ArrayList;

public class Pee {
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
}
