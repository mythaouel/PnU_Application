package com.example.fragment.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.Product;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;

import java.util.ArrayList;

import utils.Constant;

public class DesProductFragment extends Fragment {

    TextView txtDesName, txtDes;
    ImageView imvClose;
    Product product = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_des_product, container, false);

        MainActivity.hideBottomNav();

        //linkViews
        txtDesName = view.findViewById(R.id.txtDesName);
        txtDes = view.findViewById(R.id.txtDes);
        imvClose = view.findViewById(R.id.imvClose);

        Bundle bundle = getArguments();
        if (bundle != null){
            product = (Product) bundle.getSerializable(Constant.SELECTED_ITEM);
            txtDesName.setText(product.getProductName());
            txtDes.setText(Html.fromHtml(product.getProductDescription()));
        }

        //close fragment
        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }
}