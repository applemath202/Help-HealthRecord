package com.jingheng.a105project.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.jingheng.a105project.R;
import com.jingheng.a105project.adapter.QAAdapter;

import java.util.ArrayList;

public class QAActivity extends CommonActivity {

    private String selectItem;
    private RecyclerView rv;
    private ArrayList<String> list;
    private ArrayList<String> q1List;
    private ArrayList<String> q2List;
    private ArrayList<String> q3List;
    private ArrayList<String> q4List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);

        MaterialSpinner spinner = findViewById(R.id.qa_spinner);
        addMainButton(R.id.qa_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //init
        list = new ArrayList<>();
        q1List = new ArrayList<>();
        q2List = new ArrayList<>();
        q3List = new ArrayList<>();
        q4List = new ArrayList<>();

        setListContent();

        selectItem = "護腎衛教";
        spinner.setItems("護腎衛教", "腹膜透析", "血液透析", "腎臟移植");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                selectItem = item;
                changeQA(selectItem);
            }
        });
        list.addAll(q1List);
        rv = findViewById(R.id.qa_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new QAAdapter(this, list));
    }

    private void changeQA(String selectItem){
        list.clear();
        switch (selectItem) {
            case "護腎衛教":
                list.addAll(q1List);
                break;
            case "腹膜透析":
                list.addAll(q2List);
                break;
            case "血液透析":
                list.addAll(q3List);
                break;
            case "腎臟移植":
                list.addAll(q4List);
                break;
        }
        assert rv.getAdapter() != null;
        rv.getAdapter().notifyDataSetChanged();
    }

    private void setListContent(){
        q1List.add("淺談腎臟");
        q2List.add("淺談腹膜透析");
        q3List.add("蛋白質要多少才夠?");
        q4List.add("什麼是存活率?");
    }
}
