package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.adapter.BloodReportRVAdapter;
import com.jingheng.a105project.adapter.BloodSugarReportRVAdapter;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.model.BloodSugar;
import com.jingheng.a105project.sqlite.DAOBlood;
import com.jingheng.a105project.sqlite.DAOBloodSugar;

import java.util.ArrayList;

public class BloodSugarReportActivity extends CommonActivity {

    private RecyclerView rv;
    private DAOBloodSugar daoBloodSugar;

    private ArrayList<BloodSugar> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar_report);
        addBloodSugarButton(R.id.bloodsugar_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        list = new ArrayList<>();
        daoBloodSugar = new DAOBloodSugar(this);
        if (daoBloodSugar.getCount() == 0) {
            Toast.makeText(this, "空的", Toast.LENGTH_LONG);
        }
        list = daoBloodSugar.getAll();
        Log.d("dao", "" + list);

        // ui
        rv = findViewById(R.id.rv_bloodSugar_report);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new BloodSugarReportRVAdapter(this, list));
    }
}
