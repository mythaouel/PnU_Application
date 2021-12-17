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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class OTP_Screen extends AppCompatActivity {

    ImageView imvBack;
    Button btnOTP,btnSignIn;
    EditText edtOTP;
    TextView txtPhone;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);

        linkViews();
        context = this;
        addEvents();
        getData();

    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String phone = bundle.getString("number", "");
            String userName = bundle.getString("userName", "");
            String password = bundle.getString("password", "");
        }
        txtPhone.setText(intent.getStringExtra("number"));
    }

    private void linkViews() {
        imvBack=findViewById(R.id.imvBack);

        btnOTP = findViewById(R.id.btnOTP);
        btnSignIn = findViewById(R.id.btnSignIn);

        txtPhone = findViewById(R.id.txtPhone);

        edtOTP = findViewById(R.id.edtOTP);
    }
    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SignUp_Screen.class);

                startActivity(intent);
            }
        });
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
                    //lưu bundle vào database
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