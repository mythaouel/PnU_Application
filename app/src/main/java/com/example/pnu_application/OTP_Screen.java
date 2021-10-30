package com.example.pnu_application;

import androidx.annotation.IntRange;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OTP_Screen extends AppCompatActivity {

    Button btnOTP,btnSignIn;
    EditText edtOTP;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);
        linkViews();
        context = this;
        addEvents();
    }

    private void linkViews() {
        btnOTP = findViewById(R.id.btnOTP);
        btnSignIn = findViewById(R.id.btnSignIn);
        edtOTP = findViewById(R.id.edtOTP);
    }
    private void addEvents() {
        btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;
                String OTP = edtOTP.getText().toString().trim();

                if(OTP.length()<6){
                    edtOTP.requestFocus();
                    edtOTP.setError(context.getResources().getString(R.string.error_OTP));
                    error = true;
                }
                //bỏ trống ô OTP
                if (TextUtils.isEmpty(OTP)) {
                    edtOTP.requestFocus();
                    edtOTP.setError(context.getResources().getString(R.string.thieu_OTP));
                    error = true;
                }
                if (!error) {
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SignIn_Screen.class);
                startActivity(intent);
            }
        });
    }
}