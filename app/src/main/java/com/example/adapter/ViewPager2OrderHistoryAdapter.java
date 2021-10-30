package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.fragment.category.OrderHistory1;
import com.example.fragment.category.OrderHistory2;

public class ViewPager2OrderHistoryAdapter extends FragmentStatePagerAdapter {
    public ViewPager2OrderHistoryAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OrderHistory1();
            case 1:
                return new OrderHistory2();
            default: return new OrderHistory1();

        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Đang xử lý";
            case 1:
                return "Hoàn thành";
            default: return "Đang xử lý";
        }
    }
    @Override
    public int getCount() {
        return 2;
    }
}
