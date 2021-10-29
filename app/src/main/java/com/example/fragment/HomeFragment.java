package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.adapter.BannerAdapter;
import com.example.model.Banner;
import com.example.pnu_application.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;


public class HomeFragment extends Fragment {

    private ViewPager2 nViewPager2;
    private CircleIndicator3 nCircleIndicator3;
    BannerAdapter bannerAdapter;
    ArrayList<Banner> banners;

    RecyclerView rcvSpNoiBat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        nViewPager2=view.findViewById(R.id.vpBanner);

        nCircleIndicator3=view.findViewById(R.id.circleIndicator);
        initData();
        return view;

    }

    private void configRecyclerView() {
        LinearLayoutManager manager=new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        rcvSpNoiBat.setLayoutManager(manager);
        rcvSpNoiBat.setHasFixedSize(true);
        rcvSpNoiBat.setItemAnimator( new DefaultItemAnimator());
    }

    private void initData() {
        ArrayList<Banner> banners=new ArrayList<>();
        banners.add(new Banner(R.drawable.banner_1));
        banners.add(new Banner(R.drawable.banner_2));
        banners.add(new Banner(R.drawable.banner_3));
        banners.add(new Banner(R.drawable.banner_4));

        bannerAdapter=new BannerAdapter(getContext(),banners);
        nViewPager2.setAdapter(bannerAdapter);
        nCircleIndicator3.setViewPager(nViewPager2);




    }
}