package com.jingheng.a105project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jingheng.a105project.R;
import com.jingheng.a105project.activity.QAActivity;

import java.util.ArrayList;

public class QAAdapter extends CommonRVAdapter {

    private ArrayList<String> list;

    public QAAdapter(Context context, ArrayList<String> list) {
        super(context);
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_qa, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final String title = list.get(position);
            ViewHolder h = (ViewHolder) holder;
            h.title.setText(title);

            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setContent(title);
                }
            });
        }
    }

    private void setContent(String title){
        switch (title) {
            case "淺談腎臟":
                ((QAActivity)getContext()).setContentView(R.layout.qa1_1);
                break;
            case "淺談腹膜透析":
                ((QAActivity)getContext()).setContentView(R.layout.qa2_1);
                break;
            case "蛋白質要多少才夠?":
                ((QAActivity)getContext()).setContentView(R.layout.qa3_1);
                break;
            case "什麼是存活率?":
                ((QAActivity)getContext()).setContentView(R.layout.qa4_1);
                break;
        }

        Button buttonBack = ((QAActivity)getContext()).findViewById(R.id.back);
        buttonBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(), QAActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tv_item_rv_qa_title);
        }
    }
}
