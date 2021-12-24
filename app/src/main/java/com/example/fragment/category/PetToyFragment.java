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

        products.add(new Product("sp0017",R.drawable.pet_toy_01, getString(R.string.pet_toy_01), 25, "Description"));
        products.add(new Product("sp0018",R.drawable.pet_toy_02, getString(R.string.pet_toy_02), 15, "Description"));
        products.add(new Product("sp0019",R.drawable.pet_toy_03, getString(R.string.pet_toy_03), 24, "Description"));
        products.add(new Product("sp0020",R.drawable.pet_toy_04, getString(R.string.pet_toy_04), 200, "Description"));
        products.add(new Product("sp0021",R.drawable.pet_toy_05, getString(R.string.pet_toy_05), 46, "Description"));
        products.add(new Product("sp0022",R.drawable.pet_toy_06, getString(R.string.pet_toy_06), 20, "Description"));
        products.add(new Product("sp0023",R.drawable.pet_toy_07, getString(R.string.pet_toy_07), 21.5, "Description"));
        products.add(new Product("sp0024",R.drawable.pet_toy_08, getString(R.string.pet_toy_08), 15, "Description"));

        return products;
    }
}