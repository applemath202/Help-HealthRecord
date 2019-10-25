package com.jingheng.a105project.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.jingheng.a105project.R;
import com.jingheng.a105project.activity.BloodReportActivity;
import com.jingheng.a105project.activity.EditBloodActivity;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.sqlite.DAOBlood;

import java.util.ArrayList;

public class BloodReportRVAdapter extends CommonRVAdapter{

    private ArrayList<Blood> list;

    public BloodReportRVAdapter(Context context, ArrayList<Blood> list) {
        super(context);
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
            h.bloodPressure.setText("舒張壓:" + blood.getBloodPressure());
            h.bloodPressure2.setText("收縮壓:" + blood.getBloodPressure_2());
            h.createDate.setText("時間:" + blood.getCreateDate());

            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getContext().startActivity(new Intent(getContext(), EditBloodActivity.class)
                    .putExtra("blood",blood));
                }
            });

            h.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showDeleteDialog(blood.getBloodId());
                    return false;
                }
            });
        }
    }

    private void showDeleteDialog(final int bloodId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("刪除")
                .setMessage("確認是否刪除此筆紀錄？")
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DAOBlood daoBlood = new DAOBlood(getContext());
                        daoBlood.delete(bloodId);
                        getContext().startActivity(new Intent(getContext(),BloodReportActivity.class));
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
        TextView bloodPressure;
        TextView bloodPressure2;
        TextView createDate;

        ViewHolder(View v) {
            super(v);
            bloodPressure = v.findViewById(R.id.tv_item_rv_blood_report_list_blood_pressure);
            bloodPressure2 = v.findViewById(R.id.tv_item_rv_blood_report_list_blood_pressure2);
            createDate = v.findViewById(R.id.tv_item_rv_blood_report_list_create_date);
        }
    }
}
