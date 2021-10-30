package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pnu_application.R;


public class CartFragment extends Fragment {
    Button btnDatHang1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        btnDatHang1 = view.findViewById( R.id.btnDatHang1 );
        btnDatHang1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,new OrderFragment());
                transaction.commit();
            }
        } );
        return view;
    }
}