package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


import com.example.adapter.OrderStatusAdapter;
import com.example.model.OrderHistoryItemClick;
import com.example.model.OrderStatus;

import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;

import java.util.ArrayList;

public class OrderHistory1 extends Fragment {

    View view;
    ListView lvOrder;
    OrderStatusAdapter adapter;
    ArrayList<OrderStatus> orderList;

    OrderHistoryItemClick orderHistoryItemClick;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.account_tab_order1_handling, container, false);
        linkViews();
        initData();
        MainActivity.hideBottomNav();
        addEvents();
        return view;
    }

    private void addEvents() {
       lvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
               orderHistoryItemClick = (OrderHistoryItemClick) getActivity();
               if (orderHistoryItemClick != null){
                   orderHistoryItemClick.click(orderList.get(i));
               }
           }
       });
    }

    private void initData() {
        orderList= new ArrayList<>();
        orderList.add(new OrderStatus("#Order 345","Đã vận chuyển","26Tháng 10 ,2019","300.000 đ"));
        orderList.add(new OrderStatus("#Order 346","Đã hủy","24 Tháng 10 ,2020","243.000 đ"));
        orderList.add(new OrderStatus("#Order 347","Đã vận chuyển","26 Tháng 8 ,2021","532.000 đ"));
        adapter=new OrderStatusAdapter(getContext(),R.layout.account_orderl_list_item,orderList);
        lvOrder.setAdapter(adapter);
    }

    private void linkViews() {
        lvOrder=view.findViewById(R.id.lvOrder);
    }
}
