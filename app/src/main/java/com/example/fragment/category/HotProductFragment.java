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
        products.add(new Product(R.drawable.dog_food_01, "Hạt thức ăn khô cho chó Royal Canin Poodle Puppy", 394000, "Description"));
        products.add(new Product(R.drawable.dog_food_08, "Thức ăn cho chó vị cá biển MEC Wild Taste Ocean Deep Fish", 380000, "Description"));
        products.add(new Product(R.drawable.dog_food_04, "Thức ăn cho chó con vị sữa Classic", 150000, "Description"));


        //Hot Cat Food
        products.add(new Product(R.drawable.cat_food_01, "Hạt khô cho mèo con Royal Canin Kitten", 349000, "Description"));
        products.add(new Product(R.drawable.cat_food_02, "Hạt thức ăn khô cho mèo Royal Canin Renal", 258000, "Description"));
        products.add(new Product(R.drawable.cat_food_08, "Thức Ăn Hạt Cho Mèo Trưởng Thành Catsrang", 422000, "Description"));

        //Hot Pet Toys
        products.add(new Product(R.drawable.pet_toy_07, "Đồ chơi cao su hình xương cá", 21500, "Description"));
        products.add(new Product(R.drawable.pet_toy_04, "Đồ chơi cao su cho Squeaky Dog", 200000, "Description"));
        products.add(new Product(R.drawable.pet_toy_05, "Đồ chơi cho chó mèo hình cá chim", 46000, "Description"));

        //Hot Pet Fashion
        products.add(new Product(R.drawable.pet_fashion_03, "Mũ len thời trang dễ thương cho chó mèo", 119000, "Description"));
        products.add(new Product(R.drawable.pet_fashion_04, "Thời Trang Chó Mèo nhện Haloween", 125000, "Description"));
        products.add(new Product(R.drawable.pet_fashion_08, "Mũ ếch dễ thương cho thú cưng", 50000, "Kích cỡ đường kính:\n  - Size S: 26-28cm\n  - Size M: 28-34cm\nChất liệu: làm từ vải len\nBạn có thể giữ ấm đầu bé khi trời trở lạnh. Thời tiết Sài Gòn cũng sấp chuyển sang trời lạnh rồi vì vậy hãy sấm ngay một chiếc mũ cho bé nhà mình đi nào."));

        return products;
    }
}