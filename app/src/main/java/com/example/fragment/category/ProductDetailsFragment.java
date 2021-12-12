package com.example.fragment.category;

import static com.example.pnu_application.MainActivity.bottomNavigationView;

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
    ImageView imvThumbDetails;
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
            //txtPriceDetails.setText(String. format("%.3f", product.getProductPrice())+ " " + "đ");
            txtPriceDetails.setText(Constant.decimalFormat.format( product.getProductPrice() ));
            txtDescription.setText(product.getProductDescription());
        }
        addEvents();
        return view;
    }


    private void addEvents() {
        btnAddToCart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //Giỏ hàng không trống
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
                    }
                    //Giỏ hàng trống
                    else{
                        CartProduct cartProduct = new CartProduct( product.getProductId(),
                                product.getProductThumbnail(),
                                product.getProductName(),
                                product.getProductPrice(), 1);
                        Constant.arrCartProduct.add( cartProduct );
                    }
                    //show bottom sheet dialog
                    showBottomSheetDialog();
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
//                Intent intent = new Intent(getContext(), Order_Screen.class );
//                startActivity( intent );
            }
        } );
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
            //txtPrice.setText(String. format("%.3f", p.getProductPrice())+ " " + "đ");
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
