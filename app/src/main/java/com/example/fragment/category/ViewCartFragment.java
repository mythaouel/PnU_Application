package com.example.fragment.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragment.CartFragment;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;

public class ViewCartFragment extends Fragment {

    ImageView imvBack;
    LinearLayout viewCartContainer;
    Button btnOrder1;
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_cart, container, false);

        //Link Views
        imvBack = view.findViewById(R.id.imvBack);
        //btnOrder1 = view.findViewById(R.id.btnDatHang1);
//        viewCartContainer = viewCartContainer.findViewById(R.id.viewCartContainer);

        CartFragment cartFragment = new CartFragment();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.viewCartContainer, cartFragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

//        btnOrder1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        return view;
    }
}