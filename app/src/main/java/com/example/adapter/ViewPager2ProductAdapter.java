package com.example.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fragment.category.CatFoodFragment;
import com.example.fragment.category.DogFoodFragment;
import com.example.fragment.category.HotProductFragment;
import com.example.fragment.category.PetFashionFragment;
import com.example.fragment.category.PetToyFragment;

//Connecting an adapter to the viewpager

public class ViewPager2ProductAdapter extends FragmentStateAdapter {

    public ViewPager2ProductAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new HotProductFragment();
            case 1:
                return new DogFoodFragment();
            case 2:
                return new CatFoodFragment();
            case 3:
                return new PetToyFragment();
            case 4:
                return new PetFashionFragment();
            default:
                return new HotProductFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
