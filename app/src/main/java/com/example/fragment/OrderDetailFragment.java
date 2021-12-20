package com.example.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.OrderDetail;

import com.example.pnu_application.R;

import utils.Constant;

public class OrderDetailFragment extends Fragment {

    View mView;

    TextView txtOrderId,txtOrderDate;
    ImageView imvBack;

    OrderDetail orderDetail=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragement_order_detail, container, false);
        linkViews();

        loadData();
        addEvents();

        return  mView;
    }

    private void linkViews() {
        txtOrderDate=mView.findViewById(R.id.txtOrderDetailDate);
        txtOrderId=mView.findViewById(R.id.txtOrderDetailId);

        imvBack= mView.findViewById(R.id.imvOrderDetailBack);
    }
    private void loadData() {
        Bundle bundle = getArguments();
        if (bundle != null){
            orderDetail = (OrderDetail) bundle.getSerializable( Constant.SELECTED_ORDER );
            txtOrderDate.setText(orderDetail.getOrderDateTime());
            txtOrderId.setText(orderDetail.getOrderId());
        }
    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}