package com.example.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.adapter.ViewPager2OrderHistoryAdapter;
import com.example.pnu_application.R;
import com.google.android.material.tabs.TabLayout;

public class OrderHistory extends Fragment {
    private TabLayout tabLayoutAct;
    private ViewPager viewPagerAct;
    View mView;

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_order_history, container, false);
        tabLayoutAct=mView.findViewById(R.id.tabLayoutAct);
        viewPagerAct=mView.findViewById(R.id.viewPagerAct);
        ViewPager2OrderHistoryAdapter adapter= new ViewPager2OrderHistoryAdapter(getChildFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAct.setAdapter(adapter);
        tabLayoutAct.setupWithViewPager(viewPagerAct);
        return mView;
    }


}
