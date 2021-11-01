package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.adapter.BannerAdapter;
import com.example.adapter.SpNoiBatAdapter;
import com.example.model.Banner;
import com.example.model.SpNoiBat;
import com.example.pnu_application.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;


public class HomeFragment extends Fragment {

    ImageButton btnFind;
    private ViewPager2 nViewPager2;
    private CircleIndicator3 nCircleIndicator3;
    BannerAdapter bannerAdapter;
    ArrayList<Banner> banners;

    RecyclerView rcvSpNoiBat;
    SpNoiBatAdapter nbAdapter;
    ArrayList<SpNoiBat> noiBat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        nViewPager2=view.findViewById(R.id.vpBanner);

        rcvSpNoiBat=view.findViewById(R.id.rcvNoiBat);
        configRecyclerView();


        btnFind=view.findViewById(R.id.btnFindItem);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,new FindFragment());
                transaction.commit();
            }
        });

        nCircleIndicator3=view.findViewById(R.id.circleIndicator);
        initData();
        return view;

    }

    private void configRecyclerView() {
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rcvSpNoiBat.setLayoutManager(manager);
        rcvSpNoiBat.setHasFixedSize(true);
        rcvSpNoiBat.setItemAnimator( new DefaultItemAnimator());
    }

    private void initData() {
        //Banner
        ArrayList<Banner> banners=new ArrayList<>();
        banners.add(new Banner(R.drawable.banner_1));
        banners.add(new Banner(R.drawable.banner_2));
        banners.add(new Banner(R.drawable.banner_3));
        banners.add(new Banner(R.drawable.banner_4));

        bannerAdapter=new BannerAdapter(getContext(),banners);
        nViewPager2.setAdapter(bannerAdapter);
        nCircleIndicator3.setViewPager(nViewPager2);

        //Sản phẩm nổi bật
        ArrayList<SpNoiBat> noiBat=new ArrayList<>();
        noiBat.add(new SpNoiBat(R.drawable.sp_16,"Nệm chấm bi hồng"));
        noiBat.add(new SpNoiBat(R.drawable.sp_4,"Yếm kèm dây dắt"));
        noiBat.add(new SpNoiBat(R.drawable.sp_11,"Bộ yếm và áo"));
        noiBat.add(new SpNoiBat(R.drawable.sp_14,"Bát ăn hình cute"));

        nbAdapter=new SpNoiBatAdapter(getContext().getApplicationContext(),noiBat);
        rcvSpNoiBat.setAdapter(nbAdapter);





    }
}