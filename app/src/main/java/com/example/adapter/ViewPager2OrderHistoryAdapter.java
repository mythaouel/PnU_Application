package com.example.adapter;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.fragment.OrderHistoryFinishedFragment;
import com.example.fragment.OrderHistoryHandlingFragment;


public class ViewPager2OrderHistoryAdapter extends FragmentStateAdapter {

    public ViewPager2OrderHistoryAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new OrderHistoryFinishedFragment();

            default:
                return new OrderHistoryHandlingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}