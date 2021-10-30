package com.example.fragment.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.adapter.ProductAdapter;
import com.example.model.Product;
import com.example.pnu_application.R;

import java.util.ArrayList;

public class PetToyFragment extends Fragment {

    GridView gvPetToy;
    ArrayList<Product> products;
    ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_toy, container, false);

        gvPetToy = view.findViewById(R.id.gvPetToy);
        productAdapter = new ProductAdapter(getContext(), R.layout.product_item_layout, initData());
        gvPetToy.setAdapter(productAdapter);

        return view;
    }

    private ArrayList<Product> initData() {
        products = new ArrayList<>();

        products.add(new Product(R.drawable.pet_toy_01, "Đồ chơi bóng mặt chó", 25000));
        products.add(new Product(R.drawable.pet_toy_02, "Con gà đồ chơi cho chó mèo", 15000));
        products.add(new Product(R.drawable.pet_toy_03, "Đồ chơi hình tôm hùm cho chó mèo", 24000));
        products.add(new Product(R.drawable.pet_toy_04, "Đồ chơi cao su cho Squeaky Dog", 200000));
        products.add(new Product(R.drawable.pet_toy_05, "Đồ chơi cho chó mèo hình cá chim", 46000));
        products.add(new Product(R.drawable.pet_toy_06, "Bóng lồng chuột cho chó mèo", 20000));
        products.add(new Product(R.drawable.pet_toy_07, "Đồ chơi cao su hình xương cá", 21500));
        products.add(new Product(R.drawable.pet_toy_08, "Đồ chơi xương bông phát ra tiếng", 15000));

        return products;
    }
}