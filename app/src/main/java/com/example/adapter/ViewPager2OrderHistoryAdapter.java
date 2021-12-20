package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.fragment.BlogFragment;
import com.example.fragment.NotificationFragment;
import com.example.fragment.OrderHistory1;
import com.example.fragment.OrderHistory2;
import com.example.fragment.category.CatFoodFragment;
import com.example.fragment.category.DogFoodFragment;
import com.example.fragment.category.HotProductFragment;
import com.example.fragment.category.PetFashionFragment;
import com.example.fragment.category.PetToyFragment;

public class ViewPager2OrderHistoryAdapter extends FragmentStateAdapter {

    public ViewPager2OrderHistoryAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new OrderHistory1();
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