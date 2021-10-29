package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fragment.BlogFragment;
import com.example.fragment.NotificationFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> arrFragment = new ArrayList<>();
    private ArrayList<String> arrFragTitle = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrFragment.get(position);
        }

    @Override
    public int getCount() {
        return arrFragment.size();
    }
    public void addFragment (Fragment fragment, String title){
        arrFragment.add(fragment);
        arrFragTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrFragTitle.get(position);
    }
}
