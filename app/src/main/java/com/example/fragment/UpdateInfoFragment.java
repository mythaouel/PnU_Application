package com.example.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;

public class UpdateInfoFragment extends Fragment {

    Button btnUpdate;
    EditText edtName, edtBirthday, edtPhone, edtAddress, edtEmail;
    ImageView imvAvatar, imvBack;

    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView=inflater.inflate(R.layout.fragment_update_info, container, false);
        linkViews();
        addEvents();
        MainActivity.hideBottomNav();
        // Inflate the layout for this fragment
        return mView;

    }

    private void linkViews() {
        btnUpdate   = mView.findViewById(R.id.btnUpdate);

        edtName     = mView.findViewById(R.id.edtName);
        edtBirthday = mView.findViewById(R.id.edtBirthday);
        edtEmail    = mView.findViewById(R.id.edtEmail);
        edtPhone    = mView.findViewById(R.id.edtPhoneAct);
        edtAddress  = mView.findViewById(R.id.edtAddress);

        imvAvatar   = mView.findViewById(R.id.imvAvtInfo);
        imvBack     = mView.findViewById(R.id.imvAccountBack);

    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
                MainActivity.showBottomNav();
            }
        });
    }

}