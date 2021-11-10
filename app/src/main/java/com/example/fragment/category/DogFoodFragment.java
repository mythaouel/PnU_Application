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

public class DogFoodFragment extends Fragment {

    GridView gvDogFood;
    ArrayList<Product> products;
    ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dog_food, container, false);

        gvDogFood = view.findViewById(R.id.gvDogFood);
        productAdapter = new ProductAdapter(getContext(), R.layout.product_item_layout, initData());
        gvDogFood.setAdapter(productAdapter);
        return view;
    }

    private ArrayList<Product> initData() {
        products = new ArrayList<>();

        products.add(new Product(R.drawable.dog_food_01, "Hạt thức ăn khô cho chó Royal Canin Poodle Puppy", 394000, "Description"));
        products.add(new Product(R.drawable.dog_food_02, "Hạt khô cho chó Poodle trưởng thành Royal Canin Poodle Adult", 350000, "Description"));
        products.add(new Product(R.drawable.dog_food_03, "Thức ăn cho chó trưởng thành SMARTHEART", 290000, "Description"));
        products.add(new Product(R.drawable.dog_food_04, "Thức ăn cho chó con vị sữa Classic", 150000, "Description"));
        products.add(new Product(R.drawable.dog_food_05, "Thức ăn cho chó MOSHM", 294000, "Description"));
        products.add(new Product(R.drawable.dog_food_06, "Thức ăn cho chó trưởng thành Pedigree vị bò", 355000, "Description"));
        products.add(new Product(R.drawable.dog_food_07, "Thức ăn cho chó con hạt mềm ZENITH Puppy Chicken & Potato", 275000, "Description"));
        products.add(new Product(R.drawable.dog_food_08, "Thức ăn cho chó vị cá biển MEC Wild Taste Ocean Deep Fish", 380000, "Description"));

        return products;
    }
}