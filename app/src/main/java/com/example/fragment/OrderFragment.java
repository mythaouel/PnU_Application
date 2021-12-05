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
    ArrayList<CartProduct> arrOrderProduct;
    TextView txtGiaTongCong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_order,container,false);
        btnDatHang2 = view.findViewById( R.id.btnDatHang2 );
        rcvOrder = view.findViewById( R.id.rcvOrder );
        txtGiaTongCong = view.findViewById( R.id.txtGiaTongCong );
        MainActivity.hideBottomNav();
        configRecyclerView();
        initData();
        //calTotal();

        btnDatHang2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,new SuccessFragment());
                transaction.commit();
            }
        } );
        return view;
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
        ArrayList<CartProduct> arrOrderProduct = new ArrayList<>();
//        arrOrderProduct.add(new CartProduct(R.drawable.cat_food_01, "Hạt khô cho mèo con Royal Canin Kitten", 349000,1));
//        arrOrderProduct.add(new CartProduct(R.drawable.cat_food_02, "Hạt thức ăn khô cho mèo Royal Canin Renal", 258000,1));
//        arrOrderProduct.add(new CartProduct(R.drawable.dog_food_07, "Thức ăn cho chó con hạt mềm ZENITH Puppy Chicken & Potato", 275000,1));
        RecyclerViewOrderAdapter adapter = new RecyclerViewOrderAdapter( getContext(), Constant.arrCartProduct);
        rcvOrder.setAdapter( adapter );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }
}
