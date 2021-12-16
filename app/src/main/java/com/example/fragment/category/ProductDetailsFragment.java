package com.example.fragment.category;

import static com.example.pnu_application.MainActivity.bottomNavigationView;

import android.content.Intent;
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
import com.example.model.CartProduct;
import com.example.model.Product;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.nex3z.notificationbadge.NotificationBadge;

import utils.Constant;


public class ProductDetailsFragment extends Fragment {

    Product product = null;
    ImageView imvThumbDetails, imvBack;
    TextView txtNameDetails, txtPriceDetails, txtDescription;

    Button btnAddToCart;
    NotificationBadge countQty;
    FrameLayout btnCart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        //reference the method that hides Bottom Navigation Bar
        MainActivity.hideBottomNav();

        imvThumbDetails = view.findViewById(R.id.imvThumbDetails);
        imvBack = view.findViewById(R.id.imvBack);
        txtNameDetails = view.findViewById(R.id.txtNameDetails);
        txtPriceDetails = view.findViewById(R.id.txtPriceDetails);
        txtDescription = view.findViewById(R.id.txtDescription);
        btnAddToCart = view.findViewById(R.id.btnAddToCart);
        btnCart = view.findViewById( R.id.btnCart );
        countQty = view.findViewById( R.id.countQty );

        changeCountQty();

        Bundle bundle = getArguments();
        if (bundle != null){
            product = (Product) bundle.getSerializable(Constant.SELECTED_ITEM);
            imvThumbDetails.setImageResource(product.getProductThumbnail());
            txtNameDetails.setText(product.getProductName());
            txtPriceDetails.setText(Constant.decimalFormat.format( product.getProductPrice() ));
            txtDescription.setText(product.getProductDescription());
        }
        addEvents();
        return view;
    }

    private void addEvents() {
        //Event cho Button Thêm sản phẩm vào giỏ hàng
        btnAddToCart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //Giỏ hàng không trống
                    if (Constant.arrCartProduct.size() > 0){
                        boolean flag = false;
                        //Kiểm tra sản phẩm đã có sẵn trong giỏ hàng chưa => nếu có gắn flag = true và tăng số lượng
                        for (int i = 0; i < Constant.arrCartProduct.size(); i++){
                            if(Constant.arrCartProduct.get( i ).getProductId() == product.getProductId()){
                                if(Constant.arrCartProduct.get( i ).getProductQuantity() < 10 ) {
                                    //Kiểm tra số lượng sản phẩm đó trong giỏ hàng hiện tại có hơn 10 không
                                    Constant.arrCartProduct.get( i ).setProductQuantity( Constant.arrCartProduct.get( i ).getProductQuantity() + 1 );
                                    //show bottom sheet dialog
                                    showBottomSheetDialog();
                                }
                                else {Toast.makeText(getContext(), "Không thể mua một sản phẩm với số lượng hơn 10", Toast.LENGTH_SHORT ).show();}
                                flag = true;
                            }
                        }
                        //Sản phẩm chưa có trong giỏ hàng => thêm mới
                        if (flag == false){
                            CartProduct cartProduct = new CartProduct( product.getProductId(),
                                    product.getProductThumbnail(),
                                    product.getProductName(),
                                    product.getProductPrice(), 1);
                            Constant.arrCartProduct.add( cartProduct );
                            //show bottom sheet dialog
                            showBottomSheetDialog();
                        }
                    }
                    //Giỏ hàng trống
                    else{
                        CartProduct cartProduct = new CartProduct( product.getProductId(),
                                product.getProductThumbnail(),
                                product.getProductName(),
                                product.getProductPrice(), 1);
                        Constant.arrCartProduct.add( cartProduct );
                        //show bottom sheet dialog
                        showBottomSheetDialog();
                    }
                    //update số lượng sản phẩm trong giỏ hàng
                    changeCountQty();
                }catch (Exception ex){
                    Toast.makeText(getContext(), "Xảy ra lỗi", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        //Event show Order screen when click in button cart
        btnCart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainer,new CartFragment());
                transaction.addToBackStack(null);
                transaction.commit();
                bottomNavigationView.setSelectedItemId( R.id.itCart );
            }
        } );

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void showBottomSheetDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.view_cart_dialog);
        ImageView imvThumb = bottomSheetDialog.findViewById(R.id.imvProductThumb);
        TextView txtName = bottomSheetDialog.findViewById(R.id.txtProductName);
        TextView txtPrice = bottomSheetDialog.findViewById(R.id.txtProductPrice);

        Product p = null;
        Bundle bundleDialog = getArguments();
        if (bundleDialog != null){
            p = (Product) bundleDialog.getSerializable(Constant.SELECTED_ITEM);
            imvThumb.setImageResource(p.getProductThumbnail());
            txtName.setText(p.getProductName());
            txtPrice.setText(Constant.decimalFormat.format(p.getProductPrice()));
        }

        Button btnViewCart;
        btnViewCart = bottomSheetDialog.findViewById(R.id.btnViewCart);
        btnViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Replace CartFragment when click button
//                CartFragment cartFragment = new CartFragment();
//                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.layoutContainer, cartFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
                //Show BottomNavigationView

                //Set item Cart in BottomNavigationView as selected

                //Close Dialog
//                bottomSheetDialog.dismiss();
                
                openCartFragment();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        //Close Dialog
        ImageView imvClose = bottomSheetDialog.findViewById(R.id.imvClose);
        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.show();
    }

    private void openCartFragment() {
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

    //reference the method that shows Bottom Navigation Bar when returning to the previous screen
    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }
}
