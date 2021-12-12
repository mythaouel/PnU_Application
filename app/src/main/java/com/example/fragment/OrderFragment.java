package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.RecyclerViewOrderAdapter;
import com.example.model.CartProduct;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;

import java.util.ArrayList;

import utils.Constant;

public class OrderFragment extends Fragment {

    Button btnDatHang2;
    RecyclerView rcvOrder;
    TextView txtGiaTongCong, txtTienTamTinh, txtTongTien, txtPhiShip1, txtPhiShip2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_order,container,false);

        btnDatHang2 = view.findViewById( R.id.btnDatHang2 );

        rcvOrder = view.findViewById( R.id.rcvOrder );

        txtGiaTongCong = view.findViewById( R.id.txtGiaTongCong );
        txtTongTien = view.findViewById( R.id.txtTongTien );
        txtTienTamTinh = view.findViewById( R.id.txtTienTamTinh );
        txtPhiShip1 = view.findViewById( R.id.txtPhiShip1 );
        txtPhiShip2 = view.findViewById( R.id.txtPhiShip2 );

        //txtPhiShip1.setText( String. format( "%.3f", Constant.PHI_SHIP ) + " " + "đ" );
        txtPhiShip1.setText( Constant.decimalFormat.format( Constant.PHI_SHIP ) );
        //txtPhiShip2.setText( String. format( "%.3f", Constant.PHI_SHIP ) + " " + "đ" );
        txtPhiShip2.setText( Constant.decimalFormat.format( Constant.PHI_SHIP ) );

        MainActivity.hideBottomNav();

        configRecyclerView();
        initData();
        addEvents();
        calTotal();
        return view;
    }

    private void calTotal() {
            double TongTien = 0;
            for (int i = 0; i < Constant.arrCartProduct.size(); i++)
                TongTien += Constant.arrCartProduct.get( i ).getProductPrice() * Constant.arrCartProduct.get( i ).getProductQuantity();
            //txtTienTamTinh.setText( String. format( "%.3f", TongTien ) + " " + "đ" );
            txtTienTamTinh.setText( Constant.decimalFormat.format( TongTien ));
            //txtTongTien.setText( String. format( "%.3f", TongTien + Constant.PHI_SHIP ) + " " + "đ" );
            txtTongTien.setText( Constant.decimalFormat.format( TongTien + Constant.PHI_SHIP ));
            //txtGiaTongCong.setText( String. format( "%.3f", TongTien + Constant.PHI_SHIP ) + " " + "đ" );
            txtGiaTongCong.setText( Constant.decimalFormat.format( TongTien + Constant.PHI_SHIP ));
    }

    private void configRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvOrder.setLayoutManager( manager );
        DividerItemDecoration decoration = new DividerItemDecoration( getContext(),manager.getOrientation() );
        rcvOrder.addItemDecoration( decoration );
        rcvOrder.setHasFixedSize( true );
        rcvOrder.setItemAnimator( new DefaultItemAnimator() );
    }

    private void initData() {
        RecyclerViewOrderAdapter adapter = new RecyclerViewOrderAdapter( getContext(), Constant.arrCartProduct);
        rcvOrder.setAdapter( adapter );
    }

    private void addEvents() {
        btnDatHang2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,new SuccessFragment());
                transaction.commit();
                Constant.arrCartProduct.clear();
            }
        } );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }
}
