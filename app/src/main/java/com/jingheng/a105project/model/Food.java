package com.jingheng.a105project.model;


import java.util.ArrayList;

public class Food {

    private String food;
    private String createDate;

    private ArrayList<Food> foods;
    public Food(String food, String createDate) {
        this.food = food;
        this.createDate = createDate;
    }

    public Food() {
        foods = new ArrayList<>();
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }
}


