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
import com.jingheng.a105project.model.BloodSugar;
import com.jingheng.a105project.model.Water;
import com.jingheng.a105project.sqlite.DAOBloodSugar;
import com.jingheng.a105project.sqlite.DAOWater;

public class EditWaterActivity extends CommonActivity implements View.OnClickListener {

    // ui
    private TextView tv_edit_water_water;

    private Water water;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_water);
        // extra
        water = getIntent().getParcelableExtra("water");

        // ui
        tv_edit_water_water = findViewById(R.id.tv_edit_water_water);
        tv_edit_water_water.setOnClickListener(this);
        tv_edit_water_water.setText(water.getWater());
        findViewById(R.id.edit_water_finish).setOnClickListener(this);

        addMainButton(R.id.edit_water_toolbar);
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
        if (kind.equals("water")) {
            numberPicker.setMinValue(30);
            numberPicker.setMaxValue(500);
            numberPicker.setValue(150); // 設定預設位置
        }
        numberPicker.setWrapSelectorWheel(false); // 是否循環顯示
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 不可編輯


        dp_bt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kind.equals("water")) {
                    tv_edit_water_water.setText(String.valueOf(numberPicker.getValue()));
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
            case R.id.tv_edit_water_water:
                showScrollPicker("water");
                break;

            case R.id.edit_water_finish:
                String waters = tv_edit_water_water.getText().toString();

                if (waters.isEmpty()) {
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                } else {
                    DAOWater daoWater = new DAOWater(this);
                    water.setWater(waters);
                    daoWater.update(water);
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
        }


    }
}
