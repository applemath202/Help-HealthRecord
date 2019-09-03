package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
