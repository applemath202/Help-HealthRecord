package com.jingheng.a105project.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Person {
    private static final Person ourInstance = new Person();
    private String sex;
    private String height;
    private String weight;
    private String birth;

    public Person(String sex, String height, String weight) {
        this.sex = sex;
        this.height = height;
        this.weight = weight;
    }

    public Person(){

    }

    public static Person getInstance() {
        return ourInstance;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public JSONObject getJSONObject() {
        try {
            JSONObject j = new JSONObject();
            j.put("sex", sex);
            j.put("height", height);
            j.put("weight", weight);
            j.put("birth", birth);
            return j;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setJSONObject(JSONObject j) {
        try {
            setSex(j.getString("sex"));
            setHeight(j.getString("height"));
            setWeight(j.getString("weight"));
            setBirth(j.getString("birth"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

