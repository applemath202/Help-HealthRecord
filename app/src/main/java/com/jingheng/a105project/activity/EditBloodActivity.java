package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.sqlite.DAOBlood;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditBloodActivity extends CommonActivity implements View.OnClickListener{

    private Blood blood;

    // ui
    private TextView tv_blood_bloodpressure, tv_blood_bloodpressure_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_blood);

        // extra
        blood = getIntent().getParcelableExtra("blood");

        // ui
        tv_blood_bloodpressure = findViewById(R.id.tv_edit_blood_bloodpressure);
        tv_blood_bloodpressure.setOnClickListener(this);
        tv_blood_bloodpressure.setText(blood.getBloodPressure());
        tv_blood_bloodpressure_2 = findViewById(R.id.tv_edit_blood_bloodpressure_2);
        tv_blood_bloodpressure_2.setOnClickListener(this);
        tv_blood_bloodpressure_2.setText(blood.getBloodPressure_2());
        findViewById(R.id.edit_blood_finish).setOnClickListener(this);

        addMainButton(R.id.edit_blood_toolbar);
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
            case R.id.tv_edit_blood_bloodpressure:
                showScrollPicker("bloodpressure");
                break;
            case R.id.tv_edit_blood_bloodpressure_2:
                showScrollPicker("bloodpressure_2");
                break;
            case R.id.edit_blood_finish:
                String bloodpressure = tv_blood_bloodpressure.getText().toString();
                String bloodpressure_2 = tv_blood_bloodpressure_2.getText().toString();

                if (bloodpressure.isEmpty() || bloodpressure_2.isEmpty()) {
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                } else {
                    DAOBlood daoBlood = new DAOBlood(this);
                    blood.setBloodPressure(bloodpressure);
                    blood.setBloodPressure_2(bloodpressure_2);
                    daoBlood.update(blood);
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
        }
    }
}
