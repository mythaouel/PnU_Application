package com.example.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.model.Blog;
import com.example.model.OrderStatus;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;

import utils.Constant;

public class OrderDetailFragment extends Fragment {

    View mView;

    TextView txtOrderDate;

    OrderStatus orderStatus=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragement_order_detail, container, false);
        linkViews();
        Bundle bundle = getArguments();
        if (bundle != null){
            orderStatus = (OrderStatus) bundle.getSerializable( Constant.SELECTED_ORDER );
            txtOrderDate.setText(orderStatus.getOrderDateTime());
        }

        return  mView;
    }

    private void linkViews() {
        txtOrderDate=mView.findViewById(R.id.txtOrderDate);
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
}