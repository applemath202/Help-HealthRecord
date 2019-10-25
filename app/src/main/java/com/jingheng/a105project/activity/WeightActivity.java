package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.model.BloodSugar;
import com.jingheng.a105project.model.Weight;
import com.jingheng.a105project.sqlite.DAOBloodSugar;
import com.jingheng.a105project.sqlite.DAOWeight;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeightActivity extends CommonActivity implements View.OnClickListener {

    // ui
    private TextView tv_weight_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        // ui
        tv_weight_weight = findViewById(R.id.tv_weight_weight);
        tv_weight_weight.setOnClickListener(this);
        findViewById(R.id.weight_finish).setOnClickListener(this);
        findViewById(R.id.rv_weight_report).setOnClickListener(this);

        addMainButton(R.id.weight_toolbar);
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

        if (kind.equals("weight")) {
            numberPicker.setMinValue(40);
            numberPicker.setMaxValue(120);
            numberPicker.setValue(60); // 設定預設位置
        }
        numberPicker.setWrapSelectorWheel(false); // 是否循環顯示
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 不可編輯

        dp_bt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kind.equals("weight")) {
                    tv_weight_weight.setText(String.valueOf(numberPicker.getValue()));
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
            case R.id.rv_weight_report:
                startActivity(new Intent(this, WeightReportActivity.class));
                break;
            case R.id.tv_weight_weight:
                showScrollPicker("weight");
                break;

            case R.id.weight_finish:
                String weights = tv_weight_weight.getText().toString();

                if (weights.isEmpty()) {
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN);
                    Date d = new Date();
                    //data
                    String date = sdf.format(d);
                    DAOWeight daoWeight = new DAOWeight(this);
                    Weight weight = new Weight(weights, date);
                    daoWeight.insert(weight);
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
        }
    }

}
