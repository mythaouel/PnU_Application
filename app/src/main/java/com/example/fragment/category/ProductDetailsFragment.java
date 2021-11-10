package com.example.fragment.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.Product;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import utils.Constant;


public class ProductDetailsFragment extends Fragment {

    Product product = null;
    ImageView imvThumbDetails;
    TextView txtNameDetails, txtPriceDetails, txtDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        MainActivity.hideBottomNav();

        imvThumbDetails = view.findViewById(R.id.imvThumbDetails);
        txtNameDetails = view.findViewById(R.id.txtNameDetails);
        txtPriceDetails = view.findViewById(R.id.txtPriceDetails);
        txtDescription = view.findViewById(R.id.txtDescription);

        Bundle bundle = getArguments();
        if (bundle != null){
            product = (Product) bundle.getSerializable(Constant.SELECTED_ITEM);
            imvThumbDetails.setImageResource(product.getProductThumbnail());
            txtNameDetails.setText(product.getProductName());
            txtPriceDetails.setText(String.valueOf(product.getProductPrice()));
            txtDescription.setText(product.getProductDescription());
        }
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }
}
