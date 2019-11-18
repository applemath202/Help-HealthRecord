package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.data.Entry;
import com.jingheng.a105project.R;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.model.Food;
import com.jingheng.a105project.model.Pee;
import com.jingheng.a105project.model.Water;
import com.jingheng.a105project.model.Weight;
import com.jingheng.a105project.sqlite.DAOBlood;
import com.jingheng.a105project.sqlite.DAOFood;
import com.jingheng.a105project.sqlite.DAOPee;
import com.jingheng.a105project.sqlite.DAOWater;
import com.jingheng.a105project.sqlite.DAOWeight;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class WaterActivity extends CommonActivity implements View.OnClickListener {

    // ui
    private TextView tv_water_water;
    private TextView suggest_water;
    DAOPee daoPee;
    private ArrayList<String> dateList;
    private ArrayList<Pee> peeList;
    private ArrayList<Pee> peedao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        // ui
        tv_water_water = findViewById(R.id.tv_water_water);
        tv_water_water.setOnClickListener(this);
        suggest_water =findViewById(R.id.suggest_water);
        findViewById(R.id.water_finish).setOnClickListener(this);
        findViewById(R.id.rv_water_report).setOnClickListener(this);

        addMainButton(R.id.water_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.TAIWAN);
        Date d = new Date();
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+8:00"));
        try {
            cal.setTime(sdf.parse(String.valueOf(d)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DATE, -1);
        dateList.add(sdf.format(cal.getTime()));
        peeList = new ArrayList<>();
        peedao = new ArrayList<>();
        DAOPee daoPee = new DAOPee(this);
        peedao.addAll(daoPee.getAll());
        for (Pee pee : peedao) {
            for (String day : dateList) {
                if (pee.getCreateDate().substring(0, 10).equals(day)) {
                    peeList.add(pee);
                }
            }
        }
//        ArrayList<Entry> pee_values = new ArrayList<>();
        Long h =0l ;

        for (int i = 0; i < peeList.size(); i++) {
            Pee pee = peeList.get(i);
             Long sb= Long.valueOf(pee.getPee());
            h =sb+h;

        }
        h=h+500;
        int ii= new Long(h).intValue();

        if(ii==500){
            suggest_water.setText(" ");
        }
        else {
            String s = Long.toString(h);
            suggest_water.setText(s+"   毫升");

        }

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
                    tv_water_water.setText(String.valueOf(numberPicker.getValue()));
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
            case R.id.rv_water_report:
                startActivity(new Intent(this, WaterReportActivity.class));
                break;
            case R.id.tv_water_water:
                showScrollPicker("water");
                break;

            case R.id.water_finish:
                String waters = tv_water_water.getText().toString();

                if (waters.isEmpty()) {
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN);
                    Date d = new Date();
                    String date = sdf.format(d);
                    DAOWater daoWater = new DAOWater(this);
                    Water water = new Water(waters, date);
                    daoWater.insert(water);
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
        }


    }
}
