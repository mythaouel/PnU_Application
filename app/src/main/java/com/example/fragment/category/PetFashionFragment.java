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

public class PetFashionFragment extends Fragment {

    GridView gvPetFashion;
    ArrayList<Product> products;
    ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_fashion, container, false);

        gvPetFashion = view.findViewById(R.id.gvPetFashion);
        productAdapter = new ProductAdapter(getContext(), R.layout.product_item_layout, initData());
        gvPetFashion.setAdapter(productAdapter);

        return view;
    }

    private ArrayList<Product> initData() {
        products = new ArrayList<>();

        products.add(new Product(R.drawable.pet_fashion_01, "Áo thun có tay màu Hồng cho chó", 62000));
        products.add(new Product(R.drawable.pet_fashion_02, "Vòng cổ thú cưng hình cổ áo", 188000));
        products.add(new Product(R.drawable.pet_fashion_03, "Mũ len thời trang dễ thương cho chó mèo", 119000));
        products.add(new Product(R.drawable.pet_fashion_04, "Thời Trang Chó Mèo nhện Haloween", 125000));
        products.add(new Product(R.drawable.pet_fashion_05, "Áo thun liền quần cho chó mèo", 100000));
        products.add(new Product(R.drawable.pet_fashion_06, "Quần áo thú cưng thoải mái mùa hè", 63000));
        products.add(new Product(R.drawable.pet_fashion_07, "Quần áo cho chó mùa hè cho chó nhỏ", 29000));
        products.add(new Product(R.drawable.pet_fashion_08, "Mũ ếch dễ thương cho thú cưng", 50000));

        return products;
    }
}