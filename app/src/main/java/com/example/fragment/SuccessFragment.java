package com.example.fragment;

import static com.example.pnu_application.MainActivity.bottomNavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import utils.Constant;

public class SuccessFragment extends Fragment {

    Button btnTiepTuc;
    TextView txtTongCong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_success,container,false);
        btnTiepTuc = view.findViewById( R.id.btnTiepTuc );
        txtTongCong = view.findViewById( R.id.txtTongCong );

        txtTongCong.setText( Constant.decimalFormat.format(OrderFragment.total) );
        //Bấm nút Tiếp tục sẽ quay lại trang chủ
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainer, new HomeFragment());
                transaction.commit();
                Constant.arrCartProduct.clear();
                bottomNavigationView.setSelectedItemId( R.id.itHome );
            }
        } );
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }
}
