package com.example.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pnu_application.R;
public class ChangePassFragment extends Fragment {

    View mView;
    EditText edtPassNow,edtNewPass,edtRepass;
    Button   btnChange;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_change_pass, container, false);
        linkViews();
        addEvent();
        return mView;

    }

    private void addEvent() {
        String passNow = edtPassNow.getText().toString().trim();
        String newPass = edtNewPass.getText().toString().trim();
        String rePass = edtRepass.getText().toString().trim();

    }

    private void linkViews() {
        edtPassNow=mView.findViewById(R.id.edtPassNow);
        edtNewPass=mView.findViewById(R.id.edtNewPass);
        edtRepass=mView.findViewById(R.id.edtRepass);

        btnChange=mView.findViewById(R.id.btnChange);

    }
}