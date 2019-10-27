package com.jingheng.a105project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jingheng.a105project.R;
import com.jingheng.a105project.adapter.DrugReportRVAdapter;
import com.jingheng.a105project.model.Drug;
import com.jingheng.a105project.sqlite.DAOBlood;
import com.jingheng.a105project.sqlite.DAODrug;

import java.util.ArrayList;

import javax.xml.transform.dom.DOMResult;

public class DrugReportActivity extends CommonActivity {

    private RecyclerView rv;
    private DAODrug daoDrug;

    private ArrayList<Drug> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_report);
        adddrugButton(R.id.drug_report_toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        list = new ArrayList<>();
        daoDrug = new DAODrug(this);
        if (daoDrug.getCount() == 0) {
            Toast.makeText(this, "空的", Toast.LENGTH_LONG);
        }
        list = daoDrug.getAll();
        Log.d("dao", "" + list);

        // ui
        rv = findViewById(R.id.rv_drug_report_report);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new DrugReportRVAdapter(this, list));
    }
}
