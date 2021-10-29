package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.RecyclerViewAdapter;
import com.example.model.Blog;
import com.example.pnu_application.R;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {
    RecyclerView rcvNotification;
    ArrayList<Blog> notifications;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification,container,false);
        rcvNotification = view.findViewById(R.id.rcvNotification);
        ArrayList<Blog> notifications = new ArrayList<>();
        notifications.add(new Blog("Chương trình giảm giá 11/11","Nhân dịp 11/11, PnU xin giới thiệu đến bạn chương trình..."));
        notifications.add(new Blog("Thức ăn cho chó đang đồng loạt giảm giá","Một số loại thức ăn cho chó đang được giảm giá sốc..."));
        notifications.add(new Blog("Thêm nhiều phụ kiện cho thú cưng","Ghé thăm gian hàng phụ kiện để đặt ngay những sản..."));
        notifications.add(new Blog("Freeship cho đơn hàng từ 500k","Nhanh tay đặt hàng để được nhận ưu đãi freesh..."));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),notifications);
        rcvNotification.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvNotification.setAdapter(recyclerViewAdapter);
        return view;
    }
}
