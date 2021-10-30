package com.example.fragment.category;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.AccountLineAdapter;
import com.example.adapter.OrderStatusAdapter;
import com.example.model.LineItem;
import com.example.model.OrderStatus;
import com.example.pnu_application.R;

import java.util.ArrayList;


public class OrderHistory2 extends Fragment {

    View view;
    ListView lvOrder;
    OrderStatusAdapter adapter;
    ArrayList<OrderStatus> orderList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.account_tab_order_finish, container, false);
        linkViews();
        initData();
        return view;
    }

    private void initData() {
        orderList= new ArrayList<>();
        orderList.add(new OrderStatus("Order 345","Đã vận chuyển","26,Tháng 10,2019","300.00d "));
        orderList.add(new OrderStatus("Order 346","Đã hủy",",Tháng 10,2020","243.000 đ"));
        orderList.add(new OrderStatus("Order 347","Đã vận chuyển","26 Tháng 8 ,2020","532.000 đ"));
        adapter=new OrderStatusAdapter(getContext(),R.layout.account_orderl_list_item,orderList);
        lvOrder.setAdapter(adapter);
    }

    private void linkViews() {
        lvOrder=view.findViewById(R.id.lvOrder);
    }
}