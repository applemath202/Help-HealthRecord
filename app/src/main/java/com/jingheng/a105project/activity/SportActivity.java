package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.model.Sport;
import com.jingheng.a105project.sqlite.DAOBlood;
import com.jingheng.a105project.sqlite.DAOSport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SportActivity extends CommonActivity implements View.OnClickListener {

    // ui
    private TextView tv_sport_sportname, tv_sport_min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

        // ui
        tv_sport_sportname = findViewById(R.id.tv_sport_sportname);
        tv_sport_sportname.setOnClickListener(this);
        tv_sport_min = findViewById(R.id.tv_sport_min);
        tv_sport_min.setOnClickListener(this);
        findViewById(R.id.sport_finish).setOnClickListener(this);
        findViewById(R.id.rv_sport_report).setOnClickListener(this);

        addMainButton(R.id.sport_toolbar);
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
        final String[] sportname = getResources().getStringArray(R.array.sportname);

        if (kind.equals("min")) {
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
                    tv_sport_sportname.setText(String.valueOf(sportname[numberPicker.getValue()]));
                } else {
                    tv_sport_min.setText(String.valueOf(numberPicker.getValue()));
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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rv_sport_report:
                startActivity(new Intent(this, SportReportActivity.class));
                break;
            case R.id.tv_sport_sportname:
                showScrollPicker("sportname");
                break;
            case R.id.tv_sport_min:
                showScrollPicker("min");
                break;
            case R.id.sport_finish:
                String sportname = tv_sport_sportname.getText().toString();
                String min = tv_sport_min.getText().toString();

                if (sportname.isEmpty() || min.isEmpty()) {
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN);
                    Date d = new Date();
                    DAOSport daoSport = new DAOSport(this);
                    //data
                    String date = sdf.format(d);
                    Sport sport = new Sport(sportname, min, date);
                    daoSport.insert(sport);
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
        }
    }
}
