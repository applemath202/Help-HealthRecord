package com.jingheng.a105project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingheng.a105project.R;
import com.jingheng.a105project.model.Blood;
import com.jingheng.a105project.model.Food;

import java.util.ArrayList;

import androidx.annotation.NonNull;
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
            h.createDate.setText("時間:" + food.getCreateDate());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView food;
        TextView createDate;

        ViewHolder(View v) {
            super(v);
            food = v.findViewById(R.id.tv_item_rv_food_report_list_food);
            createDate = v.findViewById(R.id.tv_item_rv_food_report_list_create_date);
        }
    }
}
