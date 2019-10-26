package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.adapter.PeeReportRVAdapter;
import com.jingheng.a105project.model.Pee;
import com.jingheng.a105project.sqlite.DAOPee;

import java.util.ArrayList;

public class PeeReportActivity extends CommonActivity {

    private RecyclerView rv;
    private DAOPee daoPee;

    private ArrayList<Pee> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pee_report);
        addPeeButton(R.id.pee_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        list = new ArrayList<>();
        daoPee = new DAOPee(this);
        if (daoPee.getCount() == 0) {
            Toast.makeText(this, "空的", Toast.LENGTH_LONG);
        }
        list = daoPee.getAll();

        // ui
        rv = findViewById(R.id.rv_pee_report_report);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new PeeReportRVAdapter(this, list));
    }
}
