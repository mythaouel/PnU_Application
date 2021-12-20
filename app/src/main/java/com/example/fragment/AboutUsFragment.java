package com.example.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;

public class AboutUsFragment extends Fragment {

     View mView;

     TextView txtPhone;
     ImageView imvBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity.hideBottomNav();
        mView= inflater.inflate(R.layout.fragment_about_us, container, false);

        linkViews();
        addEvents();
        return mView;

    }

    private void linkViews() {
        txtPhone = mView.findViewById(R.id.txtAUsPhone);

        imvBack = mView.findViewById(R.id.imvAUsBack);
    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
        txtPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dial = "tel:" + txtPhone.getText().toString();
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        });
    }
    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity.showBottomNav();
    }

}