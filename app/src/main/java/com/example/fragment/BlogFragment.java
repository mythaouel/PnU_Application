package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.RecyclerViewAdapter;
import com.example.model.Blog;
import com.example.pnu_application.R;

import java.util.ArrayList;
import java.util.List;

public class BlogFragment extends Fragment {

    RecyclerView rcvBlog;
    List<Blog> blogs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog,container,false);
        rcvBlog = view.findViewById(R.id.rcvBlog);
        configRecyclerView();
        initData();
        return view;
    }

    private void configRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvBlog.setLayoutManager( manager );
        DividerItemDecoration decoration = new DividerItemDecoration( getContext(),manager.getOrientation() );
        rcvBlog.addItemDecoration( decoration );
        rcvBlog.setHasFixedSize( true );
        rcvBlog.setItemAnimator( new DefaultItemAnimator() );
    }

    private void initData() {
        List<Blog> blogs = new ArrayList<>();
        blogs.add(new Blog("Sữa tắm cho chó nào tốt nhất?","Ve ký sinh trên cơ thể không chỉ khiến các boss chó ngứa ngáy khó chịu mà còn có thể khiến da bị tổn thương, nhiễm trùng..."));
        blogs.add(new Blog("5 loại thức ăn cho mèo được tin dùng nhất","Hiện nay xu hướng nuôi mèo làm thú cưng càng ngày càng được yêu thích. Vì vậy các thương hiệu về đồ ăn, phụ kiện thú cưng ngày càng nhiều..."));
        blogs.add(new Blog("5 loại thức ăn cho mèo mang thai đầy đủ dinh dưỡng cần thiết","Cũng như hầu hết các loài vật khác, thức ăn cho mèo mang thai quyết định rất lớn đến thể trạng..."));
        blogs.add(new Blog("5 điều cần biết khi mua thức ăn cho mèo","Thức ăn cho mèo Max Power là thương hiệu thức ăn vật nuôi nổi tiếng của Anh...."));
        blogs.add(new Blog("6 điều bạn nên chú ý khi lựa chọn thức ăn cho mèo","Về cách nuôi mèo trong nhà, chắc chắn bạn không thể không quan tâm đến một điều: làm thế nào để lựa chọn được chế độ dinh dưỡng tốt nhất cho mèo..."));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),blogs);
        rcvBlog.setAdapter(recyclerViewAdapter);
    }
}
