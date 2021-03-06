package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.adapter.BloodReportRVAdapter;
import com.jingheng.a105project.adapter.SportReportRVAdapter;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.model.Sport;
import com.jingheng.a105project.sqlite.DAOBlood;
import com.jingheng.a105project.sqlite.DAOSport;

import java.util.ArrayList;

public class SportReportActivity extends CommonActivity {

    private RecyclerView rv;
    private DAOSport daoSport;

    private ArrayList<Sport> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_report);
        addsportButton(R.id.sport_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        list = new ArrayList<>();
        daoSport = new DAOSport(this);
        if (daoSport.getCount() == 0) {
            Toast.makeText(this, "空的", Toast.LENGTH_LONG);
        }
        list = daoSport.getAll();
        Log.d("dao", "" + list);

        // ui
        rv = findViewById(R.id.rv_sport_report_report);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new SportReportRVAdapter(this, list));
    }
}
