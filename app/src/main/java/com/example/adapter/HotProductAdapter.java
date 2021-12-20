package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.Product;
import com.example.pnu_application.R;

import java.util.ArrayList;

import utils.Constant;

public class HotProductAdapter extends BaseAdapter {
    Context context;
    int itemGridView;
    ArrayList<Product> products;

    public HotProductAdapter(Context context, int itemGridView, ArrayList<Product> products) {
        this.context = context;
        this.itemGridView = itemGridView;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(itemGridView, null);
            holder.imvSpNoiBat = view.findViewById(R.id.imvSpNoiBat);
            holder.txtSpNoiBat = view.findViewById(R.id.txtSpNoiBat);

            view.setTag(holder);

        } else {
            holder  = (ViewHolder) view.getTag();
        }

        Product p = products.get(i);
        holder.imvSpNoiBat.setImageResource(p.getProductThumbnail());
        holder.txtSpNoiBat.setText(p.getProductName());
        return view;

    }

    private class ViewHolder{
        ImageView imvSpNoiBat;
        TextView txtSpNoiBat;
    }

}
