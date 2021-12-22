package com.example.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.adapter.ViewPager2NotificationAdapter;
import com.example.adapter.ViewPager2OrderHistoryAdapter;
import com.example.adapter.ViewPager2ProductAdapter;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class OrderHistoryFragment extends Fragment {


    ViewPager2 viewPager;
    private TabLayout tabLayout;

    ImageView imvBack;

    View mView;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_order_history, container, false);

        linkViews();
        bindViewPage2();
        addEvents();
        return mView;
    }

    private void linkViews() {
        viewPager = mView.findViewById(R.id.viewPagerAct);
        tabLayout = mView.findViewById(R.id.tabLayoutAct);

        imvBack   = mView.findViewById(R.id.imvOrderHistoryBack);
    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }


    private void bindViewPage2() {

        FragmentManager fragmentManager = getChildFragmentManager();
        ViewPager2OrderHistoryAdapter adapter = new ViewPager2OrderHistoryAdapter(fragmentManager, getLifecycle());
        adapter.createFragment(0);
        adapter.createFragment(1);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Đang xử lý");
                        break;
                    case 1:
                        tab.setText("Hoàn thành");
                        break;
                }
            }
        }).attach();

        //Change Tab when swiping
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }
}
