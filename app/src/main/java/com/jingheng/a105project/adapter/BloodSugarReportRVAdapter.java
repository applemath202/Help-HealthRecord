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
import com.jingheng.a105project.activity.BloodSugarReportActivity;
import com.jingheng.a105project.activity.EditBloodSugarActvity;
import com.jingheng.a105project.model.BloodSugar;
import com.jingheng.a105project.sqlite.DAOBloodSugar;

import java.util.ArrayList;

public class BloodSugarReportRVAdapter extends CommonRVAdapter {

    private ArrayList<BloodSugar> list;

    public BloodSugarReportRVAdapter(Context context, ArrayList<BloodSugar> list) {
        super(context);
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_blood_sugar_report_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final BloodSugar bloodSugar = list.get(position);
            ViewHolder h = (ViewHolder) holder;
            h.bloodSugar.setText("血糖:" + bloodSugar.getBloodsugar());
            h.createDate.setText("時間:" + bloodSugar.getCreateDate());

            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getContext().startActivity(new Intent(getContext(), EditBloodSugarActvity.class)
                            .putExtra("bloodSugar",bloodSugar));
                }
            });

            h.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showDeleteDialog(bloodSugar.getBloodsugarId());
                    return false;
                }
            });
        }

    }
    private void showDeleteDialog(final int bloodsugarId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("刪除")
                .setMessage("確認是否刪除此筆紀錄？")
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DAOBloodSugar daoBloodSugar = new DAOBloodSugar(getContext());
                        daoBloodSugar.delete(bloodsugarId);
                        getContext().startActivity(new Intent(getContext(), BloodSugarReportActivity.class));
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

        TextView bloodSugar;
        TextView createDate;

        ViewHolder(View v) {
            super(v);
            bloodSugar = v.findViewById(R.id.tv_item_rv_blood_sugar_report_list_blood_sugar);
            createDate = v.findViewById(R.id.tv_item_rv_blood_sugar_report_list_create_date);
        }
    }
}
