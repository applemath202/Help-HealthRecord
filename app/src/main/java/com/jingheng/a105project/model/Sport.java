package com.jingheng.a105project.model;

import java.util.ArrayList;

public class Sport {
    private String sportName;
    private String sportTime;
    private String createDate;

    private ArrayList<Sport> sports;

    public Sport(String sportName, String sportTime, String createDate) {
        this.sportName = sportName;
        this.sportTime = sportTime;
        this.createDate = createDate;
    }

    public Sport(){ sports = new ArrayList<>(); }

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
}
