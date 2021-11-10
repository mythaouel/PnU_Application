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

public class PetToyFragment extends Fragment {

    GridView gvPetToy;
    ImageView imvThumbDetails;
    TextView txtNameDetails, txtPriceDetails, txtDescription;
    ArrayList<Product> products;
    ProductAdapter productAdapter;
    ProductItemClick productItemClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_toy, container, false);

        imvThumbDetails = view.findViewById(R.id.imvThumbDetails);
        txtNameDetails = view.findViewById(R.id.txtNameDetails);
        txtPriceDetails = view.findViewById(R.id.txtPriceDetails);
        txtDescription = view.findViewById(R.id.txtDescription);

        gvPetToy = view.findViewById(R.id.gvPetToy);
        productAdapter = new ProductAdapter(getContext(), R.layout.product_item_layout, initData());
        gvPetToy.setAdapter(productAdapter);

        gvPetToy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        products.add(new Product(R.drawable.pet_toy_01, "Đồ chơi bóng mặt chó", 25, "Description"));
        products.add(new Product(R.drawable.pet_toy_02, "Con gà đồ chơi chó mèo", 15, "Description"));
        products.add(new Product(R.drawable.pet_toy_03, "Đồ chơi hình tôm hùm cho chó mèo", 24, "Description"));
        products.add(new Product(R.drawable.pet_toy_04, "Đồ chơi cao su cho Squeaky Dog", 200, "Description"));
        products.add(new Product(R.drawable.pet_toy_05, "Đồ chơi cho chó mèo hình cá chim", 46, "Description"));
        products.add(new Product(R.drawable.pet_toy_06, "Bóng lồng chuột cho chó mèo", 20, "Description"));
        products.add(new Product(R.drawable.pet_toy_07, "Đồ chơi cao su hình xương cá", 21.5, "Description"));
        products.add(new Product(R.drawable.pet_toy_08, "Đồ chơi xương bông phát ra tiếng", 15, "Description"));

        return products;
    }
}