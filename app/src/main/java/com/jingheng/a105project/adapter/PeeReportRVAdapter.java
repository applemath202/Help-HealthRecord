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
import com.jingheng.a105project.activity.EditPeeActivity;
import com.jingheng.a105project.activity.PeeReportActivity;
import com.jingheng.a105project.model.Pee;
import com.jingheng.a105project.sqlite.DAOPee;

import java.util.ArrayList;

public class PeeReportRVAdapter extends CommonRVAdapter {

    private ArrayList<Pee> list;

    public PeeReportRVAdapter(Context context, ArrayList<Pee> list) {
        super(context);
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_pee_report_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final Pee pee = list.get(position);
            ViewHolder h = (ViewHolder) holder;
            h.pee.setText("尿液:" + pee.getPee());
            h.createDate.setText("時間:" + pee.getCreateDate());

            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getContext().startActivity(new Intent(getContext(), EditPeeActivity.class)
                            .putExtra("pee",pee));
                }
            });

            h.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showDeleteDialog(pee.getPeeId());
                    return false;
                }
            });
        }
    }

    private void showDeleteDialog(final int peeId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("刪除")
                .setMessage("確認是否刪除此筆紀錄？")
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DAOPee daoPee = new DAOPee(getContext());
                        daoPee.delete(peeId);
                        getContext().startActivity(new Intent(getContext(), PeeReportActivity.class));
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
        TextView pee;
        TextView createDate;

        ViewHolder(View v) {
            super(v);
            pee = v.findViewById(R.id.tv_item_rv_pee_report_list_pee);
            createDate = v.findViewById(R.id.tv_item_rv_pee_report_list_create_date);
        }
    }
}
