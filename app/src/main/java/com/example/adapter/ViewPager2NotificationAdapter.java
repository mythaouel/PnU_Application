package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fragment.BlogFragment;
import com.example.fragment.NotificationBlogFragment;
import com.example.fragment.NotificationFragment;
import com.example.fragment.category.AllProductFragment;
import com.example.fragment.category.CatFoodFragment;
import com.example.fragment.category.DogFoodFragment;
import com.example.fragment.category.PetFashionFragment;
import com.example.fragment.category.PetToyFragment;

public class ViewPager2NotificationAdapter extends FragmentStateAdapter {
    public ViewPager2NotificationAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super( fragmentManager, lifecycle );
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new NotificationFragment();
            case 1:
                return new BlogFragment();
            default:
                return new NotificationFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
