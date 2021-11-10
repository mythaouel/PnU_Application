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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    Context context;
    int itemGridview;
    ArrayList<Product> products;

    public ProductAdapter(Context context, int itemGridview, ArrayList<Product> products) {
        this.context = context;
        this.itemGridview = itemGridview;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(itemGridview, null);
            holder.imvThumb = view.findViewById(R.id.imvThumbnail);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtPrice = view.findViewById(R.id.txtPrice);

            view.setTag(holder);

        } else {
            holder  = (ViewHolder) view.getTag();
        }

        Product p = products.get(i);
        holder.imvThumb.setImageResource(p.getProductThumbnail());
        holder.txtName.setText(p.getProductName());
        //holder.txtPrice.setText(String.valueOf(p.getProductPrice()));
        holder.txtPrice.setText(String. format("%.3f", p.getProductPrice())+ " " + "đ");
        return view;
    }


    private class ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtPrice;
    }
}
