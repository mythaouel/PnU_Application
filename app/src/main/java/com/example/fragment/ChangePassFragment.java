package com.example.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pnu_application.Loading_Screen;
import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePassFragment extends Fragment {

    View mView;

    EditText edtPassNow,edtNewPass,edtRepass;
    TextInputLayout nowpassWrapper,newpassWrapper,repassWrapper;
    Button   btnChange;
    ImageView imvBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_change_pass, container, false);
        linkViews();
        MainActivity.hideBottomNav();
        addEvent();
        return mView;

    }

    private void addEvent() {
        String passNow = edtPassNow.getText().toString().trim();
        String newPass = edtNewPass.getText().toString().trim();
        String rePass = edtRepass.getText().toString().trim();

        edtNewPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() ==0) {
                    newpassWrapper.setError("Bạn bắt buộc phải nhập usernam");
                } else {
                    newpassWrapper.setError(null);
                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
                MainActivity.showBottomNav();
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passNow = edtPassNow.getText().toString().trim();
                String newPass = edtNewPass.getText().toString().trim();
                String rePass = edtRepass.getText().toString().trim();

                if (!passNow.equals("")&& !newPass.equals("") && !rePass.equals("") ){
                    boolean flag= Loading_Screen.db.updatePassword(newPass,1);
                    if(flag==true){
                        Toast.makeText(getContext(), "Update Succes", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Update Fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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
}