package com.example.fragment.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
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

        products.add(new Product("sp0009",R.drawable.dog_food_01, getString(R.string.dog_food_01), 394, getString(R.string.des_dog_food_01)));
        products.add(new Product("sp0010",R.drawable.dog_food_02, getString(R.string.dog_food_02), 350, getString(R.string.des_dog_food_02)));
        products.add(new Product("sp0011",R.drawable.dog_food_03, getString(R.string.dog_food_03), 290, getString(R.string.des_dog_food_03)));
        products.add(new Product("sp0012",R.drawable.dog_food_04, getString(R.string.dog_food_04), 150, getString(R.string.des_dog_food_04)));
        products.add(new Product("sp0013",R.drawable.dog_food_05, getString(R.string.dog_food_05), 294, getString(R.string.des_dog_food_05)));
        products.add(new Product("sp0014",R.drawable.dog_food_06, getString(R.string.dog_food_06), 355, getString(R.string.des_dog_food_06)));
        products.add(new Product("sp0015",R.drawable.dog_food_07, getString(R.string.dog_food_07), 275, getString(R.string.des_dog_food_07)));
        products.add(new Product("sp0016",R.drawable.dog_food_08, getString(R.string.dog_food_08), 380, getString(R.string.des_dog_food_08)));

        return products;
    }
}