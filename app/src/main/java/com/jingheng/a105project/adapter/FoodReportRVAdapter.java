package com.jingheng.a105project.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingheng.a105project.R;
import com.jingheng.a105project.activity.FoodReportActivity;
import com.jingheng.a105project.activity.PeeReportActivity;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.model.Food;
import com.jingheng.a105project.sqlite.DAOFood;
import com.jingheng.a105project.sqlite.DAOPee;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class FoodReportRVAdapter extends CommonRVAdapter {

    private Context context;
    private ArrayList<Food> list;

    public FoodReportRVAdapter(Context context, ArrayList<Food> list) {
        super(context);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodReportRVAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_food_report_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FoodReportRVAdapter.ViewHolder) {
            final Food food = list.get(position);
            FoodReportRVAdapter.ViewHolder h = (FoodReportRVAdapter.ViewHolder) holder;
            h.food.setText("食物:" + food.getFood());
            h.hot.setText("熱量:" + food.getHot());
            h.createDate.setText("時間:" + food.getCreateDate());

            h.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showDeleteDialog(food.getFoodId());
                    return false;
                }
            });
        }
    }

    private void showDeleteDialog(final int foodId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("刪除")
                .setMessage("確認是否刪除此筆紀錄？")
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DAOFood daoFood = new DAOFood(getContext());
                        daoFood.delete(foodId);
                        getContext().startActivity(new Intent(getContext(), FoodReportActivity.class));
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
        TextView food;
        TextView hot;
        TextView createDate;

        ViewHolder(View v) {
            super(v);
            food = v.findViewById(R.id.tv_item_rv_food_report_list_food);
            hot = v.findViewById(R.id.tv_item_rv_food_report_list_hot);
            createDate = v.findViewById(R.id.tv_item_rv_food_report_list_create_date);
        }
    }
}
