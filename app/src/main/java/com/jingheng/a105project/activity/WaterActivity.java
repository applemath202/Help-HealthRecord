package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.model.Food;
import com.jingheng.a105project.model.Water;
import com.jingheng.a105project.sqlite.DAOFood;
import com.jingheng.a105project.sqlite.DAOWater;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WaterActivity extends AppCompatActivity implements View.OnClickListener{

    // ui
    private TextView tv_neow;
    private EditText et_pee;

    //data
    private String date;
    private ArrayList<Water> list;
    private DAOWater daoWater;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_water);

        // ui
        tv_neow = findViewById(R.id.tv_activity_water_neow);
        et_pee = findViewById(R.id.et_activity_water_pee);
        findViewById(R.id.bt_activity_water_save).setOnClickListener(this);
        findViewById(R.id.iv_activity_water_report).setOnClickListener(this);

        // data
        list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN);
        Date d = new Date();
        date = sdf.format(d);
        daoWater = new DAOWater(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_activity_water_save:
                if(et_pee.getText().toString().isEmpty()){
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                    return;
                }
                Water water = new Water(
                        et_pee.getText().toString(),
                        date
                );
                daoWater.insert(water);
                int neow = 0;
                list = daoWater.getAll();
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getCreateDate().substring(0,10).equals(date.substring(0,10))){
                        neow += Integer.valueOf(list.get(i).getPee());
                    }
                }
                neow=neow+500;
                tv_neow.setText(String.valueOf(neow));
                et_pee.setText("");
                Toast.makeText(this, "儲存成功", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_activity_water_report:
                startActivity(new Intent(this, WaterReportActivity.class));
                break;
        }

    }
}
