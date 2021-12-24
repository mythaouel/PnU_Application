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

public class CatFoodFragment extends Fragment {

    GridView gvCatFood;
    ImageView imvThumbDetails;
    TextView txtNameDetails, txtPriceDetails, txtDescription;
    ArrayList<Product> products;
    ProductAdapter productAdapter;
    ProductItemClick productItemClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cat_food, container, false);

        imvThumbDetails = view.findViewById(R.id.imvThumbDetails);
        txtNameDetails = view.findViewById(R.id.txtNameDetails);
        txtPriceDetails = view.findViewById(R.id.txtPriceDetails);
        txtDescription = view.findViewById(R.id.txtDescription);
        
        gvCatFood = view.findViewById(R.id.gvCatFood);
        productAdapter = new ProductAdapter(getContext(), R.layout.product_item_layout, initData());
        gvCatFood.setAdapter(productAdapter);

        gvCatFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        products.add(new Product("sp0001",R.drawable.cat_food_01, getString(R.string.cat_food_01), 349, getString(R.string.des_cat_food_01)));
        products.add(new Product("sp0002",R.drawable.cat_food_02, getString(R.string.cat_food_02), 258, getString(R.string.des_cat_food_02)));
        products.add(new Product("sp0003",R.drawable.cat_food_03, getString(R.string.cat_food_03), 210, getString(R.string.des_cat_food_03)));
        products.add(new Product("sp0004",R.drawable.cat_food_04, getString(R.string.cat_food_04), 290, getString(R.string.des_cat_food_04)));
        products.add(new Product("sp0005",R.drawable.cat_food_05, getString(R.string.cat_food_05), 124, getString(R.string.des_cat_food_05)));
        products.add(new Product("sp0006",R.drawable.cat_food_06, getString(R.string.cat_food_06), 110, getString(R.string.des_cat_food_06)));
        products.add(new Product("sp0007",R.drawable.cat_food_07, getString(R.string.cat_food_07), 360, getString(R.string.des_cat_food_07)));
        products.add(new Product("sp0008",R.drawable.cat_food_08, getString(R.string.cat_food_08), 422, getString(R.string.des_cat_food_08)));

        return products;
    }
}