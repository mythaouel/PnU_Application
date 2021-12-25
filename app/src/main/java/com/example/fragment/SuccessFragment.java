package com.example.fragment;

import static com.example.pnu_application.MainActivity.bottomNavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import utils.Constant;

public class SuccessFragment extends Fragment {

    Button btnTiepTuc;
    TextView txtTongCong, txtPTThanhToan, txtNote;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_success,container,false);
        btnTiepTuc = view.findViewById( R.id.btnTiepTuc );
        txtTongCong = view.findViewById( R.id.txtTongCong );
        txtPTThanhToan = view.findViewById( R.id.txtPTThanhToan );
        txtNote = view.findViewById( R.id.txtNote );

        setTextPayment();
        addEvents();

        return view;
    }

    private void setTextPayment() {
        if (Constant.payment_method == 0){
            txtNote.setVisibility( View.VISIBLE );
            txtPTThanhToan.setText( "Thanh toán khi nhận hàng (COD)" );
        }
        else if (Constant.payment_method == 1)
        {
            txtNote.setVisibility( View.GONE );
            txtPTThanhToan.setText( "Thanh toán bằng Thẻ ATM" );
        }
        else if (Constant.payment_method == 2)
        {
            txtNote.setVisibility( View.GONE );
            txtPTThanhToan.setText( "Thanh toán bằng Ví MOMO" );
        }
        txtTongCong.setText( Constant.decimalFormat.format(OrderFragment.total) );
    }

    private void addEvents() {
        //Bấm nút Tiếp tục sẽ quay lại trang chủ
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainer, new CategoryFragment());
                transaction.commit();
                Constant.arrCartProduct.clear();
                bottomNavigationView.setSelectedItemId( R.id.itCategory );
            }
        } );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }
}
