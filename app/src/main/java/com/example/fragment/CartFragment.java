package com.example.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.ProductAdapter;
import com.example.adapter.RecyclerViewCartAdapter;
import com.example.eventbus.TotalCalculator;
import com.example.model.CartProduct;
import com.example.model.Product;
import com.example.model.ProductItemClick;
import com.example.pnu_application.Loading_Screen;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDatabaseHelper;
import com.example.pnu_application.R;
import com.example.pnu_application.SignIn_Screen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import utils.Constant;


public class CartFragment extends Fragment {
    Button btnDatHang1, btnApDung;
    RecyclerView rcvCart;
    TextView txtTongCong, txtTongSL;
    ImageView imvDeleteAll;
    EditText edtGiamGia;
    GridView gvSuggestProduct;

    ArrayList<Product> products;
    ProductItemClick productItemClick;

    int MATK;

    public static LinearLayout emptyCartView;
    public static ConstraintLayout cartView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        btnDatHang1 = view.findViewById( R.id.btnDatHang1 );
        btnApDung = view.findViewById( R.id.btnApDung );

        rcvCart = view.findViewById( R.id.rcvCart );

        edtGiamGia = view.findViewById( R.id.edtGiamGia );

        txtTongCong = view.findViewById( R.id.txtTongCong );
        txtTongSL = view.findViewById( R.id.txtTongSL );

        imvDeleteAll = view.findViewById( R.id.imvDeleteAll );

        gvSuggestProduct = view.findViewById( R.id.gvSuggestProduct );

        emptyCartView = view.findViewById( R.id.emptyCartView );
        cartView = view.findViewById( R.id.cartView );

        MainActivity.showBottomNav();
        //Ki???m tra s??? l?????ng s???n ph???m trong gi??? h??ng ????? hi???n giao di???n ph?? h???p
        if(Constant.arrCartProduct.size() > 0){
            cartView.setVisibility( View.VISIBLE );
            emptyCartView.setVisibility( View.GONE );
        }else{
            cartView.setVisibility( View.GONE );
            emptyCartView.setVisibility( View.VISIBLE );
        }

        configRecyclerView();
        initData();
        calTotal();
        countQty();
        addEvents();

        gvSuggestProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    private void configRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager( getContext(),LinearLayoutManager.VERTICAL,false );
        rcvCart.setLayoutManager( manager );
        DividerItemDecoration decoration = new DividerItemDecoration( getContext(),manager.getOrientation() );
        rcvCart.addItemDecoration( decoration );
        rcvCart.setHasFixedSize( true );
        rcvCart.setItemAnimator( new DefaultItemAnimator() );
    }

    private void initData() {
        //Data s???n ph???m trong gi??? h??ng
        RecyclerViewCartAdapter adapter = new RecyclerViewCartAdapter(getContext(), Constant.arrCartProduct);
        rcvCart.setAdapter( adapter );
        //Data list s???n ph???m g???i ?? khi gi??? h??ng tr???ng
        ProductAdapter productAdapter = new ProductAdapter(getContext(), R.layout.product_item_layout, getData());
        gvSuggestProduct.setAdapter(productAdapter);
    }

    private ArrayList<Product> getData() {
        products = new ArrayList<>();

        //Dog Food
        products.add(new Product("sp0016",R.drawable.dog_food_08, getString(R.string.dog_food_08), 380, getString(R.string.des_dog_food_08)));
        products.add(new Product("sp0014",R.drawable.dog_food_06, getString(R.string.dog_food_06), 355, getString(R.string.des_dog_food_06)));
        //Cat Food
        products.add(new Product("sp0008",R.drawable.cat_food_08, getString(R.string.cat_food_08), 422, getString(R.string.des_cat_food_08)));
        products.add(new Product("sp0007",R.drawable.cat_food_07, getString(R.string.cat_food_07), 360, getString(R.string.des_cat_food_07)));
        //Pet Toys
        products.add(new Product("sp0017",R.drawable.pet_toy_01, getString(R.string.pet_toy_01), 25, getString(R.string.des_pet_toy_01)));
        //Pet Fashion
        products.add(new Product("sp0026",R.drawable.pet_fashion_02, getString(R.string.pet_fashion_02), 188, getString(R.string.des_pet_fashion_02)));

        return products;
    }

    private void calTotal() {
        if (Constant.arrCartProduct != null){
            double TongTien = 0;
            for (int i = 0; i<Constant.arrCartProduct.size();i++)
                TongTien+= Constant.arrCartProduct.get( i ).getProductPrice() * Constant.arrCartProduct.get( i ).getProductQuantity();
            txtTongCong.setText(Constant.decimalFormat.format( TongTien ));
        }
    }

    private void countQty() {
        if(Constant.arrCartProduct!=null){
            int countSl = 0;
            for (int i = 0; i<Constant.arrCartProduct.size(); i++){
                countSl += Constant.arrCartProduct.get( i ).getProductQuantity();
            }
            txtTongSL.setText(countSl + " s???n ph???m");
        }
    }

    private void addEvents() {
        //event chuy???n sang order
        btnDatHang1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ki???m tra ng?????i d??ng ???? c???p nh???t th??ng tin c?? nh??n ch??a n???u ch??a th?? hi???n th??ng b??o y??u c???u c???p nh???t
                Cursor cursor = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.ACCOUNT_TB_NAME + " WHERE " + MyDatabaseHelper.ACCOUNT_COL_STATUS + " = 1");
                if (cursor!=null && cursor.moveToFirst()){
                    MATK= cursor.getInt(0);
                    Cursor cursor2 = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.CUSTOMER_TB_NAME + " WHERE " + MyDatabaseHelper.CUSTOMER_COL_ACT_ID + " = " + MATK );
                    if (cursor2!=null && cursor2.moveToFirst()){
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.add(R.id.fragmentContainer, new OrderFragment(), "orderFragment");
                        transaction.addToBackStack( OrderFragment.TAG );
                        transaction.commit();
                        Constant.shipping_method = 0;
                        Constant.payment_method = 0;
                    }else{
                        Toast.makeText( getContext(), "B???n c???n c???p nh???t th??ng tin c?? nh??n tr?????c khi ?????t h??ng", Toast.LENGTH_SHORT ).show();
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.add(R.id.fragmentContainer, new UpdateInfoFragment());
                        transaction.addToBackStack( null );
                        transaction.commit();
                    }
                }else{
                    Toast.makeText( getContext(), "B???n c???n ????ng nh???p", Toast.LENGTH_SHORT ).show();
                    Intent intent = new Intent(getContext(), SignIn_Screen.class);
                    startActivity(intent);
                }
            }
        } );

        imvDeleteAll.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle( "X??a t???t c????" );
                builder.setMessage( "X??a t???t c??? s???n ph???m trong gi??? h??ng?" );
                builder.setPositiveButton( "X??a", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Constant.arrCartProduct.clear();
                        CartFragment.cartView.setVisibility( View.GONE );
                        CartFragment.emptyCartView.setVisibility( View.VISIBLE );
                    }
                } );
                builder.setNegativeButton( "H???y", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                } );
                builder.show();
            }
        } );
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register( this );
    }
    @Override
    public void onStop() {
        EventBus.getDefault().unregister( this );
        super.onStop();
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void TotalCalculator(TotalCalculator event){
        if (event != null){
            calTotal();
            countQty();
        }
    }
}