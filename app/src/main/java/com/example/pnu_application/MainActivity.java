package com.example.pnu_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fragment.AccountFragment;
import com.example.fragment.CartFragment;
import com.example.fragment.CategoryFragment;
import com.example.fragment.HomeFragment;
import com.example.fragment.NotificationBlogFragment;
import com.example.fragment.NotificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabCategory;
    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navContainer);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();
        bottomNavigationView.setOnItemSelectedListener(navListener);
    }
    private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;
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
}