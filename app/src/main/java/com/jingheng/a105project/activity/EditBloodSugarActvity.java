package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class EditBloodSugarActvity extends CommonActivity implements View.OnClickListener {

    // ui
    private TextView tv_edit_bloodsugar_bloodsugar;

    private BloodSugar bloodSugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_blood_sugar_actvity);

        // extra
        bloodSugar = getIntent().getParcelableExtra("bloodSugar");

        // ui
        tv_edit_bloodsugar_bloodsugar = findViewById(R.id.tv_edit_bloodsugar_bloodsugar);
        tv_edit_bloodsugar_bloodsugar.setOnClickListener(this);
        tv_edit_bloodsugar_bloodsugar.setText(bloodSugar.getBloodsugar());
        findViewById(R.id.edit_bloodsugar_finish).setOnClickListener(this);

        addMainButton(R.id.edit_bloodsugar_toolbar);
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
                    tv_edit_bloodsugar_bloodsugar.setText(String.valueOf(numberPicker.getValue()));
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
            case R.id.tv_edit_bloodsugar_bloodsugar:
                showScrollPicker("bloodsugar");
                break;

            case R.id.edit_bloodsugar_finish:
                String bloodsugars = tv_edit_bloodsugar_bloodsugar.getText().toString();

                if (bloodsugars.isEmpty()) {
                Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
            } else {
                DAOBloodSugar daoBloodSugar = new DAOBloodSugar(this);
                bloodSugar.setBloodsugar(bloodsugars);
                daoBloodSugar.update(bloodSugar);
                startActivity(new Intent(this, MainActivity.class));
            }
            break;
        }


    }
}
