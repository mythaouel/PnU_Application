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