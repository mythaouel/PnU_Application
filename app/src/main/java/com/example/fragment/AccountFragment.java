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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.adapter.AccountLineAdapter;

import com.example.model.AccountLineItem;


import com.example.pnu_application.Loading_Screen;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDatabaseHelper;
import com.example.pnu_application.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class AccountFragment extends Fragment {

    Button btnUpdateInf;
    TextView txtName;
    CircleImageView imvAvatar;

    ListView lvAccount1,lvAccount2,lvAccount3;
    AccountLineAdapter adapter1,adapter2,adapter3;
    ArrayList<AccountLineItem> lineItems1,lineItems2,lineItems3;

    View view;

    int MATK=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_account, container, false);
        linkViews();
        loadData();
        initData();
        addEvents();
        return view;
    }

    private void linkViews() {

        imvAvatar    = view.findViewById(R.id.imvAvatar) ;
        txtName      = view.findViewById(R.id.txtAccountName);
        btnUpdateInf = view.findViewById(R.id.btnUpdateInf);

        lvAccount1   = view.findViewById(R.id.lvAccount1);
        lvAccount2   = view.findViewById(R.id.lvAccount2);
        lvAccount3   = view.findViewById(R.id.lvAccount3);



    }

    private void addEvents() {

        lvAccount1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ChangePass Event
                if(i==0){
                   ChangePassFragment fragment= new ChangePassFragment();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.add(R.id.layoutContainer, fragment).addToBackStack(null) ;
                    transaction.commit();
                }
                //View Order History Event
                if(i==1){
                    OrderHistory fragment= new OrderHistory();
                    FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.fragment_account, fragment);
                    transaction.commit();
                }
            }
        });


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

        lvAccount3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // LogOut Event
                if(i==0){
                    openLogOutDialog();
                }
            }
        });

        //Update Information Event
        btnUpdateInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateInfoFragment fragment= new UpdateInfoFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.layoutContainer, fragment).addToBackStack(null) ;
                transaction.commit();
            }
        });


    }
    private void loadData(){
        Cursor cursor = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.CUSTOMER_TB_NAME + " WHERE " + MyDatabaseHelper.CUSTOMER_COL_ACT_ID + " = " + MATK );
        if(cursor.moveToFirst()) {
            {
                txtName.setText( cursor.getString( 1 ) );
                //Covert to byte array->Bitmap
                byte[] photo= cursor.getBlob(6);
                if(photo==null){
                    imvAvatar.setImageResource(R.drawable.user_avt_default);
                }else{
                    Bitmap bitmap= BitmapFactory.decodeByteArray(photo,0, photo.length);
                    imvAvatar.setImageBitmap(bitmap);
                    Loading_Screen.db.close();
                }
            }
        }
    }
    private void openLogOutDialog(){
        final Dialog dialog= new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.account_custom_dialog);
        Window window= dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes= window.getAttributes();
        windowAttributes.gravity= Gravity.BOTTOM;
        window.setAttributes(windowAttributes);
        dialog.setCancelable(false);
        //LinkView
        Button btnLogOut     = dialog.findViewById(R.id.btnLogOut);
        Button btnCancer     = dialog.findViewById(R.id.btnCancer);

        btnCancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        dialog.show();
    }

    private void initData() {
        //Init Array
        lineItems1 = new ArrayList<AccountLineItem>();
        lineItems2 = new ArrayList<AccountLineItem>();
        lineItems3 = new ArrayList<AccountLineItem>();
        //Add Array
        lineItems1.add(new AccountLineItem(R.drawable.act_ic_key,"Đổi mật khẩu",R.drawable.img));
        lineItems1.add(new AccountLineItem(R.drawable.act_ic_ord,"Lịch sử đơn hàng", R.drawable.img ));

        lineItems2.add(new AccountLineItem(R.drawable.act_ic_gt,"Giới thiệu",R.drawable.img));
        lineItems2.add(new AccountLineItem(R.drawable.act_ic_cs,"Chính sách",R.drawable.img));
        lineItems3.add(new AccountLineItem(R.drawable.act_ic_logout,"Đăng xuất",R.drawable.img));
        //Attach Adapter
        adapter1=new AccountLineAdapter(getContext(),R.layout.account_list_item,lineItems1);
        lvAccount1.setAdapter(adapter1);

        adapter2=new AccountLineAdapter(getContext(),R.layout.account_list_item,lineItems2);
        lvAccount2.setAdapter(adapter2);

        adapter3=new AccountLineAdapter(getContext(),R.layout.account_list_item,lineItems3);
        lvAccount3.setAdapter(adapter3);

    }

}