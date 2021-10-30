package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.LineItem;
import com.example.pnu_application.R;

import java.util.List;

public class AccountLineAdapter extends BaseAdapter {
    private Context context;
    private int      layout;
    private List<LineItem> lineLists;

    public AccountLineAdapter(Context context, int layout, List<LineItem> lineLists) {
        this.context = context;
        this.layout = layout;
        this.lineLists = lineLists;
    }

    @Override
    public int getCount() {
        return lineLists.size();
    }

    @Override
    public Object getItem(int i) {
        return lineLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.imvIcon1 = view.findViewById(R.id.imvIcon1);
            holder.txtNameLine = view.findViewById(R.id.txtNameLine);
            holder.imvIcon2 = view.findViewById(R.id.imvIcon2);
            view.setTag(holder);
        }else{
            holder=(ViewHolder) view.getTag();
        }
        //gan gia tri
         LineItem lineItems =lineLists.get(i);
        holder.imvIcon1.setImageResource(lineItems.getIcon1());
        holder.imvIcon2.setImageResource(lineItems.getIcon2());
        holder.txtNameLine.setText(lineItems.getNameLine());
        return view;
    }
    public static class ViewHolder{
        ImageView imvIcon1,imvIcon2;
        TextView txtNameLine;
    }
}
