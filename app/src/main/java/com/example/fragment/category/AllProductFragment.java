package com.example.fragment.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.adapter.ProductAdapter;
import com.example.model.Product;
import com.example.model.ProductItemClick;
import com.example.pnu_application.R;

import java.util.ArrayList;

public class AllProductFragment extends Fragment {

    GridView gvAllProduct;
    ArrayList<Product> products;
    ProductAdapter productAdapter;
    ProductItemClick productItemClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_product, container, false);

        gvAllProduct = view.findViewById(R.id.gvAllProduct);
        productAdapter = new ProductAdapter(getContext(), R.layout.product_item_layout, initData());
        gvAllProduct.setAdapter(productAdapter);

        gvAllProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                productItemClick = (ProductItemClick) getParentFragment();
                if (productItemClick != null){
                    productItemClick.click(products.get(i));
                }
            }
        });

        return view;
    }

    private ArrayList<Product> initData() {
        products = new ArrayList<>();

        //Dog Food
        products.add(new Product(R.drawable.dog_food_01, "Hạt thức ăn khô cho chó Royal Canin Poodle Puppy", 394000));
        products.add(new Product(R.drawable.dog_food_02, "Hạt khô cho chó Poodle trưởng thành Royal Canin Poodle Adult", 350000));
        products.add(new Product(R.drawable.dog_food_03, "Thức ăn cho chó trưởng thành SMARTHEART", 290000));
        products.add(new Product(R.drawable.dog_food_04, "Thức ăn cho chó con vị sữa Classic", 150000));
        products.add(new Product(R.drawable.dog_food_05, "Thức ăn cho chó MOSHM", 294000));
        products.add(new Product(R.drawable.dog_food_06, "Thức ăn cho chó trưởng thành Pedigree vị bò", 355000));
        products.add(new Product(R.drawable.dog_food_07, "Thức ăn cho chó con hạt mềm ZENITH Puppy Chicken & Potato", 275000));
        products.add(new Product(R.drawable.dog_food_08, "Thức ăn cho chó vị cá biển MEC Wild Taste Ocean Deep Fish", 380000));

        //Cat Food
        products.add(new Product(R.drawable.cat_food_01, "Hạt khô cho mèo con Royal Canin Kitten", 349000));
        products.add(new Product(R.drawable.cat_food_02, "Hạt thức ăn khô cho mèo Royal Canin Renal", 258000));
        products.add(new Product(R.drawable.cat_food_03, "Thức ăn hạt WHISKAS cho mèo", 210000));
        products.add(new Product(R.drawable.cat_food_04, "Thức ăn cho mèo MININO Vị cá Ngừ ", 290000));
        products.add(new Product(R.drawable.cat_food_05, "Thức ăn cho mèo ME-O 1,2KG", 124000));
        products.add(new Product(R.drawable.cat_food_06, "Thức ăn cho mèo Kitekat Vị Cá Thu", 110000));
        products.add(new Product(R.drawable.cat_food_07, "Hạt Reflex Food Chicken cho mèo", 360000));
        products.add(new Product(R.drawable.cat_food_08, "Thức Ăn Hạt Cho Mèo Trưởng Thành Catsrang", 422000));

        //Pet Toys
        products.add(new Product(R.drawable.pet_toy_01, "Đồ chơi bóng mặt chó", 25000));
        products.add(new Product(R.drawable.pet_toy_02, "Con gà đồ chơi chó mèo", 15000));
        products.add(new Product(R.drawable.pet_toy_03, "Đồ chơi hình tôm hùm cho chó mèo", 24000));
        products.add(new Product(R.drawable.pet_toy_04, "Đồ chơi cao su cho Squeaky Dog", 200000));
        products.add(new Product(R.drawable.pet_toy_05, "Đồ chơi cho chó mèo hình cá chim", 46000));
        products.add(new Product(R.drawable.pet_toy_06, "Bóng lồng chuột cho chó mèo", 20000));
        products.add(new Product(R.drawable.pet_toy_07, "Đồ chơi cao su hình xương cá", 21500));
        products.add(new Product(R.drawable.pet_toy_08, "Đồ chơi xương bông phát ra tiếng", 15000));

        //Pet Fashion
        products.add(new Product(R.drawable.pet_fashion_01, "Áo thun có tay màu Hồng cho chó", 62000));
        products.add(new Product(R.drawable.pet_fashion_02, "Vòng cổ thú cưng hình cổ áo", 188000));
        products.add(new Product(R.drawable.pet_fashion_03, "Mũ len thời trang dễ thương cho chó mèo", 119000));
        products.add(new Product(R.drawable.pet_fashion_04, "Thời Trang Chó Mèo nhện Haloween", 125000));
        products.add(new Product(R.drawable.pet_fashion_05, "Áo thun liền quần cho chó mèo", 100000));
        products.add(new Product(R.drawable.pet_fashion_06, "Quần áo thú cưng thoải mái mùa hè", 63000));
        products.add(new Product(R.drawable.pet_fashion_07, "Quần áo cho chó mùa hè cho chó nhỏ", 29000));
        products.add(new Product(R.drawable.pet_fashion_08, "Mũ ếch dễ thương cho thú cưng", 50000));

        return products;
    }



}