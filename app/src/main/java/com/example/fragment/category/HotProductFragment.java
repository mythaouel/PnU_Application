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
import java.util.List;

public class HotProductFragment extends Fragment {

    GridView gvHotProduct;
    ImageView imvThumbDetails;
    TextView txtNameDetails, txtPriceDetails, txtDescription;
    ArrayList<Product> products;
    ProductAdapter productAdapter;
    ProductItemClick productItemClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_product, container, false);
        gvHotProduct = view.findViewById(R.id.gvHotProduct);

        imvThumbDetails = view.findViewById(R.id.imvThumbDetails);
        txtNameDetails = view.findViewById(R.id.txtNameDetails);

        txtPriceDetails = view.findViewById(R.id.txtPriceDetails);

        txtDescription = view.findViewById(R.id.txtDescription);

        productAdapter = new ProductAdapter(getContext(), R.layout.product_item_layout, initData());
        gvHotProduct.setAdapter(productAdapter);

        gvHotProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        //Hot Dog Food
        products.add(new Product("sp0009",R.drawable.dog_food_01, getString(R.string.dog_food_01), 394, "Description"));
        products.add(new Product("sp0016",R.drawable.dog_food_08, getString(R.string.dog_food_08), 380, "Description"));
        products.add(new Product("sp0012",R.drawable.dog_food_04, getString(R.string.dog_food_04), 150, "Description"));


        //Hot Cat Food
        products.add(new Product("sp0001",R.drawable.cat_food_01, getString(R.string.cat_food_01), 349, "Description"));
        products.add(new Product("sp0002",R.drawable.cat_food_02, getString(R.string.cat_food_02), 258, "Description"));
        products.add(new Product("sp0008",R.drawable.cat_food_08, getString(R.string.cat_food_08), 422, "Description"));

        //Hot Pet Toys
        products.add(new Product("sp0023",R.drawable.pet_toy_07, getString(R.string.pet_toy_07), 21.5, "Description"));
        products.add(new Product("sp0020",R.drawable.pet_toy_04, getString(R.string.pet_toy_04), 200, "Description"));
        products.add(new Product("sp0021",R.drawable.pet_toy_05, getString(R.string.pet_toy_05), 46, "Description"));

        //Hot Pet Fashion
        products.add(new Product("sp0027",R.drawable.pet_fashion_03, getString(R.string.pet_toy_03), 119, "Description"));
        products.add(new Product("sp0028",R.drawable.pet_fashion_04, getString(R.string.pet_toy_04), 125, "Description"));
        products.add(new Product("sp0032",R.drawable.pet_fashion_08, getString(R.string.pet_toy_08), 50, "Kích cỡ đường kính:\n  - Size S: 26-28cm\n  - Size M: 28-34cm\nChất liệu: làm từ vải len\nBạn có thể giữ ấm đầu bé khi trời trở lạnh. Thời tiết Sài Gòn cũng sấp chuyển sang trời lạnh rồi vì vậy hãy sấm ngay một chiếc mũ cho bé nhà mình đi nào."));

        return products;
    }

}