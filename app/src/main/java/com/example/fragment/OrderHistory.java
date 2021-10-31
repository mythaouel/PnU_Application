package com.example.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.adapter.ViewPager2OrderHistoryAdapter;
import com.example.pnu_application.R;
import com.google.android.material.tabs.TabLayout;

public class OrderHistory extends Fragment {
    private TabLayout tabLayoutAct;
    private ViewPager viewPagerAct;
    private Toolbar     actToolBar;

    View mView;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_order_history, container, false);

        linkViews();
        addEvents();
        return mView;
    }

    private void addEvents() {
        ViewPager2OrderHistoryAdapter adapter= new ViewPager2OrderHistoryAdapter(getChildFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAct.setAdapter(adapter);
        tabLayoutAct.setupWithViewPager(viewPagerAct);
    }
    private void linkViews() {
        tabLayoutAct=mView.findViewById(R.id.tabLayoutAct);
        viewPagerAct=mView.findViewById(R.id.viewPagerAct);

    }


}
