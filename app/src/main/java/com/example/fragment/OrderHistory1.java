package com.example.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


import com.example.adapter.OrderDetailAdapter;
import com.example.model.OrderDetail;
import com.example.model.OrderHistoryItemClick;


import com.example.pnu_application.Loading_Screen;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDatabaseHelper;
import com.example.pnu_application.R;

import java.util.ArrayList;

public class OrderHistory1 extends Fragment {

    View view;
    ListView lvOrder;
    OrderDetailAdapter adapter;
    ArrayList<OrderDetail> orderList;

    OrderHistoryItemClick orderHistoryItemClick;

    int MATK;
    MainActivity mainActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_tab_order1_handling, container, false);
        linkViews();
        //Lấy mã Khách hàng đang đăng nhập hiện
        mainActivity = (MainActivity) getActivity();
        MATK = mainActivity.getMATK();
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

    private ArrayList<OrderDetail> getDataFromDb(){
            orderList= new ArrayList<>();
            Cursor cursor = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.ORDER_TB_NAME + " WHERE " + MyDatabaseHelper.ORDER_COL_ACT_ID + " = " + MATK );
            orderList.clear();
            while (cursor!=null && cursor.moveToNext())
             {
                orderList.add(new OrderDetail("#Order" +cursor.getString(0), cursor.getString(3), cursor.getString(2),String.valueOf(cursor.getDouble(5)+ " đ")));
            }
            cursor.close();
            return orderList;
    }
    private void initData() {
        orderList= new ArrayList<>();
        adapter= new OrderDetailAdapter(getContext(),R.layout.account_orderl_list_item,getDataFromDb());
        lvOrder.setAdapter(adapter);
    }

    private void linkViews() {
        lvOrder=view.findViewById(R.id.lvOrder);
    }
}
