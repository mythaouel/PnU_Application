package com.example.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import com.example.adapter.RecyclerViewCartAdapter;
import com.example.eventbus.TotalCalculator;
import com.example.model.CartProduct;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDatabaseHelper;
import com.example.pnu_application.R;

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
        RecyclerViewCartAdapter adapter = new RecyclerViewCartAdapter(getContext(), Constant.arrCartProduct);
        rcvCart.setAdapter( adapter );
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
                if (AccountFragment.db.getCount( MyDatabaseHelper.CUSTOMER_TB_NAME ) == 0){
//                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                    transaction.add(R.id.layoutCartContainer, new UpdateInfoFragment()).addToBackStack("tag") ;
//                    transaction.commit();
                    Toast.makeText( getContext(), "Bạn cần cập nhật thông tin cá nhân trước khi đặt hàng", Toast.LENGTH_SHORT ).show();
                }
                else {
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, new OrderFragment());
                    transaction.addToBackStack( OrderFragment.TAG );
                    transaction.commit();
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