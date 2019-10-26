package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.adapter.FoodReportRVAdapter;
import com.jingheng.a105project.model.Food;
import com.jingheng.a105project.sqlite.DAOFood;

import java.util.ArrayList;

public class FoodReportActivity extends CommonActivity {
    private RecyclerView rv;
    private DAOFood daoFood;

    private ArrayList<Food> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_report);
        addFoodInformationButton(R.id.food_report_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        list = new ArrayList<>();
        daoFood = new DAOFood(this);
        if (daoFood.getCount() == 0) {
            Toast.makeText(this, "空的", Toast.LENGTH_LONG);
        }
        list = daoFood.getAll();
        Log.d("dao", "" + list);

        // ui
        rv = findViewById(R.id.rv_food_report);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new FoodReportRVAdapter(this, list));
    }
}
