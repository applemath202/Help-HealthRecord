package com.jingheng.a105project.model;

import java.util.ArrayList;

public class Blood {
    private String bloodPressure;
    private String bloodSugar;
    private String weight;
    private String createDate;

    private ArrayList<Blood> bloods;

    public Blood(String bloodPressure, String bloodSugar, String weight, String createDate){
        this.bloodPressure = bloodPressure;
        this.bloodSugar = bloodSugar;
        this.weight = weight;
        this.createDate = createDate;
    }

    public Blood(){
        bloods = new ArrayList<>();
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
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

    public ArrayList<Blood> getBloods() {
        return bloods;
    }

    public void setBloods(ArrayList<Blood> bloods) {
        this.bloods = bloods;
    }
}
