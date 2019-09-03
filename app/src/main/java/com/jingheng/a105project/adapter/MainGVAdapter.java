package com.jingheng.a105project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingheng.a105project.model.MainGVItem;
import com.jingheng.a105project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainGVAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MainGVItem> list;
    private int width;
    private int high;


    public MainGVAdapter(Context context, ArrayList<MainGVItem> list) {
        this.context = context;
        this.list = list;
        this.width = (int) (context.getResources().getDisplayMetrics().density * 200);
        this.high = (int) (context.getResources().getDisplayMetrics().density * 200);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.main_gv_item, null);
        }

        MainGVItem item = (MainGVItem) getItem(position);
        ImageView img = (ImageView) convertView.findViewById(R.id.gi_iv);
        TextView tv = (TextView) convertView.findViewById(R.id.gi_tv);
        //img.setImageResource(item.getPicture());
        Picasso.with(context).load(item.getPicture()).centerInside().resize(width, high).into(img);
        tv.setText(item.getName());
        return convertView;
    }
}
