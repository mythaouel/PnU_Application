package com.example.fragment.category;

import static com.example.pnu_application.MainActivity.bottomNavigationView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.ProductAdapter;
import com.example.fragment.CartFragment;
import com.example.model.CartProduct;
import com.example.model.Product;
import com.example.model.ProductItemClick;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.nex3z.notificationbadge.NotificationBadge;

import java.io.Serializable;
import java.util.ArrayList;

import utils.Constant;


public class ProductDetailsFragment extends Fragment {

    Product product = null;
    ImageView imvThumbDetails, imvBack;
    TextView txtNameDetails, txtPriceDetails, txtDescription;

    LinearLayout viewAll;

    Button btnAddToCart;
    NotificationBadge countQty;
    FrameLayout btnCart;

    GridView gvLikeProduct;
    ArrayList<Product> products;
//    ProductAdapter productAdapter;
    ProductItemClick productItemClick;

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

        viewAll = view.findViewById(R.id.viewALL);

        btnAddToCart = view.findViewById(R.id.btnAddToCart);
        btnCart = view.findViewById( R.id.btnCart );
        countQty = view.findViewById( R.id.countQty );

        gvLikeProduct = view.findViewById(R.id.gvLikeProduct);

        gvLikeProduct.setFocusable(false);

        initDataGridview();

        gvLikeProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                productItemClick = (ProductItemClick) getActivity();
                if (productItemClick != null){
                    productItemClick.click(products.get(i));
                }
            }
        });

//        //layout Bạn có thể thích
//        HotProductFragment hotProductFragment = new HotProductFragment();
//        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
//        ft.add(R.id.layoutLikeProduct, hotProductFragment);
//        ft.addToBackStack(null);
//        ft.commit();

        changeCountQty();

        Bundle bundle = getArguments();
        if (bundle != null){
            product = (Product) bundle.getSerializable(Constant.SELECTED_ITEM);
            imvThumbDetails.setImageResource(product.getProductThumbnail());
            txtNameDetails.setText(product.getProductName());
            txtPriceDetails.setText(Constant.decimalFormat.format( product.getProductPrice() ));
            txtDescription.setText(Html.fromHtml(product.getProductDescription()));
        }
        addEvents();
        return view;
    }

    private void initDataGridview() {
        ProductAdapter productAdapter = new ProductAdapter(getContext(), R.layout.product_item_layout, initData());
        gvLikeProduct.setAdapter(productAdapter);
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

    private void changeCountQty() {
        if(Constant.arrCartProduct!=null){
            int countSl = 0;
            for (int i = 0; i<Constant.arrCartProduct.size(); i++){
                countSl += Constant.arrCartProduct.get( i ).getProductQuantity();
            }
            countQty.setText( String.valueOf(countSl));
        }
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
                                Constant.arrCartProduct.get( i ).setProductQuantity( Constant.arrCartProduct.get( i ).getProductQuantity() + 1 );
                                flag = true;
                            }
                        }
                        //Sản phẩm chưa có trong giỏ hàng => thêm mới
                        if (!flag){
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
                openCart();
            }
        } );

        //View product description
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DesProductFragment desProductFragment = new DesProductFragment();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                MainActivity.bundle.putSerializable(Constant.SELECTED_ITEM, product);
                desProductFragment.setArguments(MainActivity.bundle);
                ft.add(R.id.layoutContainer, desProductFragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        //Back to the previous fragment
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void openCart() {
        //remove current fragment when add new fragment
        getParentFragmentManager().beginTransaction().remove(ProductDetailsFragment.this).commit();

        CartFragment cartFragment = new CartFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContainer, cartFragment);
        transaction.addToBackStack(null);

        transaction.commit();

        bottomNavigationView.setSelectedItemId( R.id.itCart );
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
                openCart();
                bottomSheetDialog.dismiss();

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

    //reference the method that shows Bottom Navigation Bar when returning to the previous screen
    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }
}
