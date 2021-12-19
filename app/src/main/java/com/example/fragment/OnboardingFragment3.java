package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pnu_application.MainActivity;
import com.example.pnu_application.R;
import com.example.pnu_application.SignIn_Screen;

public class OnboardingFragment3 extends Fragment {

    private Button btnStart;
    private View mView;
    public OnboardingFragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_onboarding3, container, false);
        btnStart = mView.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"Chào mừng bạn đến với PnU <3", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SignIn_Screen.class);
                getActivity().startActivity(intent);
            }
        });
        return mView;
    }
}