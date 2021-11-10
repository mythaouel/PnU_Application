package com.example.fragment.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.ProductAdapter;
import com.example.model.Product;
import com.example.model.ProductItemClick;
import com.example.pnu_application.R;

import java.util.ArrayList;

public class DogFoodFragment extends Fragment {

    GridView gvDogFood;
    ImageView imvThumbDetails;
    TextView txtNameDetails, txtPriceDetails, txtDescription;
    ArrayList<Product> products;
    ProductAdapter productAdapter;
    ProductItemClick productItemClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dog_food, container, false);

        imvThumbDetails = view.findViewById(R.id.imvThumbDetails);
        txtNameDetails = view.findViewById(R.id.txtNameDetails);
        txtPriceDetails = view.findViewById(R.id.txtPriceDetails);
        txtDescription = view.findViewById(R.id.txtDescription);

        gvDogFood = view.findViewById(R.id.gvDogFood);
        productAdapter = new ProductAdapter(getContext(), R.layout.product_item_layout, initData());
        gvDogFood.setAdapter(productAdapter);

        gvDogFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                productItemClick = (ProductItemClick) getActivity();
                if (productItemClick != null){
                    productItemClick.click(products.get(i));
                }
            }
        });

        return view;
    }

    private ArrayList<Product> initData() {
        products = new ArrayList<>();

        products.add(new Product(R.drawable.dog_food_01, "Hạt thức ăn khô cho chó Royal Canin Poodle Puppy", 394, "Description"));
        products.add(new Product(R.drawable.dog_food_02, "Hạt khô cho chó Poodle trưởng thành Royal Canin Poodle Adult", 350, "Description"));
        products.add(new Product(R.drawable.dog_food_03, "Thức ăn cho chó trưởng thành SMARTHEART", 290, "Description"));
        products.add(new Product(R.drawable.dog_food_04, "Thức ăn cho chó con vị sữa Classic", 150, "Description"));
        products.add(new Product(R.drawable.dog_food_05, "Thức ăn cho chó MOSHM", 294, "Description"));
        products.add(new Product(R.drawable.dog_food_06, "Thức ăn cho chó trưởng thành Pedigree vị bò", 355, "Description"));
        products.add(new Product(R.drawable.dog_food_07, "Thức ăn cho chó con hạt mềm ZENITH Puppy Chicken & Potato", 275, "Description"));
        products.add(new Product(R.drawable.dog_food_08, "Thức ăn cho chó vị cá biển MEC Wild Taste Ocean Deep Fish", 380, "Description"));

        return products;
    }
}