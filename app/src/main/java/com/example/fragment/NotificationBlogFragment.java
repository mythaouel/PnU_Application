package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.adapter.ViewPager2NotificationAdapter;
import com.example.pnu_application.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class NotificationBlogFragment extends Fragment {

    View view;

    ViewPager2 viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_notification_blog_screen,container,false);

        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        FragmentManager fragmentManager = getChildFragmentManager();
        ViewPager2NotificationAdapter adapter = new ViewPager2NotificationAdapter(fragmentManager,getLifecycle());
        adapter.createFragment(0);
        adapter.createFragment(1);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator( tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position){
                    case 0:
                        tab.setText( "Thông báo");
                        break;
                    case 1:
                        tab.setText( "Blog");
                        break;
                }
            }
        } ).attach();
        return view;
    }

}
