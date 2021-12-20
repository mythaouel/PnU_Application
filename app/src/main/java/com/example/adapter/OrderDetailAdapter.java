package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.OrderDetail;
import com.example.pnu_application.R;

import java.util.List;

public class OrderDetailAdapter extends BaseAdapter {
    private Context context;
    private int      layout;
    private List<OrderDetail> orderLists;

    public OrderDetailAdapter(Context context, int layout, List<OrderDetail> orderLists) {
        this.context = context;
        this.layout = layout;
        this.orderLists = orderLists;
    }

    @Override
    public int getCount() {
        return orderLists.size();
    }

    @Override
    public Object getItem(int i) {
        return getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        OrderDetailAdapter.ViewHolder holder;
        if(view==null) {
            holder = new OrderDetailAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.imvIconOrder = view.findViewById(R.id.imvIconOrder);
            holder.txtOrderId = view.findViewById(R.id.txtOrderId);
            holder.txtOrderStatus = view.findViewById(R.id.txtOrderStatus);
            holder.txtOrderDateTime = view.findViewById(R.id.txtOrderDateTime);
            holder.txtOrderTotal = view.findViewById(R.id.txtOrderTotal);

            view.setTag(holder);
        }else{
            holder=(OrderDetailAdapter.ViewHolder) view.getTag();
        }
        //gan gia tri
        OrderDetail orderStatus =orderLists.get(i);
        holder.imvIconOrder.setImageResource(R.drawable.act_ic_order_list);
        holder.txtOrderId.setText(orderStatus.getOrderId());
        holder.txtOrderStatus.setText(orderStatus.getOrderStatus());
        holder.txtOrderDateTime.setText(orderStatus.getOrderDateTime());
        holder.txtOrderTotal.setText(orderStatus.getOrderTotal());
        return view;
    }
    public static class ViewHolder{
        ImageView imvIconOrder;
        TextView txtOrderId,txtOrderStatus,txtOrderDateTime,txtOrderTotal;
    }
}
