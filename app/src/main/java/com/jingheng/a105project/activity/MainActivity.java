package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.jingheng.a105project.adapter.MainGVAdapter;
import com.jingheng.a105project.model.MainGVItem;
import com.jingheng.a105project.R;
import com.jingheng.a105project.model.Person;

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

        Log.d("testPerson", Person.getInstance().toString());
    }

    private void prepareListData() {
        list = new ArrayList<>();
        MainGVItem g1 = new MainGVItem();
        g1.setName("血壓");
        g1.setPicture(R.drawable.blood);
        list.add(g1);

        MainGVItem g2 = new MainGVItem();
        g2.setName("血糖");
        g2.setPicture(R.drawable.bloodsugar);
        list.add(g2);

        MainGVItem g3 = new MainGVItem();
        g3.setName("體重");
        g3.setPicture(R.drawable.weight);
        list.add(g3);

        MainGVItem g4 = new MainGVItem();
        g4.setName("運動");
        g4.setPicture(R.drawable.sport);
        list.add(g4);

        MainGVItem g5 = new MainGVItem();
        g5.setName("水分");
        g5.setPicture(R.drawable.water);
        list.add(g5);

        MainGVItem g6 = new MainGVItem();
        g6.setName("尿液");
        g6.setPicture(R.drawable.pee);
        list.add(g6);

        MainGVItem g7 = new MainGVItem();
        g7.setName("數據分析");
        g7.setPicture(R.drawable.line_graph);
        list.add(g7);

        MainGVItem g8 = new MainGVItem();
        g8.setName("問題解答");
        g8.setPicture(R.drawable.qa);
        list.add(g8);
        MainGVItem g9 = new MainGVItem();
        g9.setName("藥物管理");
        g9.setPicture(R.drawable.drug);
        list.add(g9);
        MainGVItem g10 = new MainGVItem();
        g10.setName("設定");
        g10.setPicture(R.drawable.setting);
        list.add(g10);
        MainGVItem g11 = new MainGVItem();
        g11.setName("通知");
        g11.setPicture(R.drawable.setting);
        list.add(g11);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                Intent i1 = new Intent();
                i1.setClass(MainActivity.this, BloodActivity.class);
                startActivity(i1);
                Toast.makeText(MainActivity.this, "血壓", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Intent i2 = new Intent();
                i2.setClass(MainActivity.this, BloodSugarActivity.class);
                startActivity(i2);
                Toast.makeText(MainActivity.this, "血糖", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Intent i3 = new Intent();
                i3.setClass(MainActivity.this, WeightActivity.class);
                startActivity(i3);
                Toast.makeText(MainActivity.this, "體重", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Intent i4 = new Intent();
                i4.setClass(MainActivity.this, SportActivity.class);
                startActivity(i4);
                Toast.makeText(MainActivity.this, "運動", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Intent i5 = new Intent();
                i5.setClass(MainActivity.this, WaterActivity.class);
                startActivity(i5);
                Toast.makeText(MainActivity.this, "水分", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Intent i6 = new Intent();
                i6.setClass(MainActivity.this, PeeActivity.class);
                startActivity(i6);
                Toast.makeText(MainActivity.this, "尿液", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Intent i7 = new Intent();
                i7.setClass(MainActivity.this, LineGraphActivity.class);
                i7.putExtra("isSetting",false);
                startActivity(i7);
                Toast.makeText(MainActivity.this, "數據分析", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Intent i8 = new Intent();
                i8.setClass(MainActivity.this, QAActivity.class);
                startActivity(i8);
                Toast.makeText(MainActivity.this,
                        "問題解答", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Intent i9 = new Intent();
                i9.setClass(MainActivity.this, DrugActivity.class);
                startActivity(i9);
                Toast.makeText(MainActivity.this,
                        "藥物管理", Toast.LENGTH_SHORT).show();
                break;

            case 9:
                Intent i10 = new Intent();
                i10.setClass(MainActivity.this, SettingActivity.class);
                i10.putExtra("isSetting", false);
                startActivity(i10);
                Toast.makeText(MainActivity.this,
                        "設定", Toast.LENGTH_SHORT).show();
                break;
            case 10:
                Intent i11 = new Intent();
                i11.setClass(MainActivity.this, AlarmActivity.class);
                startActivity(i11);
                Toast.makeText(MainActivity.this,
                        "通知管理", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
