package com.jingheng.a105project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jingheng.a105project.R;
import com.jingheng.a105project.model.Blood;

import java.util.ArrayList;

public class BloodReportRVAdapter extends CommonRVAdapter{

    private Context context;
    private ArrayList<Blood> list;

    public BloodReportRVAdapter(Context context, ArrayList<Blood> list) {
        super(context);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_blood_report_list, parent, false));
}

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final Blood blood = list.get(position);
            ViewHolder h = (ViewHolder) holder;
            h.bloodPressure.setText("血壓:" + blood.getBloodPressure());
            h.bloodSugar.setText("血糖:" + blood.getBloodSugar());
            h.weight.setText("體重:" + blood.getWeight());
            h.createDate.setText("時間:" + blood.getCreateDate());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView bloodPressure;
        TextView bloodSugar;
        TextView weight;
        TextView createDate;

        ViewHolder(View v) {
            super(v);
            bloodPressure = v.findViewById(R.id.tv_item_rv_blood_report_list_blood_pressure);
            bloodSugar = v.findViewById(R.id.tv_item_rv_blood_report_list_blood_sugar);
            weight = v.findViewById(R.id.tv_item_rv_blood_report_list_weight);
            createDate = v.findViewById(R.id.tv_item_rv_blood_report_list_create_date);
        }
    }
}
