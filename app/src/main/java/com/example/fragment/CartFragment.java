package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.RecyclerViewCartAdapter;
import com.example.model.CartProduct;
import com.example.model.Product;
import com.example.pnu_application.R;

import java.util.ArrayList;


public class CartFragment extends Fragment {
    Button btnDatHang1;
    RecyclerView rcvCart;
    ArrayList<CartProduct> arrCartProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        btnDatHang1 = view.findViewById( R.id.btnDatHang1 );
        rcvCart = view.findViewById( R.id.rcvCart );
        initData();
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

    private void initData() {
        ArrayList<CartProduct> arrCartProduct = new ArrayList<>();
        arrCartProduct.add(new CartProduct(R.drawable.cat_food_01, "Hạt khô cho mèo con Royal Canin Kitten", 349000,1));
        arrCartProduct.add(new CartProduct(R.drawable.cat_food_02, "Hạt thức ăn khô cho mèo Royal Canin Renal", 258000,1));
        arrCartProduct.add(new CartProduct(R.drawable.dog_food_07, "Thức ăn cho chó con hạt mềm ZENITH Puppy Chicken & Potato", 275000,1));
        RecyclerViewCartAdapter adapter = new RecyclerViewCartAdapter(getContext(),arrCartProduct);
        rcvCart.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        rcvCart.setAdapter( adapter );
    }
}