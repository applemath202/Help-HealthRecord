package com.jingheng.a105project.model;


import java.util.ArrayList;

public class Food {
    private int foodId;
    private String food;
    private String hot;
    private String createDate;

    private ArrayList<Food> foods;

    public Food(String food, String hot, String createDate) {
        this.food = food;
        this.hot = hot;
        this.createDate = createDate;
    }

    public Food() {
        foods = new ArrayList<>();
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
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


