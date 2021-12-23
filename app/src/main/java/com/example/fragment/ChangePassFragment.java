package com.example.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pnu_application.Loading_Screen;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.MyDatabaseHelper;
import com.example.pnu_application.R;
import com.example.pnu_application.SignIn_Screen;
import com.google.android.material.textfield.TextInputLayout;

import utils.Constant;

public class ChangePassFragment extends Fragment {

    View mView;

    EditText edtPassNow,edtNewPass,edtRepass;
    TextInputLayout nowpassWrapper,newpassWrapper,repassWrapper;
    Button   btnChange;
    ImageView imvBack;

    MainActivity mainActivity;

    int MATK;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_change_pass, container, false);

        //Lấy mã Khách hàng đang đăng nhập hiện
        mainActivity = (MainActivity) getActivity();
        MATK = mainActivity.getMATK();

        linkViews();
        MainActivity.hideBottomNav();
        addEvent();
        return mView;

    }

    private void linkViews() {

        edtPassNow=mView.findViewById(R.id.edtActNowPass);
        edtNewPass=mView.findViewById(R.id.edtActNewPass);
        edtRepass=mView.findViewById(R.id.edtActRePass);

        nowpassWrapper= mView.findViewById(R.id.nowpassWrapper);
        newpassWrapper= mView.findViewById(R.id.newpassWrapper);
        repassWrapper= mView.findViewById(R.id.repassWrapper);


        btnChange=mView.findViewById(R.id.btnChange);

        imvBack= mView.findViewById(R.id.imvChangePassBack);

    }

    private void addEvent() {

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newPass = edtNewPass.getText().toString().trim();

                if (checkValidation() ){
                    boolean flag= Loading_Screen.db.updatePassword(newPass,MATK);
                    if(flag==true){
                        openSuccessDialog();
                    }else{
                        Toast.makeText(getContext(), "Update Fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //Hàm check đầu vào
    private boolean checkValidation() {

        String passNow = edtPassNow.getText().toString().trim();
        String newPass = edtNewPass.getText().toString().trim();
        String rePass = edtRepass.getText().toString().trim();

        //bỏ trống ô Mật Khẩu Hiện
        if(TextUtils.isEmpty(passNow)){
            edtPassNow.requestFocus();
            edtPassNow.setError(getContext().getResources().getString(R.string.check_empty_nowpass));
            return false;
        }

        //bỏ trống ô Mật Khẩu Mới
        if(TextUtils.isEmpty(newPass)){
            edtNewPass.requestFocus();
            edtNewPass.setError(getContext().getResources().getString(R.string.check_empty_newpass));
            return false;
        }
        // Mật khẩu mới đặt không đúng yêu cầu (từ 5 ký tự trở )
        if(newPass.length()<5){
            edtNewPass.requestFocus();
            edtNewPass.setError(getContext().getResources().getString(R.string.error_password));
            return  false;
        }


        // bỏ trống ô Xác hận ật hẩu
        if(TextUtils.isEmpty(rePass)){
            edtRepass.requestFocus();
            edtRepass.setError(getContext().getResources().getString(R.string.check_empty_repass));
            return  false;
        }

        //check Repass= Newpass
        if(!rePass.equals(newPass)){
            edtRepass.requestFocus();
            edtRepass.setError(getContext().getResources().getString(R.string.check_repass_newpass));
            return false;
        }

        //check database của password hien tai
        Cursor cursor = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.ACCOUNT_TB_NAME + " WHERE " + MyDatabaseHelper.CUSTOMER_COL_ACT_ID + " = " + MATK );
        if (cursor.moveToFirst()){
            String check= cursor.getString( 3 );
            if (!passNow.equals(check)){
                edtPassNow.requestFocus();
                edtPassNow.setError(getContext().getResources().getString(R.string.check_db_nowpass));
                return false;
            };

        }
        cursor.close();
        return  true;
    }

    private void openSuccessDialog(){
        final Dialog dialog= new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.account_custom_changepass_dialog);
        Window window= dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes= window.getAttributes();
        windowAttributes.gravity= Gravity.CENTER;
        window.setAttributes(windowAttributes);
        dialog.setCancelable(false);
        //LinkView

        ImageView imvClose  = dialog.findViewById(R.id.imvDiaClose);

        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }
}