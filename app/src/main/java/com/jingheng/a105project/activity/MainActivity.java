package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.jingheng.a105project.adapter.MainGVAdapter;
import com.jingheng.a105project.model.MainGVItem;
import com.jingheng.a105project.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    // ui
    private ArrayList<MainGVItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ui
        prepareListData();
        GridView gridView = findViewById(R.id.am_gv);
        gridView.setAdapter(new MainGVAdapter(this, list));
        gridView.setOnItemClickListener(this);
    }

    private void prepareListData() {
        list = new ArrayList<>();
        MainGVItem g1 = new MainGVItem();
        g1.setName("身體狀況管理");
        g1.setPicture(R.drawable.blood);
        list.add(g1);

        MainGVItem g2 = new MainGVItem();
        g2.setName("飲食管理");
        g2.setPicture(R.drawable.food);
        list.add(g2);

        MainGVItem g3 = new MainGVItem();
        g3.setName("運動與睡眠管理");
        g3.setPicture(R.drawable.sport);
        list.add(g3);

        MainGVItem g4 = new MainGVItem();
        g4.setName("水分控制");
        g4.setPicture(R.drawable.water);
        list.add(g4);

        MainGVItem g5 = new MainGVItem();
        g5.setName("數據分析");
        g5.setPicture(R.drawable.line_graph);
        list.add(g5);

        MainGVItem g6 = new MainGVItem();
        g6.setName("問題解答");
        g6.setPicture(R.drawable.qa);
        list.add(g6);

        MainGVItem g7 = new MainGVItem();
        g7.setName("設定");
        g7.setPicture(R.drawable.bloodpressure);
        list.add(g7);

        MainGVItem g8 = new MainGVItem();
        g8.setName("藥物管理");
        g8.setPicture(R.drawable.drug);
        list.add(g8);
        MainGVItem g9 = new MainGVItem();
        g9.setName("通知");
        g9.setPicture(R.drawable.drug);
        list.add(g9);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                Intent i1 = new Intent();
                i1.setClass(MainActivity.this, BloodActivity.class);
                startActivity(i1);
                Toast.makeText(MainActivity.this, "身體狀況管理", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Intent i2 = new Intent();
                i2.setClass(MainActivity.this, FoodActivity.class);
                startActivity(i2);
                Toast.makeText(MainActivity.this, "飲食管理", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Intent i3 = new Intent();
                i3.setClass(MainActivity.this, SportActivity.class);
                startActivity(i3);
                Toast.makeText(MainActivity.this, "運動與睡眠管理", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Intent i4 = new Intent();
                i4.setClass(MainActivity.this, WaterActivity.class);
                startActivity(i4);
                Toast.makeText(MainActivity.this, "水分管理", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Intent i5 = new Intent();
                i5.setClass(MainActivity.this, LineGraphActivity.class);
                startActivity(i5);
                Toast.makeText(MainActivity.this, "數據追蹤", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Intent i6 = new Intent();
                i6.setClass(MainActivity.this, QAActivity.class);
                startActivity(i6);
                Toast.makeText(MainActivity.this, "問題解答", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Intent i7 = new Intent();
                i7.setClass(MainActivity.this, SettingActivity.class);
                startActivity(i7);
                Toast.makeText(MainActivity.this, "設定", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Intent i8 = new Intent();
                i8.setClass(MainActivity.this, DrugActivity.class);
                startActivity(i8);
                Toast.makeText(MainActivity.this,
                        "藥物管理", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Intent i9 = new Intent();
                i9.setClass(MainActivity.this, Main2Activity.class);
                startActivity(i9);
                Toast.makeText(MainActivity.this,
                        "通知管理", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
