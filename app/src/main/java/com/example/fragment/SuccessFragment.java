package com.example.fragment;

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

import com.example.pnu_application.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SuccessFragment extends Fragment {

//    Button btnTiepTucMua;
//    BottomNavigationView navContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_success,container,false);
//        btnTiepTucMua = view.findViewById( R.id.btnTiepTucMua );
//        navContainer = view.findViewById( R.id.navContainer );
//        btnTiepTucMua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragmentContainer,new CategoryFragment());
//                transaction.commit();
//            }
//        } );
        return view;
    }
}
