package com.example.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


import com.example.adapter.OrderDetailAdapter;
import com.example.model.OrderDetail;
import com.example.model.OrderHistoryItemClick;


import com.example.pnu_application.Loading_Screen;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDatabaseHelper;
import com.example.pnu_application.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class OrderHistoryHandlingFragment extends Fragment {

    View view;
    ListView lvOrder;
    OrderDetailAdapter adapter;
    ArrayList<OrderDetail> orderList;

    OrderHistoryItemClick orderHistoryItemClick;

    int MATK;
    MainActivity mainActivity;

    public static LinearLayout layoutOrderEmpty,layoutHaveOrder;


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
        getLayout();
        MainActivity.hideBottomNav();
        addEvents();
        return view;
    }

    private void linkViews() {
        lvOrder=view.findViewById(R.id.lvOrder);

        layoutOrderEmpty= view.findViewById(R.id.layoutOrderEmpty);
        layoutHaveOrder = view.findViewById(R.id.layoutHaveOrder);
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

    //Kiểm tra đơn hàng account đã đặt để hiện giao diện phù hợp
    //Được gọi sau initData ->có dữ liệu orderList để kiem tra
    private void getLayout(){
        if (orderList.size() >0){
            layoutHaveOrder.setVisibility( View.VISIBLE );
            layoutOrderEmpty.setVisibility( View.GONE );
        }else{
            layoutHaveOrder.setVisibility( View.GONE );
            layoutOrderEmpty.setVisibility( View.VISIBLE );
        }
    }

    private ArrayList<OrderDetail> getDataFromDb(){
            orderList= new ArrayList<>();
            String orderId,orderTotal;
            Cursor cursor = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.ORDER_TB_NAME +
                    " WHERE " + MyDatabaseHelper.ORDER_COL_ACT_ID + " = " + MATK );
            orderList.clear();

            while (cursor!=null && cursor.moveToNext())
             {
                orderId= "Đơn hàng #" +cursor.getString(0);
                orderTotal=String.format("%,.0f",cursor.getDouble(5) )+ " đ";

                orderList.add(new OrderDetail(orderId, cursor.getString(3), cursor.getString(2),orderTotal));
            }
            cursor.close();
            return orderList;
    }

    private void initData() {
        orderList= new ArrayList<>();
        adapter= new OrderDetailAdapter(getContext(),R.layout.account_orderl_list_item,getDataFromDb());
        lvOrder.setAdapter(adapter);
    }


}
