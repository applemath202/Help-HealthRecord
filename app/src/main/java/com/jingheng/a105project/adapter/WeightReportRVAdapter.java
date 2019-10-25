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
import com.jingheng.a105project.activity.CommonActivity;
import com.jingheng.a105project.activity.EditBloodActivity;
import com.jingheng.a105project.activity.EditWeightActivity;
import com.jingheng.a105project.activity.WeightReportActivity;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.model.Weight;
import com.jingheng.a105project.sqlite.DAOBlood;
import com.jingheng.a105project.sqlite.DAOWeight;

import java.util.ArrayList;

public class WeightReportRVAdapter extends CommonRVAdapter {

    private ArrayList<Weight> list;

    public WeightReportRVAdapter(Context context, ArrayList<Weight> list) {
        super(context);
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_weight_report_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final Weight weight = list.get(position);
            ViewHolder h = (ViewHolder) holder;
            h.weight.setText("體重:" + weight.getWeight());
            h.createDate.setText("時間:" + weight.getCreateDate());

            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getContext().startActivity(new Intent(getContext(), EditWeightActivity.class)
                            .putExtra("weight", weight));
                }
            });

            h.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showDeleteDialog(weight.getWeightId());
                    return false;
                }
            });
        }

    }

    private void showDeleteDialog(final int weightId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("刪除")
                .setMessage("確認是否刪除此筆紀錄？")
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DAOWeight daoWeight = new DAOWeight(getContext());
                        daoWeight.delete(weightId);
                        getContext().startActivity(new Intent(getContext(), WeightReportActivity.class));
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
        TextView weight;
        TextView createDate;

        ViewHolder(View v) {
            super(v);
            weight = v.findViewById(R.id.tv_item_rv_weight_report_list_weight);
            createDate = v.findViewById(R.id.tv_item_rv_weight_report_list_create_date);
        }
    }
}
