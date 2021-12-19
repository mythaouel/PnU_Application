package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SuccessFragment extends Fragment {

    Button btnTiepTuc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_success,container,false);
        btnTiepTuc = view.findViewById( R.id.btnTiepTuc );
        //Bấm nút Tiếp tục sẽ quay lại trang chủ
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent( getContext(),MainActivity.class );
                startActivity( intent );
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
