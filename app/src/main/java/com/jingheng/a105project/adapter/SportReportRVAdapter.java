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
import com.jingheng.a105project.model.Sport;

import java.util.ArrayList;

public class SportReportRVAdapter extends CommonRVAdapter{
    private Context context;
    private ArrayList<Sport> list;

    public SportReportRVAdapter(Context context, ArrayList<Sport> list) {
        super(context);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_sport_report_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final Sport sport = list.get(position);
            ViewHolder h = (ViewHolder) holder;
            h.sportname.setText("運動項目:" + sport.getSportName());
            h.sporttime.setText("運動時間:" + sport.getSportTime());
            h.createDate.setText("時間:" + sport.getCreateDate());
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView sportname;
        TextView sporttime;
        TextView createDate;

        ViewHolder(View v) {
            super(v);
            sportname = v.findViewById(R.id.tv_item_rv_sport_report_list_sport_name);
            sporttime = v.findViewById(R.id.tv_item_rv_sport_report_list_sport_time);
            createDate = v.findViewById(R.id.tv_item_rv_sport_report_list_create_date);
        }
    }
}
