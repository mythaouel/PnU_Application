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

        products.add(new Product(R.drawable.pet_fashion_01, "Áo thun có tay màu Hồng cho chó", 62000, "Description"));
        products.add(new Product(R.drawable.pet_fashion_02, "Vòng cổ thú cưng hình cổ áo", 188000, "Description"));
        products.add(new Product(R.drawable.pet_fashion_03, "Mũ len thời trang dễ thương cho chó mèo", 119000, "Description"));
        products.add(new Product(R.drawable.pet_fashion_04, "Thời Trang Chó Mèo nhện Haloween", 125000, "Description"));
        products.add(new Product(R.drawable.pet_fashion_05, "Áo thun liền quần cho chó mèo", 100000, "Description"));
        products.add(new Product(R.drawable.pet_fashion_06, "Quần áo thú cưng thoải mái mùa hè", 63000, "Description"));
        products.add(new Product(R.drawable.pet_fashion_07, "Quần áo cho chó mùa hè cho chó nhỏ", 29000, "Description"));
        products.add(new Product(R.drawable.pet_fashion_08, "Mũ ếch dễ thương cho thú cưng", 50000, "Mũ ếch dễ thương cho thú cưng\", 50000, \"Kích cỡ đường kính:\\n  - Size S: 26-28cm\\n  - Size M: 28-34cm\\nChất liệu: làm từ vải len\\nBạn có thể giữ ấm đầu bé khi trời trở lạnh. Thời tiết Sài Gòn cũng sấp chuyển sang trời lạnh rồi vì vậy hãy sấm ngay một chiếc mũ cho bé nhà mình đi nào."));

        return products;
    }
}