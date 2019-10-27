package com.jingheng.a105project.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.jingheng.a105project.R;
import com.jingheng.a105project.activity.BloodReportActivity;
import com.jingheng.a105project.activity.BloodSugarReportActivity;
import com.jingheng.a105project.activity.EditBloodSugarActvity;
import com.jingheng.a105project.activity.EditSportActivity;
import com.jingheng.a105project.activity.SportReportActivity;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.model.BloodSugar;
import com.jingheng.a105project.model.Sport;
import com.jingheng.a105project.sqlite.DAOBlood;
import com.jingheng.a105project.sqlite.DAOBloodSugar;
import com.jingheng.a105project.sqlite.DAOSport;

import java.util.ArrayList;

public class SportReportRVAdapter extends CommonRVAdapter {
    private ArrayList<Sport> list;

    public SportReportRVAdapter(Context context, ArrayList<Sport> list) {
        super(context);
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
            h.sporttime.setText("運動項目:" + sport.getSportTime());
            h.createDate.setText("時間:" + sport.getCreateDate());

            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getContext().startActivity(new Intent(getContext(), EditSportActivity.class)
                            .putExtra("sport", sport));
                }
            });
            h.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showDeleteDialog(sport.getSportID());
                    return false;
                }
            });

        }
    }

    private void showDeleteDialog(final int sportID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("刪除")
                .setMessage("確認是否刪除此筆紀錄？")
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DAOSport daoSport = new DAOSport(getContext());
                        daoSport.delete(sportID);
                        getContext().startActivity(new Intent(getContext(), SportReportActivity.class));
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView sportname;
        TextView sporttime;
        TextView createDate;

        ViewHolder(View v) {
            super(v);
            sportname = v.findViewById(R.id.tv_item_rv_sport_report_list_sportname);
            sporttime = v.findViewById(R.id.tv_item_rv_sport_report_list_min);
            createDate = v.findViewById(R.id.tv_item_rv_sport_report_list_create_date);
        }
    }
}
