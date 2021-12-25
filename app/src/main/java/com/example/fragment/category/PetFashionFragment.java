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

public class PetFashionFragment extends Fragment {

    GridView gvPetFashion;
    ImageView imvThumbDetails;
    TextView txtNameDetails, txtPriceDetails, txtDescription;
    ArrayList<Product> products;
    ProductAdapter productAdapter;
    ProductItemClick productItemClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_fashion, container, false);

        imvThumbDetails = view.findViewById(R.id.imvThumbDetails);
        txtNameDetails = view.findViewById(R.id.txtNameDetails);
        txtPriceDetails = view.findViewById(R.id.txtPriceDetails);
        txtDescription = view.findViewById(R.id.txtDescription);

        gvPetFashion = view.findViewById(R.id.gvPetFashion);
        productAdapter = new ProductAdapter(getContext(), R.layout.product_item_layout, initData());
        gvPetFashion.setAdapter(productAdapter);

        gvPetFashion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        products.add(new Product("sp0025",R.drawable.pet_fashion_01, getString(R.string.pet_fashion_01), 62, getString(R.string.des_pet_fashion_01)));
        products.add(new Product("sp0026",R.drawable.pet_fashion_02, getString(R.string.pet_fashion_02), 188, getString(R.string.des_pet_fashion_02)));
        products.add(new Product("sp0027",R.drawable.pet_fashion_03, getString(R.string.pet_fashion_03), 119, getString(R.string.des_pet_fashion_03)));
        products.add(new Product("sp0028",R.drawable.pet_fashion_04, getString(R.string.pet_fashion_04), 125, getString(R.string.des_pet_fashion_04)));
        products.add(new Product("sp0029",R.drawable.pet_fashion_05, getString(R.string.pet_fashion_05), 100, getString(R.string.des_pet_fashion_05)));
        products.add(new Product("sp0030",R.drawable.pet_fashion_06, getString(R.string.pet_fashion_06), 63, getString(R.string.des_pet_fashion_06)));
        products.add(new Product("sp0031",R.drawable.pet_fashion_07, getString(R.string.pet_fashion_07), 29, getString(R.string.des_pet_fashion_07)));
        products.add(new Product("sp0032",R.drawable.pet_fashion_08, getString(R.string.pet_fashion_08), 50, getString(R.string.des_pet_fashion_08)));

        return products;
    }
}