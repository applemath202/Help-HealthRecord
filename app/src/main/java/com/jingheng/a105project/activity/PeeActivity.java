package com.jingheng.a105project.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.model.Pee;
import com.jingheng.a105project.sqlite.DAOPee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PeeActivity extends CommonActivity implements View.OnClickListener {

    // ui
    private TextView tv_pee_pee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pee);
        // ui
        tv_pee_pee = findViewById(R.id.tv_pee_pee);
        tv_pee_pee.setOnClickListener(this);
        findViewById(R.id.pee_finish).setOnClickListener(this);
        findViewById(R.id.rv_pee_report).setOnClickListener(this);

        addBackButton(R.id.pee_toolbar);
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

        if (kind.equals("pee")) {
            numberPicker.setMinValue(30);
            numberPicker.setMaxValue(500);
            numberPicker.setValue(150); // 設定預設位置
        }
        numberPicker.setWrapSelectorWheel(false); // 是否循環顯示
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 不可編輯

        dp_bt_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kind.equals("pee")) {
                    tv_pee_pee.setText(String.valueOf(numberPicker.getValue()));
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
            case R.id.rv_pee_report:
                startActivity(new Intent(this, PeeReportActivity.class));
                break;
            case R.id.tv_pee_pee:
                showScrollPicker("pee");
                break;
            case R.id.pee_finish:
                String pees = tv_pee_pee.getText().toString();

                if (pees.isEmpty()) {
                    Toast.makeText(this, "不能是空的", Toast.LENGTH_SHORT).show();
                } else {
                    Pee pee = new Pee();
                    pee.setPee(pees);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN);
                    Date d = new Date();
                    String date = sdf.format(d);
                    DAOPee daoPee = new DAOPee(this);
                    pee.setCreateDate(date);
                    daoPee.insert(pee);
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
        }
    }
}
