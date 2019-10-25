package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.helper.SPHelper;
import com.jingheng.a105project.model.Alarm;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.model.Person;
import com.jingheng.a105project.sqlite.DAOAlarm;
import com.jingheng.a105project.sqlite.DAOBlood;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BloodActivity extends CommonActivity implements View.OnClickListener {

    // ui
    private TextView tv_blood_bloodpressure, tv_blood_bloodpressure_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);

        // ui
        tv_blood_bloodpressure = findViewById(R.id.tv_blood_bloodpressure);
        tv_blood_bloodpressure.setOnClickListener(this);
        tv_blood_bloodpressure_2 = findViewById(R.id.tv_blood_bloodpressure_2);
        tv_blood_bloodpressure_2.setOnClickListener(this);
        findViewById(R.id.blood_finish).setOnClickListener(this);
        findViewById(R.id.rv_blood_report).setOnClickListener(this);

        addMainButton(R.id.setting_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void showScrollPicker(final String kind) {
        final Dialog dialog = new Dialog(this);
        //dialog.setTitle("Title");
        dialog.setContentView(R.layout.dialog_picker);
        Button dp_bt_cancel = dialog.findViewById(R.id.dp_bt_cancel);
        Button dp_bt_set = dialog.findViewById(R.id.dp_bt_set);
        final NumberPicker numberPicker = dialog.findViewById(R.id.dp_np);

        if (kind.equals("bloodpressure")) {
            numberPicker.setMinValue(90);
            numberPicker.setMaxValue(145);
            numberPicker.setValue(120); // 設定預設位置
        } else {
            numberPicker.setMinValue(60);
            numberPicker.setMaxValue(100);
            numberPicker.setValue(80); // 設定預設位置
        }
        numberPicker.setWrapSelectorWheel(false); // 是否循環顯示
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 不可編輯

        dp_bt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kind.equals("bloodpressure")) {
                    tv_blood_bloodpressure.setText(String.valueOf(numberPicker.getValue()));
                } else {
                    tv_blood_bloodpressure_2.setText(String.valueOf(numberPicker.getValue()));
                }
                dialog.dismiss();
            }
        });
        dp_bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rv_blood_report:
                startActivity(new Intent(this, BloodReportActivity.class));
                break;
            case R.id.tv_blood_bloodpressure:
                showScrollPicker("bloodpressure");
                break;
            case R.id.tv_blood_bloodpressure_2:
                showScrollPicker("bloodpressure_2");
                break;
            case R.id.blood_finish:
                String bloodpressure = tv_blood_bloodpressure.getText().toString();
                String bloodpressure_2 = tv_blood_bloodpressure_2.getText().toString();

                if (bloodpressure.isEmpty() || bloodpressure_2.isEmpty()) {
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN);
                    Date d = new Date();
                    DAOBlood daoBlood = new DAOBlood(this);
                    //data
                    String date = sdf.format(d);
                    Blood blood = new Blood(bloodpressure, bloodpressure_2, date);
                    daoBlood.insert(blood);
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
        }
    }
}
