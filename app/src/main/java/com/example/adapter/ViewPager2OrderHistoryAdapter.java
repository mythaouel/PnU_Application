package com.example.adapter;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.fragment.OrderHistory1;
import com.example.fragment.OrderHistory2;


public class ViewPager2OrderHistoryAdapter extends FragmentStateAdapter {

    public ViewPager2OrderHistoryAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new OrderHistory2();

            default:
                return new OrderHistory1();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}