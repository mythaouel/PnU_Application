package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.adapter.ViewPager2ProductAdapter;
import com.example.fragment.category.ProductDetailsFragment;
import com.example.model.Product;
import com.example.model.ProductItemClick;
import com.example.pnu_application.R;
import com.google.android.material.tabs.TabLayout;


public class CategoryFragment extends Fragment{
    private Toolbar toolBarCategory;
    private FragmentManager fragmentManager, fragmentManager1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        fragmentManager = getChildFragmentManager();
        ViewPager2ProductAdapter productAdapter = new ViewPager2ProductAdapter(fragmentManager, getLifecycle());
        final ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);
        viewPager2.setAdapter(productAdapter);

        //Add Tabs into the TabLayout
        TabLayout tabLayoutProduct;
        tabLayoutProduct = view.findViewById(R.id.tabLayoutProduct);
        tabLayoutProduct.addTab(tabLayoutProduct.newTab().setText("Tất cả"));
        tabLayoutProduct.addTab(tabLayoutProduct.newTab().setText("Thức ăn cho Chó"));
        tabLayoutProduct.addTab(tabLayoutProduct.newTab().setText("Thức ăn cho Mèo"));
        tabLayoutProduct.addTab(tabLayoutProduct.newTab().setText("Đồ chơi chó mèo"));
        tabLayoutProduct.addTab(tabLayoutProduct.newTab().setText("Thời trang chó mèo"));

        //Connecting TabLayout to Adapter
        tabLayoutProduct.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Change Tab when swiping
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayoutProduct.selectTab(tabLayoutProduct.getTabAt(position));
            }
        });

        return view;
    }
}