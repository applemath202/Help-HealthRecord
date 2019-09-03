package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.adapter.BloodReportRVAdapter;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.sqlite.DAOBlood;

import java.util.ArrayList;

public class BloodReportActivity extends AppCompatActivity {

    private RecyclerView rv;
    private DAOBlood daoBlood;

    private ArrayList<Blood> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        list = new ArrayList<>();
        daoBlood = new DAOBlood(this);
        if (daoBlood.getCount() == 0) {
            Toast.makeText(this,"空的",Toast.LENGTH_LONG);
        }
        list = daoBlood.getAll();
        Log.d("dao","" + list);

        // ui
        rv = findViewById(R.id.rv_blood_report);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new BloodReportRVAdapter(this,list));
    }
}
