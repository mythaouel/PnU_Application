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
        //Kiểm tra số lượng sản phẩm trong giỏ hàng để hiện giao diện phù hợp
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
        //Data sản phẩm trong giỏ hàng
        RecyclerViewCartAdapter adapter = new RecyclerViewCartAdapter(getContext(), Constant.arrCartProduct);
        rcvCart.setAdapter( adapter );
        //Data list sản phẩm gợi ý khi giỏ hàng trống
        ProductAdapter productAdapter = new ProductAdapter(getContext(), R.layout.product_item_layout, getData());
        gvSuggestProduct.setAdapter(productAdapter);
    }

    private ArrayList<Product> getData() {
        products = new ArrayList<>();

        //Dog Food
        products.add(new Product("sp0016",R.drawable.dog_food_08, "Thức ăn cho chó vị cá biển MEC Wild Taste Ocean Deep Fish", 380, "Description"));
        products.add(new Product("sp0014",R.drawable.dog_food_06, "Thức ăn cho chó trưởng thành Pedigree vị bò", 355, "Description"));
        //Cat Food
        products.add(new Product("sp0008",R.drawable.cat_food_08, "Thức Ăn Hạt Cho Mèo Trưởng Thành Catsrang", 422, "Description"));
        products.add(new Product("sp0007",R.drawable.cat_food_07, "Hạt Reflex Food Chicken cho mèo", 360, "Description"));
        //Pet Toys
        products.add(new Product("sp0017",R.drawable.pet_toy_01, "Đồ chơi bóng mặt chó", 25, "Description"));
        //Pet Fashion
        products.add(new Product("sp0026",R.drawable.pet_fashion_02, "Vòng cổ thú cưng hình cổ áo", 188, "Description"));

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
            txtTongSL.setText(countSl + " sản phẩm");
        }
    }

    private void addEvents() {
        //event chuyển sang order
        btnDatHang1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Kiểm tra người dùng đã cập nhật thông tin cá nhân chưa nếu chưa thì hiện thông báo yêu cầu cập nhật
                Cursor cursor = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.ACCOUNT_TB_NAME + " WHERE " + MyDatabaseHelper.ACCOUNT_COL_STATUS + " = 1");
                if (cursor!=null && cursor.moveToFirst()){
                    MATK= cursor.getInt(0);
                    Cursor cursor2 = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.CUSTOMER_TB_NAME + " WHERE " + MyDatabaseHelper.CUSTOMER_COL_ACT_ID + " = " + MATK );
                    if (cursor2!=null && cursor2.moveToFirst()){
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainer, new OrderFragment());
                        transaction.addToBackStack( OrderFragment.TAG );
                        transaction.commit();
                    }else{
                        Toast.makeText( getContext(), "Bạn cần cập nhật thông tin cá nhân trước khi đặt hàng", Toast.LENGTH_SHORT ).show();
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.add(R.id.fragmentContainer, new UpdateInfoFragment());
                        transaction.addToBackStack( null );
                        transaction.commit();
                    }
                }else{
                    Toast.makeText( getContext(), "Bạn cần đăng nhập", Toast.LENGTH_SHORT ).show();
                    Intent intent = new Intent(getContext(), SignIn_Screen.class);
                    startActivity(intent);
                }
            }
        } );


        imvDeleteAll.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle( "Xóa tất cả?" );
                builder.setMessage( "Xóa tất cả sản phẩm trong giỏ hàng?" );
                builder.setPositiveButton( "Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Constant.arrCartProduct.clear();
                        CartFragment.cartView.setVisibility( View.GONE );
                        CartFragment.emptyCartView.setVisibility( View.VISIBLE );
                    }
                } );
                builder.setNegativeButton( "Hủy", new DialogInterface.OnClickListener() {
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