package com.jingheng.a105project.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jingheng.a105project.R;
import com.jingheng.a105project.adapter.OrderAdapter;
import com.jingheng.a105project.model.Food;
import com.jingheng.a105project.model.Meal;
import com.jingheng.a105project.sqlite.DAOFood;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class FoodInformationActivity extends CommonActivity implements View.OnClickListener {

    private RecyclerView rv;
    private TextView tv_total_price;

    private ArrayList<Meal> orders;
    private ArrayList<Meal> list;
    private String meals;
    private int total_hot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_information);
        addFoodInformationButton(R.id.food_information_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // extra
        list = new ArrayList<>();
        list = getIntent().getParcelableArrayListExtra("orders");
        meals = getIntent().getStringExtra("meals");

        //
        rv = findViewById(R.id.rv_food_information);
        tv_total_price = findViewById(R.id.tv_total_price);
        findViewById(R.id.bt_submit).setOnClickListener(this);
        findViewById(R.id.bt_cancel).setOnClickListener(this);

        orders = new ArrayList<>();
        orders.addAll(list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new OrderAdapter(this, orders));

        int hot = 0;
        for (Meal meal : orders) {
            hot += meal.getHot();
        }
        countTotalPrice(hot);
    }

    public void removeItemToOrder(Meal meal) {
        t("已取消");
        orders.remove(meal);
        rv.getAdapter().notifyDataSetChanged();
        countTotalPrice(meal.getHot() * -1);
    }

    public void countTotalPrice(int price) {
        total_hot += price;
        tv_total_price.setText(String.valueOf(total_hot));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_submit:
                if (orders.size() == 0){
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                }else{
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN);
                    Date d = new Date();
                    //data
                    String date = sdf.format(d);
                    DAOFood daofood = new DAOFood(this);
                    Food food = new Food(meals, String.valueOf(total_hot), date);
                    daofood.insert(food);
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
            case R.id.bt_cancel:
                orders.clear();
                rv.getAdapter().notifyDataSetChanged();
                total_hot = 0;
                tv_total_price.setText(String.valueOf(total_hot));
                break;
        }
    }
}
