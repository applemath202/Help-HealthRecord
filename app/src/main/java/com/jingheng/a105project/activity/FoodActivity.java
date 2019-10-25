package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.model.Food;
import com.jingheng.a105project.sqlite.DAOBlood;
import com.jingheng.a105project.sqlite.DAOFood;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {

    // ui
    private EditText et_food;

    //data
    private String date;
    private DAOFood daoFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        // ui
        et_food = findViewById(R.id.et_activity_food_food);
        findViewById(R.id.bt_activity_food_save).setOnClickListener(this);
        findViewById(R.id.iv_activity_food_report).setOnClickListener(this);

        // data
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN);
        Date d = new Date();
        date = sdf.format(d);
        daoFood = new DAOFood(this);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        //建立一個ArrayAdapter物件，並放置下拉選單的內容
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.myspinner, new String[]{"無", "饅頭", "飯", "麵", "地瓜"});
        //設定下拉選單的樣式
        adapter.setDropDownViewResource(R.layout.myspinner);
        spinner.setAdapter(adapter);
        //設定項目被選取之後的動作
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(FoodActivity.this, "您選擇" + adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(FoodActivity.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }

        });
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        //建立一個ArrayAdapter物件，並放置下拉選單的內容
        ArrayAdapter adapter2 = new ArrayAdapter(this, R.layout.myspinner, new String[]{"無", "豬肉", "雞肉", "鴨肉", "魚肉", "鵝肉", "牛肉", "羊肉"});
//        //設定下拉選單的樣式
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        //設定項目被選取之後的動作
        spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView2, View view, int position, long id) {
                Toast.makeText(FoodActivity.this, "您選擇" + adapterView2.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(FoodActivity.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }

        });


        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        //建立一個ArrayAdapter物件，並放置下拉選單的內容
        ArrayAdapter adapter3 = new ArrayAdapter(this, R.layout.myspinner, new String[]{"無", "地瓜葉", "高麗菜", "菠菜", "花椰菜", "芹菜", "大陸妹", "大白菜"});
//        //設定下拉選單的樣式
//        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        //設定項目被選取之後的動作
        spinner3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView3, View view, int position, long id) {
                Toast.makeText(FoodActivity.this, "您選擇" + adapterView3.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(FoodActivity.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }

        });

        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        //建立一個ArrayAdapter物件，並放置下拉選單的內容
        ArrayAdapter adapter4 = new ArrayAdapter(this, R.layout.myspinner, new String[]{"無", "蘋果", "水梨", "香蕉", "芭樂", "桃子"});
//        //設定下拉選單的樣式
//        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        //設定項目被選取之後的動作
        spinner4.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView4, View view, int position, long id) {
                Toast.makeText(FoodActivity.this, "您選擇" + adapterView4.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(FoodActivity.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }

        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_activity_food_save:
                if (et_food.getText().toString().isEmpty()) {
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                    return;
                }
                Food food = new Food(
                        et_food.getText().toString(),

                        date
                );
                daoFood.insert(food);
                et_food.setText("");
                Toast.makeText(this, "儲存成功", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_activity_food_report:
                startActivity(new Intent(this, FoodReportActivity.class));
                break;
        }
    }

}

