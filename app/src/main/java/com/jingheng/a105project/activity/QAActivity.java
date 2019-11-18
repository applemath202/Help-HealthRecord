package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jingheng.a105project.R;

public class QAActivity extends CommonActivity implements View.OnClickListener {
    private String selectItem;

    private TextView tv_QA1_1;
    private LinearLayout QA1_ll;
    private LinearLayout QA2_ll;
    private LinearLayout QA3_ll;
    private LinearLayout QA4_ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);

        tv_QA1_1 = findViewById(R.id.QA1_1);
        tv_QA1_1.setOnClickListener(this);
        QA1_ll = findViewById(R.id.QA1_ll);
        QA2_ll = findViewById(R.id.QA2_ll);
        QA3_ll = findViewById(R.id.QA3_ll);
        QA4_ll = findViewById(R.id.QA4_ll);


        MaterialSpinner spinner = findViewById(R.id.qa_spinner);
        addMainButton(R.id.qa_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //init
        selectItem = "護腎衛教";
        spinner.setItems("護腎衛教", "腹膜透析", "血液透析", "腎臟移植");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                selectItem = item;
            }
        });
        switch (selectItem) {
            case "護腎衛教":
                QA1_ll.setVisibility(View.VISIBLE);
                QA2_ll.setVisibility(View.INVISIBLE);
                QA3_ll.setVisibility(View.INVISIBLE);
                QA4_ll.setVisibility(View.INVISIBLE);
                break;
            case "腹膜透析":
                QA1_ll.setVisibility(View.INVISIBLE);
                QA2_ll.setVisibility(View.VISIBLE);
                QA3_ll.setVisibility(View.INVISIBLE);
                QA4_ll.setVisibility(View.INVISIBLE);
                break;
            case "血液透析":
                QA1_ll.setVisibility(View.INVISIBLE);
                QA2_ll.setVisibility(View.INVISIBLE);
                QA3_ll.setVisibility(View.VISIBLE);
                QA4_ll.setVisibility(View.INVISIBLE);
                break;
            case "腎臟移植":
                QA1_ll.setVisibility(View.INVISIBLE);
                QA2_ll.setVisibility(View.INVISIBLE);
                QA3_ll.setVisibility(View.INVISIBLE);
                QA4_ll.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.QA1_1:
                setContentView(R.layout.qa1_1);
                Button buttonBack = findViewById(R.id.back);
                buttonBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(QAActivity.this, QAActivity.class);
                        startActivity(intent);
                    }
                });

//                    case R.id.QA1_2:
//                        setContentView(R.layout.qa1_2);
//                        break;
//                    case R.id.QA1_3:
//                        setContentView(R.layout.qa1_3);
//                        break;
//                    case R.id.QA1_4:
//                        setContentView(R.layout.qa1_4);
//                        break;
//                    case R.id.QA1_5:
//                        setContentView(R.layout.qa1_5);
//                        break;
//                    case R.id.QA1_6:
//                        setContentView(R.layout.qa1_6);
//                        break;
//                    case R.id.QA1_7:
//                        setContentView(R.layout.qa1_7);
//                        break;
//                    case R.id.QA1_8:
//                        setContentView(R.layout.qa1_8);
//                    case R.id.QA1_9:
//                        setContentView(R.layout.qa1_9);
//                    case R.id.QA1_10:
//                        setContentView(R.layout.qa1_10);
                break;
        }


    }
}
