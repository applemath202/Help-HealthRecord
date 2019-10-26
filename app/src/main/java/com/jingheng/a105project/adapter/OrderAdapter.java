package com.jingheng.a105project.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jingheng.a105project.R;
import com.jingheng.a105project.activity.FoodInformationActivity;
import com.jingheng.a105project.model.Meal;

import java.util.ArrayList;

public class OrderAdapter extends CommonRVAdapter {

    // data
    private Context context;
    private ArrayList<Meal> list;

    public OrderAdapter(Context context, ArrayList<Meal> list) {
        super(context);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(getInflater().inflate(R.layout.item_rv_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final Meal item = list.get(position);
            final ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.name.setText(item.getName());
            viewHolder.amount.setText(String.valueOf(item.getAmount()));
            viewHolder.hot.setText(item.getHot() + "大卡");

            viewHolder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.setAmount(item.getAmount() + 1);
                    viewHolder.amount.setText(String.valueOf(item.getAmount()));
                    viewHolder.hot.setText((item.getHot() * item.getAmount() + "大卡"));
                    ((FoodInformationActivity) context).countTotalPrice(item.getHot());
                }
            });

            viewHolder.less.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (item.getAmount() == 1) {
                        ((FoodInformationActivity) context).removeItemToOrder(item);
                    } else {
                        item.setAmount(item.getAmount() - 1);
                        viewHolder.amount.setText(String.valueOf(item.getAmount()));
                        viewHolder.hot.setText((item.getHot() * item.getAmount() + "大卡"));
                        ((FoodInformationActivity) context).countTotalPrice(item.getHot() * -1);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView add;
        TextView amount;
        ImageView less;
        TextView hot;

        ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.tv_item_rv_order_name);
            add = v.findViewById(R.id.iv_item_rv_order_up);
            amount = v.findViewById(R.id.tv_item_rv_order_amount);
            less = v.findViewById(R.id.iv_item_rv_order_down);
            hot = v.findViewById(R.id.tv_item_rv_order_price);
        }
    }
}
