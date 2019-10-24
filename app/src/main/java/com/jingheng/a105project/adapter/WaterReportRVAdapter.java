package com.jingheng.a105project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingheng.a105project.R;
import com.jingheng.a105project.model.Food;
import com.jingheng.a105project.model.Water;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WaterReportRVAdapter extends CommonRVAdapter {

    private Context context;
    private ArrayList<Water> list;

    public WaterReportRVAdapter(Context context, ArrayList<Water> list) {
        super(context);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WaterReportRVAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_water_report_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final Water water = list.get(position);
            ViewHolder h = (ViewHolder) holder;
            h.pee.setText("尿液:" + water.getPee());
            h.createDate.setText("時間:" + water.getCreateDate());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView pee;
        TextView createDate;

        ViewHolder(View v) {
            super(v);
            pee = v.findViewById(R.id.tv_item_rv_water_report_list_pee);
            createDate = v.findViewById(R.id.tv_item_rv_water_report_list_create_date);
        }
    }
}
