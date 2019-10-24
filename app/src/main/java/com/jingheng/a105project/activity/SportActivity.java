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
import com.jingheng.a105project.model.Sport;
import com.jingheng.a105project.sqlite.DAOBlood;
import com.jingheng.a105project.sqlite.DAOSport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SportActivity extends AppCompatActivity implements View.OnClickListener {

    // ui
    private EditText et_sport_name, et_sport_time;

    //data
    private String date;
    private DAOSport daoSport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

        // ui
        et_sport_name = findViewById(R.id.et_activity_sport_sport_name);
        et_sport_time = findViewById(R.id.et_activity_sport_sport_time);

        findViewById(R.id.bt_activity_sport_save).setOnClickListener(this);
        findViewById(R.id.iv_activity_sport_report).setOnClickListener(this);

        // data
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN);
        Date d = new Date();
        date = sdf.format(d);
        daoSport = new DAOSport(this);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        //建立一個ArrayAdapter物件，並放置下拉選單的內容
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.myspinner, new String[]{"慢走", "室內腳踏車", "有氧舞蹈", "羽毛球", "排球","慢跑","爬樓梯"});
        //設定下拉選單的樣式
        adapter.setDropDownViewResource(R.layout.myspinner);
        spinner.setAdapter(adapter);
        //設定項目被選取之後的動作
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(SportActivity.this, "您選擇" + adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(SportActivity.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_activity_sport_save:
                Sport sport = new Sport(
                        et_sport_name.getText().toString(),
                        et_sport_time.getText().toString(),
                        date
                );
                daoSport.insert(sport);
                Toast.makeText(this, "儲存成功", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_activity_sport_report:
                startActivity(new Intent(this, SportReportActivity.class));
                break;
        }
    }
}
