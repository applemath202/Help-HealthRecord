package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.adapter.FoodReportRVAdapter;
import com.jingheng.a105project.adapter.WaterReportRVAdapter;
import com.jingheng.a105project.model.Food;
import com.jingheng.a105project.model.Water;
import com.jingheng.a105project.sqlite.DAOFood;
import com.jingheng.a105project.sqlite.DAOWater;

import java.util.ArrayList;

public class WaterReportActivity extends CommonActivity {

    private RecyclerView rv;
    private DAOWater daoWater;

    private ArrayList<Water> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_report);
        addWaterButton(R.id.water_report_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        list = new ArrayList<>();
        daoWater = new DAOWater(this);
        if (daoWater.getCount() == 0) {
            Toast.makeText(this, "空的", Toast.LENGTH_LONG);
        }
        list = daoWater.getAll();
        Log.d("dao", "" + list);

        // ui
        rv = findViewById(R.id.rv_water_report);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new WaterReportRVAdapter(this, list));
    }
}
