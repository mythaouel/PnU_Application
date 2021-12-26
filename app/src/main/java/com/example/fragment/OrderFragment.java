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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.example.model.Product;
import com.example.pnu_application.Loading_Screen;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDatabaseHelper;
import com.example.pnu_application.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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
    TextView txtGiaTongCong, txtTienTamTinh, txtTongTien, txtPhiShip1, txtPhiShip2, txtHoTen, txtSDT, txtDiaChi, txtNgayGiao, txtPTGiaoHang, txtPTThanhToan;
    ImageView imvBack;
    MaterialCardView cardInfor, cardShipping, cardPayment;

    MainActivity mainActivity;
    int MATK;

    public static double total = 0;

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
        txtPTGiaoHang = view.findViewById( R.id.txtPTGiaoHang );
        txtPTThanhToan = view.findViewById( R.id.txtPTThanhToan );

        cardInfor = view.findViewById( R.id.cardInfor );
        cardShipping = view.findViewById( R.id.cardShipping );
        cardPayment = view.findViewById( R.id.cardPayment );

        MainActivity.hideBottomNav();

        mainActivity = (MainActivity) getActivity();
        MATK = mainActivity.getMATK();

        getShipDateAndCost();
        getPayment();
        configRecyclerView();
        initData();
        calTotal();
        addEvents();
        return view;
    }

    private void getShipDateAndCost() {

        if (Constant.shipping_method == 0){
            Calendar calendar = Calendar.getInstance();
            calendar.add( Calendar.DATE, 3 );
            String fromDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format( calendar.getTime() );
            calendar.add( Calendar.DATE,7 );
            String toDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format( calendar.getTime() );
            txtNgayGiao.setText("Nhận hàng vào: " + fromDate + " - " + toDate );
            txtPTGiaoHang.setText( "Giao hàng tiêu chuẩn" );
            txtPhiShip1.setText( Constant.decimalFormat.format( Constant.PHI_SHIP ) );
            txtPhiShip2.setText( Constant.decimalFormat.format( Constant.PHI_SHIP ) );
        }
        else if (Constant.shipping_method == 1) {
            Calendar calendar = Calendar.getInstance();
            calendar.add( Calendar.DATE, 2 );
            String fromDate = DateFormat.getDateInstance( DateFormat.DEFAULT ).format( calendar.getTime() );
            calendar.add( Calendar.DATE, 4 );
            String toDate = DateFormat.getDateInstance( DateFormat.DEFAULT ).format( calendar.getTime() );
            txtNgayGiao.setText( "Nhận hàng vào: " + fromDate + " - " + toDate );
            txtPTGiaoHang.setText( "Giao hàng nhanh" );
            txtPhiShip1.setText( Constant.decimalFormat.format( Constant.PHI_SHIP_NHANH ) );
            txtPhiShip2.setText( Constant.decimalFormat.format( Constant.PHI_SHIP_NHANH ) );
        }
    }

    private void getPayment() {
        if (Constant.payment_method == 0)
            txtPTThanhToan.setText( "Thanh toán khi nhận hàng" );
        else if (Constant.payment_method == 1)
            txtPTThanhToan.setText( "Thanh toán bằng Thẻ ATM" );
        else if (Constant.payment_method == 2)
            txtPTThanhToan.setText( "Thanh toán bằng Ví MOMO" );
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
        if (Constant.shipping_method == 0)
            total = TongTien + Constant.PHI_SHIP;
        else if (Constant.shipping_method == 1)
            total = TongTien + Constant.PHI_SHIP_NHANH;
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
                Constant.shipping_method = 0;
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
        //Bấm vào khùng thông tin => hiện màn hình cập nhật thông tin
        cardInfor.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace( R.id.fragmentContainer, new UpdateInfoFragment());
                transaction.addToBackStack( null );
                transaction.commit();
            }
        } );
        //Chọn phương thức giao hàng
        cardShipping.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialogShipping();
            }
        } );
        //Chọn phương thức thanh toán
        cardPayment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialogPayment();
            }
        } );
    }

    private void showBottomSheetDialogShipping() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.choose_shipping_method_dialog);
        RadioGroup rdGiaoHang = bottomSheetDialog.findViewById( R.id.rdGiaoHang );
        RadioButton rdGHTieuChuan = bottomSheetDialog.findViewById( R.id.rdGHTieuChuan );
        RadioButton rdGHNhanh = bottomSheetDialog.findViewById( R.id.rdGHNhanh );

        rdGiaoHang.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rdGHTieuChuan:
                        Constant.shipping_method = 0;
                        break;
                    case R.id.rdGHNhanh:
                        Constant.shipping_method = 1;
                        break;
                }
            }
        } );
        //Button xác nhận
        Button btnChoose = bottomSheetDialog.findViewById( R.id.btnChoose );
        btnChoose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getShipDateAndCost();
                calTotal();
                String str = "";
                if (Constant.shipping_method == 0)
                    str = "Giao hàng tiêu chuẩn";
                else if (Constant.shipping_method == 1)
                    str = "Giao hàng nhanh";
                Toast.makeText( getContext(), "Bạn đã chọn " + str, Toast.LENGTH_SHORT ).show();
                bottomSheetDialog.dismiss();
            }
        } );

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

    private void showBottomSheetDialogPayment() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.choose_payment_method_dialog);
        RadioGroup rdThanhToan = bottomSheetDialog.findViewById( R.id.rdThanhToan );
        RadioButton rdCOD = bottomSheetDialog.findViewById( R.id.rdCOD );
        RadioButton rdATM = bottomSheetDialog.findViewById( R.id.rdATM );
        RadioButton rdMOMO = bottomSheetDialog.findViewById( R.id.rdMomo );

        rdThanhToan.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rdCOD:
                        Constant.payment_method = 0;
                        break;
                    case R.id.rdATM:
                        Constant.payment_method = 1;
                        break;
                    case R.id.rdMomo:
                        Constant.payment_method = 2;
                        break;
                }
            }
        } );
        //Button xác nhận
        Button btnChoose = bottomSheetDialog.findViewById( R.id.btnChoose );
        btnChoose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPayment();
                String str = "";
                if (Constant.payment_method == 0)
                    str = "Thanh toán khi nhận hàng";
                else if (Constant.payment_method == 1)
                    str = "Thanh toán bằng Thẻ ATM";
                else if (Constant.payment_method == 2)
                    str = "Thanh toán bằng Ví MOMO";
                Toast.makeText( getContext(), "Bạn đã chọn " + str, Toast.LENGTH_SHORT ).show();
                bottomSheetDialog.dismiss();
            }
        } );

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

    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }
}
