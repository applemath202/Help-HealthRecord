package com.jingheng.a105project.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingheng.a105project.R;
import com.jingheng.a105project.activity.BloodReportActivity;
import com.jingheng.a105project.activity.EditWaterActivity;
import com.jingheng.a105project.activity.WaterReportActivity;
import com.jingheng.a105project.model.Food;
import com.jingheng.a105project.model.Water;
import com.jingheng.a105project.sqlite.DAOBlood;
import com.jingheng.a105project.sqlite.DAOWater;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class WaterReportRVAdapter extends CommonRVAdapter {

    private ArrayList<Water> list;

    public WaterReportRVAdapter(Context context, ArrayList<Water> list) {
        super(context);
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
            h.water.setText("水分:" + water.getWater());
            h.createDate.setText("時間:" + water.getCreateDate());

            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getContext().startActivity(new Intent(getContext(), EditWaterActivity.class)
                            .putExtra("water", water));
                }
            });

            h.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showDeleteDialog(water.getWaterId());
                    return false;
                }
            });
        }
    }

    private void showDeleteDialog(final int waterId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("刪除")
                .setMessage("確認是否刪除此筆紀錄？")
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DAOWater daoWater = new DAOWater(getContext());
                        daoWater.delete(waterId);
                        getContext().startActivity(new Intent(getContext(), WaterReportActivity.class));
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
        TextView water;
        TextView createDate;

        ViewHolder(View v) {
            super(v);
            water = v.findViewById(R.id.tv_item_rv_water_report_list_water);
            createDate = v.findViewById(R.id.tv_item_rv_water_report_list_create_date);
        }
    }
}
