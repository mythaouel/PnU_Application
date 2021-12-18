package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.adapter.BlogAdapter;
import com.example.model.Blog;
import com.example.model.BlogItemClick;
import com.example.pnu_application.R;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {
    ListView lvNotification;
    ArrayList<Blog> notifications;
    BlogAdapter adapter;
    BlogItemClick blogItemClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification,container,false);
        lvNotification = view.findViewById(R.id.lvNotification);
        initData();
        addEvents();
        return view;
    }

    private void addEvents() {
        lvNotification.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                blogItemClick = (BlogItemClick) getActivity();
                if (blogItemClick != null)
                    blogItemClick.click( notifications.get( i ) );
            }
        } );
    }

    private void initData() {
        notifications = new ArrayList<>();
        notifications.add(new Blog("Chương trình giảm giá 12/12","Nhân dịp 12/12, PnU xin giới thiệu đến bạn các ưu đãi giảm giá..."));
        notifications.add(new Blog("Thức ăn cho chó đang đồng loạt giảm giá","Một số loại thức ăn cho chó như Royal Canin, ZENITH đang được giảm giá..."));
        notifications.add(new Blog("Thêm nhiều phụ kiện cho thú cưng","Ghé thăm gian hàng phụ kiện để có ngay những sản phẩm..."));
        notifications.add(new Blog("Bạn đã sắm đồ cho thú cưng dịp Giáng sinh chưa?","Nhanh tay đặt ngay những sản phẩm hấp dẫn cho mùa Giáng sinh..."));
        notifications.add(new Blog("Giảm giá sốc duy nhất vào 11/11","Chỉ duy nhất 11/11, giảm giá sốc lên đến 30% tất cả các sản phẩm..."));
        notifications.add(new Blog("Khai trương cửa hàng ở Thành phố Hồ Chí Minh","PnU vừa khai trương cửa hàng đầu tiên ở thành phố Hồ Chí Minh..."));
        adapter = new BlogAdapter(getContext(), R.layout.notification_blog_item ,notifications);
        lvNotification.setAdapter(adapter);
    }
}
