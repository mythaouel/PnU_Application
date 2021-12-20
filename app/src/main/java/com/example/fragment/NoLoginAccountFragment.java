package com.example.fragment;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.adapter.AccountLineAdapter;
import com.example.fragment.AboutUsFragment;
import com.example.fragment.ChangePassFragment;
import com.example.fragment.OrderHistory;
import com.example.fragment.PolicyFragment;
import com.example.fragment.UpdateInfoFragment;
import com.example.model.AccountLineItem;
import com.example.pnu_application.Loading_Screen;
import com.example.pnu_application.MyDatabaseHelper;
import com.example.pnu_application.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class NoLoginAccountFragment extends Fragment {

    TextView txtLogin;


    ListView lvAccount1,lvAccount2;
    AccountLineAdapter adapter1,adapter2;
    ArrayList<AccountLineItem> lineItems1,lineItems2;

    View view;

    int MATK=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_account_no_login, container, false);
        linkViews();
        initData();
        addEvents();
        return view;
    }

    private void linkViews() {


        lvAccount1   = view.findViewById(R.id.lvActNoLogin1);
        lvAccount2   = view.findViewById(R.id.lvActNoLogin2);

        txtLogin     = view.findViewById(R.id.txtLogin);

    }

    private void addEvents() {


        lvAccount2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //View About Us
                if(i==0){
                    AboutUsFragment fragment= new AboutUsFragment();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.add(R.id.layoutContainer, fragment).addToBackStack(null) ;
                    transaction.commit();
                }
                //View Policy Events
                else if(i==1){
                    PolicyFragment fragment= new PolicyFragment();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.add(R.id.layoutContainer, fragment).addToBackStack(null) ;
                    transaction.commit();
                }

            }
        });



        //Update Information Event
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UpdateInfoFragment fragment= new UpdateInfoFragment();
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                transaction.replace(R.id.layoutContainer, fragment).addToBackStack(null) ;
//                transaction.commit();
            }
        });

    }



    private void initData() {
        //Init Array
        lineItems1 = new ArrayList<AccountLineItem>();
        lineItems2 = new ArrayList<AccountLineItem>();

        //Add Array
        lineItems1.add(new AccountLineItem(R.drawable.act_ic_key,"Đổi mật khẩu",R.drawable.img));
        lineItems1.add(new AccountLineItem(R.drawable.act_ic_ord,"Lịch sử đơn hàng", R.drawable.img ));

        lineItems2.add(new AccountLineItem(R.drawable.act_ic_gt,"Giới thiệu",R.drawable.img));
        lineItems2.add(new AccountLineItem(R.drawable.act_ic_cs,"Chính sách",R.drawable.img));

        //Attach Adapter
        adapter1=new AccountLineAdapter(getContext(),R.layout.account_list_item,lineItems1);
        lvAccount1.setAdapter(adapter1);

        adapter2=new AccountLineAdapter(getContext(),R.layout.account_list_item,lineItems2);
        lvAccount2.setAdapter(adapter2);


    }

}