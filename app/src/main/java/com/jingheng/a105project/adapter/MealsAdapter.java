package com.jingheng.a105project.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jingheng.a105project.R;
import com.jingheng.a105project.activity.FoodActivity;
import com.jingheng.a105project.model.Meal;

import java.util.ArrayList;

public class MealsAdapter extends CommonRVAdapter {

    // data
    private Context context;
    private ArrayList<Meal> list;

    public MealsAdapter(Context context, ArrayList<Meal> list) {
        super(context);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(getInflater().inflate(R.layout.item_rv_meals, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final Meal item = list.get(position);
            final ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.picture.setImageResource(item.getPicture());
            viewHolder.name.setText(item.getName());
            viewHolder.hot.setText(item.getHot() + "大卡");

            viewHolder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // let item add to order list
                    ((FoodActivity) context).addMealToOrder(item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView picture;
        TextView name;
        TextView hot;
        Button add;

        ViewHolder(View v) {
            super(v);
            picture = v.findViewById(R.id.iv_item_rv_meals);
            name = v.findViewById(R.id.tv_item_rv_meals_name);
            hot = v.findViewById(R.id.tv_item_rv_meals_hot);
            add = v.findViewById(R.id.bt_item_rv_meals);
        }
    }
}
