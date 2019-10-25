package com.jingheng.a105project.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.model.BloodSugar;
import com.jingheng.a105project.sqlite.DAOBloodSugar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BloodSugarActivity extends CommonActivity implements View.OnClickListener {

    // ui
    private TextView tv_bloodsugar_bloodsugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar);

        // ui
        tv_bloodsugar_bloodsugar = findViewById(R.id.tv_bloodsugar_bloodsugar);
        tv_bloodsugar_bloodsugar.setOnClickListener(this);
        findViewById(R.id.bloodsugar_finish).setOnClickListener(this);
        findViewById(R.id.rv_bloodsugar_report).setOnClickListener(this);

        addMainButton(R.id.bloodsugar_toolbar);
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

        if (kind.equals("bloodsugar")) {
            numberPicker.setMinValue(65);
            numberPicker.setMaxValue(120);
            numberPicker.setValue(100); // 設定預設位置
        }
        numberPicker.setWrapSelectorWheel(false); // 是否循環顯示
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 不可編輯

        dp_bt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kind.equals("bloodsugar")) {
                    tv_bloodsugar_bloodsugar.setText(String.valueOf(numberPicker.getValue()));
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
            case R.id.rv_bloodsugar_report:
                startActivity(new Intent(this, BloodSugarReportActivity.class));
                break;
            case R.id.tv_bloodsugar_bloodsugar:
                showScrollPicker("bloodsugar");
                break;

            case R.id.bloodsugar_finish:
                String bloodsugars = tv_bloodsugar_bloodsugar.getText().toString();

                Log.d("bloodSugar", bloodsugars);

                if (bloodsugars.isEmpty()) {
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN);
                    DAOBloodSugar daoBloodSugar = new DAOBloodSugar(this);
                    Date d = new Date();
                    String date = sdf.format(d);
                    BloodSugar bloodSugar = new BloodSugar(bloodsugars, date);

                    daoBloodSugar.insert(bloodSugar);
                    Log.d("bloodSugar", "insert:" + daoBloodSugar.insert(bloodSugar));
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
        }


    }

}
