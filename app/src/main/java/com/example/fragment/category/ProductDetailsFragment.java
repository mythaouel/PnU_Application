package com.example.fragment.category;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment.CartFragment;
import com.example.fragment.OrderFragment;
import com.example.model.CartProduct;
import com.example.model.Product;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDbCartHelper;
import com.example.pnu_application.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import utils.Constant;


public class ProductDetailsFragment extends Fragment {

    Product product = null;
    ImageView imvThumbDetails;
    TextView txtNameDetails, txtPriceDetails, txtDescription;

    MyDbCartHelper db;
    Button btnAddToCart;
    NotificationBadge countQty;
    FrameLayout btnGioHang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        //reference the method that hides Bottom Navigation Bar
        MainActivity.hideBottomNav();

        imvThumbDetails = view.findViewById(R.id.imvThumbDetails);
        txtNameDetails = view.findViewById(R.id.txtNameDetails);
        txtPriceDetails = view.findViewById(R.id.txtPriceDetails);
        txtDescription = view.findViewById(R.id.txtDescription);
        btnAddToCart = view.findViewById( R.id.btnAddToCart );
        countQty = view.findViewById( R.id.countQty );
        changeCountQty();

        Bundle bundle = getArguments();
        if (bundle != null){
            product = (Product) bundle.getSerializable(Constant.SELECTED_ITEM);
            imvThumbDetails.setImageResource(product.getProductThumbnail());
            txtNameDetails.setText(product.getProductName());
            txtPriceDetails.setText(String. format("%.3f", product.getProductPrice())+ " " + "đ");
            txtDescription.setText(product.getProductDescription());
        }
        AddEvents();
        return view;
    }

    private void AddEvents() {
        btnAddToCart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (Constant.arrCartProduct.size() > 0){
                        boolean flag = false;
                        for (int i = 0; i<Constant.arrCartProduct.size(); i++){
                            if(Constant.arrCartProduct.get( i ).getProductId() == product.getProductId()){
                                Constant.arrCartProduct.get( i ).setProductQuantity( Constant.arrCartProduct.get( i ).getProductQuantity() + 1 );
                                flag = true;
                            }
                        }
                        if (flag == false){
                            CartProduct cartProduct = new CartProduct( product.getProductId(),
                                    product.getProductThumbnail(),
                                    product.getProductName(),
                                    product.getProductPrice(), 1);
                            Constant.arrCartProduct.add( cartProduct );
                        }
                    }else{
                        CartProduct cartProduct = new CartProduct( product.getProductId(),
                                product.getProductThumbnail(),
                                product.getProductName(),
                                product.getProductPrice(), 1);
                        Constant.arrCartProduct.add( cartProduct );
                    }
                    changeCountQty();
                    Toast.makeText(getContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT ).show();
                }catch (Exception ex){
                    Toast.makeText(getContext(), "Xảy ra lỗi", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }

    private void changeCountQty() {
        if(Constant.arrCartProduct!=null){
            int countSl = 0;
            for (int i = 0; i<Constant.arrCartProduct.size(); i++){
                countSl += Constant.arrCartProduct.get( i ).getProductQuantity();
            }
            countQty.setText( String.valueOf(countSl));
        }
    }

//    private void AddEvents() {
//        btnAddToCart.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int productImage = imvThumbDetails.getId();
//                String productName = txtNameDetails.getText().toString();
//                double productPrice = Double.parseDouble( txtPriceDetails.getText().toString().substring( 0,txtPriceDetails.length() - 2 ).replace( ",","." ) );
//                int productQty = 1;
//
//
//
//                boolean flag = db.insertData( productImage, productName, productPrice, productQty);
//                if (flag){
//                    Toast.makeText( getActivity(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT ).show();
//                }else{
//                    Toast.makeText( getActivity(), "Không thêm được", Toast.LENGTH_SHORT ).show();
//                }
//            }
//
//            private byte[] convertPhoto() {
//                    BitmapDrawable drawable = (BitmapDrawable) imvThumbDetails.getDrawable();
//                    Bitmap bitmap = drawable.getBitmap();
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    bitmap.compress( Bitmap.CompressFormat.PNG, 100, outputStream );
//                    return outputStream.toByteArray();
//                }
//        } );
//    }

    //reference the method that shows Bottom Navigation Bar when returning to the previous screen
    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }
}
