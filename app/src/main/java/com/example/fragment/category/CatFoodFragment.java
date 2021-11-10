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

public class CatFoodFragment extends Fragment {

    GridView gvCatFood;
    ArrayList<Product> products;
    ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cat_food, container, false);
        
        gvCatFood = view.findViewById(R.id.gvCatFood);
        productAdapter = new ProductAdapter(getContext(), R.layout.product_item_layout, initData());
        gvCatFood.setAdapter(productAdapter);
        
        return view;
    }

    private ArrayList<Product> initData() {
        products = new ArrayList<>();

        products.add(new Product(R.drawable.cat_food_01, "Hạt khô cho mèo con Royal Canin Kitten", 349000, "Description"));
        products.add(new Product(R.drawable.cat_food_02, "Hạt thức ăn khô cho mèo Royal Canin Renal", 258000, "Description"));
        products.add(new Product(R.drawable.cat_food_03, "Thức ăn hạt WHISKAS cho mèo", 210000, "Description"));
        products.add(new Product(R.drawable.cat_food_04, "Thức ăn cho mèo MININO Vị cá Ngừ ", 290000, "Description"));
        products.add(new Product(R.drawable.cat_food_05, "Thức ăn cho mèo ME-O 1,2KG", 124000, "Description"));
        products.add(new Product(R.drawable.cat_food_06, "Thức ăn cho mèo Kitekat Vị Cá Thu", 110000, "Description"));
        products.add(new Product(R.drawable.cat_food_07, "Hạt Reflex Food Chicken cho mèo", 360000, "Description"));
        products.add(new Product(R.drawable.cat_food_08, "Thức Ăn Hạt Cho Mèo Trưởng Thành Catsrang", 422000, "Description"));

        return products;
    }
}