package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.sqlite.DAOBlood;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BloodActivity extends AppCompatActivity implements View.OnClickListener {

    // ui
    private EditText et_blood_pressure, et_blood_sugar, et_weight;

    //data
    private String date;
    private DAOBlood daoBlood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);

        // ui
        et_blood_pressure = findViewById(R.id.et_activity_blood_blood_pressure);
        et_blood_sugar = findViewById(R.id.et_activity_blood_blood_sugar);
        et_weight = findViewById(R.id.et_activity_blood_weight);
        findViewById(R.id.bt_activity_blood_save).setOnClickListener(this);
        findViewById(R.id.iv_activity_blood_report).setOnClickListener(this);

        // data
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN);
        Date d = new Date();
        date = sdf.format(d);
        daoBlood = new DAOBlood(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_activity_blood_save:
                if(et_blood_pressure.getText().toString().isEmpty()||
                        et_blood_sugar.getText().toString().isEmpty()||et_weight.getText().toString().isEmpty()){
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                    return;
                }
                Blood blood = new Blood(
                        et_blood_pressure.getText().toString(),
                        et_blood_sugar.getText().toString(),
                        et_weight.getText().toString(),
                        date
                );
                daoBlood.insert(blood);
                Toast.makeText(this, "儲存成功", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_activity_blood_report:
                startActivity(new Intent(this, BloodReportActivity.class));
                break;
        }
    }
}
