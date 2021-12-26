package com.example.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragment.HomeFragment;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;

public class PromoFragment extends Fragment {
    ImageView imvBack;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_promotion, container, false);
        imvBack=view.findViewById(R.id.imvPromoBack);
        MainActivity.hideBottomNav();
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  getActivity().onBackPressed();
                MainActivity.showBottomNav();
            }
        });
        return view;
    }
}
