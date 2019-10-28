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
import com.jingheng.a105project.model.Sport;
import com.jingheng.a105project.sqlite.DAOBlood;
import com.jingheng.a105project.sqlite.DAOSport;

public class EditSportActivity extends CommonActivity implements View.OnClickListener {
    private Sport sport;

    // ui
    private TextView tv_edit__sport_sportname, tv_edit__sport_min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sport);
        // extra
        sport = getIntent().getParcelableExtra("sport");

        // ui
        tv_edit__sport_sportname = findViewById(R.id.tv_edit__sport_sportname);
        tv_edit__sport_sportname.setOnClickListener(this);
        tv_edit__sport_sportname.setText(sport.getSportName());
        tv_edit__sport_min = findViewById(R.id.tv_edit__sport_min);
        tv_edit__sport_min.setOnClickListener(this);
        tv_edit__sport_min.setText(sport.getSportTime());
        findViewById(R.id.edit_sport_finish).setOnClickListener(this);

        addMainButton(R.id.edit_sport_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    private void showScrollPicker(final String kind) {
        final Dialog dialog = new Dialog(this);
        //dialog.setTitle("Title");
        dialog.setContentView(R.layout.dialog_picker);
        Button dp_bt_cancel = dialog.findViewById(R.id.dp_bt_cancel);
        Button dp_bt_set = dialog.findViewById(R.id.dp_bt_set);
        final String[] sportname = getResources().getStringArray(R.array.sportname);
        final NumberPicker numberPicker = dialog.findViewById(R.id.dp_np);

        if (kind.equals("sportmin")) {
            numberPicker.setMinValue(1);
            numberPicker.setMaxValue(60);
            numberPicker.setValue(15); // 設定預設位置
        } else {
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(sportname.length - 1);
            numberPicker.setDisplayedValues(sportname);
            numberPicker.setValue(0); // 設定預設位置
        }
        numberPicker.setWrapSelectorWheel(false); // 是否循環顯示
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 不可編輯

        dp_bt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kind.equals("sportname")) {
                    tv_edit__sport_sportname.setText(String.valueOf(sportname[numberPicker.getValue()]));
                } else {
                    tv_edit__sport_min.setText(String.valueOf(numberPicker.getValue()));
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
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.tv_edit__sport_sportname:
                showScrollPicker("sportname");
                break;
            case R.id.tv_edit__sport_min:
                showScrollPicker("sportmin");
                break;
            case R.id.edit_sport_finish:
                String sportname = tv_edit__sport_sportname.getText().toString();
                String sportmin = tv_edit__sport_min.getText().toString();

                if (sportname.isEmpty() || sportmin.isEmpty()) {
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                } else {
                    DAOSport daoSport = new DAOSport(this);
                    sport.setSportName(sportname);
                    sport.setSportTime(sportmin);
                    daoSport.update(sport);
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
        }
    }

}

