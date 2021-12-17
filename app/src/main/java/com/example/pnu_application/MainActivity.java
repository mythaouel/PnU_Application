package com.example.pnu_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.example.fragment.AccountFragment;
import com.example.fragment.CartFragment;
import com.example.fragment.CategoryFragment;
import com.example.fragment.HomeFragment;
import com.example.fragment.NotificationBlogFragment;
import com.example.fragment.NotificationFragment;
import com.example.fragment.UpdateInfoFragment;
import com.example.fragment.category.ProductDetailsFragment;
import com.example.model.Product;
import com.example.model.ProductItemClick;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import utils.Constant;

public class MainActivity extends AppCompatActivity implements ProductItemClick {

    public static BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navContainer);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();
        bottomNavigationView.setOnItemSelectedListener(navListener);

        if(Constant.arrCartProduct == null)
            Constant.arrCartProduct = new ArrayList<>();
    }

    public NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.itHome:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.itCategory:
                    selectedFragment = new CategoryFragment();
                    break;
                case R.id.itCart:
                    selectedFragment = new CartFragment();
                    break;
                case R.id.itNotification:
                    selectedFragment = new NotificationBlogFragment();
                    break;
                case R.id.itAccount:
                    selectedFragment = new AccountFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,selectedFragment).commit();
            return true;
        }
    };

    //create a method to hide Bottom Navigation Bar
    public static void hideBottomNav(){
        bottomNavigationView.setVisibility(View.GONE);
    }

    //create a method to show Bottom Navigation Bar
    public static void showBottomNav(){
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void click(Product p) {

        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putSerializable("SelectedProduct", p);
        productDetailsFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.layoutContainer, productDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void clickAct() {
        UpdateInfoFragment updateInfoFragment = new UpdateInfoFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layoutContainer, updateInfoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}