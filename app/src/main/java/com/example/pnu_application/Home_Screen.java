package com.example.pnu_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.adapter.BannerAdapter;
import com.example.model.Banner;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class Home_Screen extends AppCompatActivity {
    private ViewPager2 nViewPager2;
    private CircleIndicator3 nCircleIndicator3;
    BannerAdapter bannerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        linkView();
        initData();
    }

    private void linkView() {
        nViewPager2=findViewById(R.id.vpBanner);
        nCircleIndicator3=findViewById(R.id.circleIndicator);
    }

    private void initData() {
        ArrayList<Banner> banners=new ArrayList<>();
        banners.add(new Banner(R.drawable.banner_1));
        banners.add(new Banner(R.drawable.banner_2));
        banners.add(new Banner(R.drawable.banner_3));
        banners.add(new Banner(R.drawable.banner_4));

        bannerAdapter=new BannerAdapter(getApplicationContext(),banners);
        nViewPager2.setAdapter(bannerAdapter);
        nCircleIndicator3.setViewPager(nViewPager2);


    }

}