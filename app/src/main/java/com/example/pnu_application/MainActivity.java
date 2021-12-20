package com.example.pnu_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.fragment.AccountFragment;
import com.example.fragment.BlogDetailFragment;
import com.example.fragment.CartFragment;
import com.example.fragment.CategoryFragment;
import com.example.fragment.HomeFragment;
import com.example.fragment.NotificationBlogFragment;
import com.example.fragment.OrderDetailFragment;
import com.example.fragment.OrderHistory;
import com.example.fragment.UpdateInfoFragment;
import com.example.fragment.NoLoginAccountFragment;
import com.example.fragment.category.ProductDetailsFragment;
import com.example.model.Blog;
import com.example.model.BlogItemClick;
import com.example.model.OrderHistoryItemClick;
import com.example.model.OrderStatus;
import com.example.model.Product;
import com.example.model.ProductItemClick;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import utils.Constant;

public class MainActivity extends AppCompatActivity implements ProductItemClick, BlogItemClick, OrderHistoryItemClick {

    public static BottomNavigationView bottomNavigationView;

    public static final String KEY_USER_TO_ACCOUNT = "KEY_USER_TO_ACCOUNT";
    public static final String KEY_USER_TO_CART = "KEY_USER_TO_CART";
    public static final String KEY_USER_TO_MAIN = "KEY_USER_TO_MAIN";
    public static final String USER_NAME_TO_MAIN = "USER_NAME_TO_MAIN";

    public Integer MATK;
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
                    Intent intent1 = getIntent();
                    Bundle bundle1 = intent1.getExtras();
                    if (bundle1 != null) {
                        Integer getData = bundle1.getInt(KEY_USER_TO_MAIN, 0);
                        //Toast.makeText(this, "Show value: " + MATK + userName, Toast.LENGTH_SHORT).show();
                        MATK = getData;
                    }
                    break;
                case R.id.itNotification:
                    selectedFragment = new NotificationBlogFragment();
                    break;
                case R.id.itAccount:

                    selectedFragment = new AccountFragment();
                    Intent intent = getIntent();
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {
                        Integer getData = bundle.getInt(KEY_USER_TO_MAIN, 0);
                        //Toast.makeText(this, "Show value: " + MATK + userName, Toast.LENGTH_SHORT).show();
                        MATK = getData;
                    }
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,selectedFragment).commit();
            return true;
        }
    };

    public Integer getMATK() {

        return MATK;
    }

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



//    @Override
//    public void click(OrderStatus orderStatus) {
//            OrderDetailFragment orderDetailFragment = new OrderDetailFragment();
//    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//
//
//    Bundle bundle = new Bundle();
//        bundle.putSerializable(Constant.SELECTED_ORDER,orderStatus);
//        orderDetailFragment.setArguments(bundle);
//
//        fragmentTransaction.add(R.id.layoutContainer, orderDetailFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }



    @Override
    public void click(Blog b) {
        BlogDetailFragment blogDetailFragment = new BlogDetailFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.SELECTED_BLOG, b);
        blogDetailFragment.setArguments(bundle);

        transaction.add(R.id.layoutContainer, blogDetailFragment);
        transaction.addToBackStack( BlogDetailFragment.class.getName() );
        transaction.commit();
    }

    @Override
    public void click(OrderStatus orderStatus) {

        OrderDetailFragment orderDetailFragment = new OrderDetailFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.SELECTED_ORDER,orderStatus);
        orderDetailFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.layoutContainer, orderDetailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

//    OrderDetailFragment orderDetailFragment = new OrderDetailFragment();
//    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//
//
//    Bundle bundle = new Bundle();
//        bundle.putSerializable(Constant.SELECTED_ORDER,orderStatus);
//        orderDetailFragment.setArguments(bundle);
//
//        fragmentTransaction.add(R.id.layoutContainer, orderDetailFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
}