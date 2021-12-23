package com.example.fragment;

import static com.example.pnu_application.MainActivity.bottomNavigationView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.pnu_application.Loading_Screen;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDatabaseHelper;
import com.example.pnu_application.R;
import com.google.android.material.card.MaterialCardView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import utils.Constant;

public class OrderFragment extends Fragment {
    public static final String TAG = OrderFragment.class.getName();

    Button btnDatHang2;
    RecyclerView rcvOrder;
    TextView txtGiaTongCong, txtTienTamTinh, txtTongTien, txtPhiShip1, txtPhiShip2, txtHoTen, txtSDT, txtDiaChi, txtNgayGiao;
    ImageView imvBack;
    MaterialCardView cardInfor;

    MainActivity mainActivity;
    int MATK;

    double total = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_order,container,false);

        btnDatHang2 = view.findViewById( R.id.btnDatHang2 );

        imvBack = view.findViewById( R.id.imvBack );

        rcvOrder = view.findViewById( R.id.rcvOrder );

        txtGiaTongCong = view.findViewById( R.id.txtGiaTongCong );
        txtTongTien = view.findViewById( R.id.txtTongTien );
        txtTienTamTinh = view.findViewById( R.id.txtTienTamTinh );
        txtPhiShip1 = view.findViewById( R.id.txtPhiShip1 );
        txtPhiShip2 = view.findViewById( R.id.txtPhiShip2 );
        txtHoTen = view.findViewById( R.id.txtHoTen );
        txtSDT = view.findViewById( R.id.txtSDT );
        txtDiaChi = view.findViewById( R.id.txtDiaChi );
        txtNgayGiao = view.findViewById( R.id.txtNgayGiao );

        cardInfor = view.findViewById( R.id.cardInfor );

        MainActivity.hideBottomNav();

        mainActivity = (MainActivity) getActivity();
        MATK = mainActivity.getMATK();

        getShipDateAndCost();
        configRecyclerView();
        initData();
        calTotal();
        addEvents();
        return view;
    }

    private void getShipDateAndCost() {
        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.DATE, 3 );
        String fromDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format( calendar.getTime() );
        calendar.add( Calendar.DATE,7 );
        String toDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format( calendar.getTime() );
        txtNgayGiao.setText("Nhận hàng vào: " + fromDate + " - " + toDate );

        txtPhiShip1.setText( Constant.decimalFormat.format( Constant.PHI_SHIP ) );
        txtPhiShip2.setText( Constant.decimalFormat.format( Constant.PHI_SHIP ) );
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
        //Load lên danh sách sản phẩm trong giỏ hàng
        RecyclerViewOrderAdapter adapter = new RecyclerViewOrderAdapter( getContext(), Constant.arrCartProduct);
        rcvOrder.setAdapter( adapter );
        //Load thông tin của khách hàng
        Cursor cursor = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.CUSTOMER_TB_NAME + " WHERE " + MyDatabaseHelper.CUSTOMER_COL_ACT_ID + " = " + MATK );
        while (cursor.moveToNext()){
            txtHoTen.setText( cursor.getString( 1 ) );
            txtDiaChi.setText( cursor.getString( 5 ) );
            txtSDT.setText( cursor.getString( 4 ) );
            Loading_Screen.db.close();
        }
    }

    //Tính tổng tiền
    private void calTotal() {
        double TongTien = 0;
        for (int i = 0; i < Constant.arrCartProduct.size(); i++)
            TongTien += Constant.arrCartProduct.get( i ).getProductPrice() * Constant.arrCartProduct.get( i ).getProductQuantity();
        txtTienTamTinh.setText( Constant.decimalFormat.format( TongTien ));
        total = TongTien + Constant.PHI_SHIP;
        txtTongTien.setText( Constant.decimalFormat.format(total));
        txtGiaTongCong.setText( Constant.decimalFormat.format(total));
    }

    private void addEvents() {
        //Event cho nút Đặt hàng
        btnDatHang2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Save vào database
                String name, status;
                name = txtHoTen.getText().toString();
                status = "Đang lấy hàng";

                //Format date
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String date=dateFormat.format(calendar.getTime());

                int quantity = 0;
                for (int i = 0; i < Constant.arrCartProduct.size(); i++){
                    quantity += Constant.arrCartProduct.get( i ).getProductQuantity();
                }
                boolean flag = Loading_Screen.db.insertOrderData( name, date, status, quantity, total, MATK);
                if (flag){
                    Toast.makeText( getContext(), "PnU xin chân thành cảm ơn.", Toast.LENGTH_SHORT ).show();
                }else{
                    Toast.makeText( getContext(), "Đã xảy ra lỗi gì đó.", Toast.LENGTH_SHORT ).show();
                }

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainer, new SuccessFragment());
                transaction.commit();
                Constant.arrCartProduct.clear();
            }
        } );
        //Event cho nút Back
        imvBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null)
                    getFragmentManager().popBackStack();
            }
        } );

        cardInfor.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainer, new UpdateInfoFragment());
                transaction.commit();
            }
        } );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }
}
