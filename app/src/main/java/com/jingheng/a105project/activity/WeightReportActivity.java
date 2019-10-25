package com.jingheng.a105project.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.adapter.WeightReportRVAdapter;
import com.jingheng.a105project.model.Weight;
import com.jingheng.a105project.sqlite.DAOWeight;

import java.util.ArrayList;

public class WeightReportActivity extends CommonActivity {

    private RecyclerView rv;
    private ArrayList<Weight> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_report);
        addWeightButton(R.id.weight_report_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        list = new ArrayList<>();
        DAOWeight daoWeight = new DAOWeight(this);
        if (daoWeight.getCount() == 0) {
            Toast.makeText(this, "空的", Toast.LENGTH_LONG);
        }
        list = daoWeight.getAll();
        Log.d("dao", "" + list);

        // ui
        rv = findViewById(R.id.rv_weight_report_report);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new WeightReportRVAdapter(this,list));
    }
}
