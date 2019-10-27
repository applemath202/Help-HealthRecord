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
import com.jingheng.a105project.activity.DrugReportActivity;
import com.jingheng.a105project.activity.EditDrugActivity;
import com.jingheng.a105project.model.Drug;
import com.jingheng.a105project.sqlite.DAODrug;
import com.jingheng.a105project.sqlite.DAOFood;

import java.util.ArrayList;

public class DrugReportRVAdapter extends CommonRVAdapter {

    private ArrayList<Drug> list;

    public DrugReportRVAdapter(Context context, ArrayList<Drug> list) {
        super(context);
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_drug_report_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final Drug drug = list.get(position);
            ViewHolder h = (ViewHolder) holder;
            h.name.setText("運動項目:" + drug.getDrugName());
            h.time.setText("運動項目:" + drug.getDrugTime());

            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getContext().startActivity(new Intent(getContext(), EditDrugActivity.class)
                            .putExtra("drug", drug));
                }
            });

            h.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showDeleteDialog(drug.getDrugId());
                    return false;
                }
            });

        }
    }

    private void showDeleteDialog(final int drugID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("刪除")
                .setMessage("確認是否刪除此筆紀錄？")
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DAODrug daoDrug = new DAODrug(getContext());
                        daoDrug.delete(drugID);
                        getContext().startActivity(new Intent(getContext(), DrugReportActivity.class));
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

        TextView name;
        TextView time;

        ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.tv_item_rv_drug_report_list_name);
            time = v.findViewById(R.id.tv_item_rv_drug_report_list_time);
        }
    }
}
