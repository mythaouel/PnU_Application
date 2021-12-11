package com.example.fragment;

import android.database.Cursor;
import android.media.metrics.Event;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.RecyclerViewCartAdapter;
import com.example.eventbus.TotalCalculator;
import com.example.model.CartProduct;
import com.example.model.Product;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDbCartHelper;
import com.example.pnu_application.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;

import utils.Constant;


public class CartFragment extends Fragment {
    Button btnDatHang1;
    RecyclerView rcvCart;
    TextView txtTongCong;
    ImageView imvPlus, imvMinus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        btnDatHang1 = view.findViewById( R.id.btnDatHang1 );
        rcvCart = view.findViewById( R.id.rcvCart );
        txtTongCong = view.findViewById( R.id.txtTongCong );
        imvMinus = view.findViewById( R.id.imvMinus );
        imvPlus = view.findViewById( R.id.imvPlus );

        configRecyclerView();
        initData();
        calTotal();
        addEvents();

        return view;
    }

    private void configRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager( getContext(),LinearLayoutManager.VERTICAL,false );
        rcvCart.setLayoutManager( manager );
        rcvCart.setHasFixedSize( true );
        rcvCart.setItemAnimator( new DefaultItemAnimator() );
    }

    private void initData() {
        ArrayList<CartProduct> arrCartProduct = new ArrayList<>();
        RecyclerViewCartAdapter adapter = new RecyclerViewCartAdapter(getContext(), Constant.arrCartProduct);
        rcvCart.setAdapter( adapter );
    }

    private void calTotal() {
        if (Constant.arrCartProduct != null){
            double TongTien = 0;
            for (int i = 0; i<Constant.arrCartProduct.size();i++)
                TongTien+= Constant.arrCartProduct.get( i ).getProductPrice() * Constant.arrCartProduct.get( i ).getProductQuantity();
            txtTongCong.setText(String. format("%.3f", TongTien)+ " " + "đ");
        }
        else {
            txtTongCong.setText("0đ");
        }
    }

    private void addEvents() {
        //event chuyển sang order
        btnDatHang1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,new OrderFragment());
                transaction.commit();
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
        }
    }
}