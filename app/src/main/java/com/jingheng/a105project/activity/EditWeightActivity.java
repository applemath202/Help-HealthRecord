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
import com.jingheng.a105project.model.Weight;
import com.jingheng.a105project.sqlite.DAOBloodSugar;
import com.jingheng.a105project.sqlite.DAOWeight;

public class EditWeightActivity extends CommonActivity implements View.OnClickListener {
    // ui
    private TextView tv_edit_weight_weight;

    private Weight weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_weight);

        // extra
        weight = getIntent().getParcelableExtra("weight");

        // ui
        tv_edit_weight_weight = findViewById(R.id.tv_edit_weight_weight);
        tv_edit_weight_weight.setOnClickListener(this);
        tv_edit_weight_weight.setText(weight.getWeight());
        findViewById(R.id.edit_weight_finish).setOnClickListener(this);

        addMainButton(R.id.edit_weight_toolbar);
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
                    tv_edit_weight_weight.setText(String.valueOf(numberPicker.getValue()));
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
            case R.id.tv_edit_weight_weight:
                showScrollPicker("weight");
                break;
            case R.id.edit_weight_finish:
                String weights = tv_edit_weight_weight.getText().toString();

                if (weights.isEmpty()) {
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                } else {
                    DAOWeight daoWeight = new DAOWeight(this);
                    weight.setWeight(weights);
                    daoWeight.update(weight);
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
        }
    }
}

