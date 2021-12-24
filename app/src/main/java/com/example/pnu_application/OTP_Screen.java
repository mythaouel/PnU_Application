package com.example.pnu_application;

import androidx.annotation.IntRange;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment.AccountFragment;

import java.nio.channels.InterruptedByTimeoutException;

public class OTP_Screen extends AppCompatActivity {

    ImageView imvBack;
    Button btnOTP,btnSignIn;
    EditText edtOTP;
    TextView txtPhone, txtUserName, txtPassword, txtOTP;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);

        linkViews();
        context = this;
        getData();
        addEvents();
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String phone1 = bundle.getString("phone", "");
            String userName1=bundle.getString("userName","");
            String password1 = bundle.getString("password","");
            String OTP1 = bundle.getString("OTP","");
        }
        txtPhone.setText(intent.getStringExtra("phone"));
        txtPassword.setText(intent.getStringExtra("password"));
        txtUserName.setText(intent.getStringExtra("userName"));
        txtOTP.setText(intent.getStringExtra("OTP"));
    }
    private void linkViews() {
        imvBack=findViewById(R.id.imvBack);

        btnOTP = findViewById(R.id.btnOTP);
        btnSignIn = findViewById(R.id.btnSignIn);

        txtPhone = findViewById(R.id.txtPhone);
        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        txtOTP = findViewById(R.id.txtOTPFromSignUp);

        edtOTP = findViewById(R.id.edtOTP);
    }
    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                Intent intent = new Intent(context, SignUp_Screen.class);
                startActivity(intent);
            }
        });
        btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;
                String OTP1 = edtOTP.getText().toString();

                if(OTP1.length()!= 6){
                    edtOTP.requestFocus();
                    edtOTP.setError(context.getResources().getString(R.string.error_OTP1));
                    error = true;
                }
                //bỏ trống ô OTP
                if (TextUtils.isEmpty(OTP1)) {
                    edtOTP.requestFocus();
                    edtOTP.setError(context.getResources().getString(R.string.thieu_OTP));
                    error = true;
                }
                if (!error) {
                    boolean error1 = false;
                    Integer OTP_permiss = Integer.parseInt(edtOTP.getText().toString());
                    Integer OTP_signUp = Integer.parseInt(txtOTP.getText().toString());
                    if(!OTP_permiss.equals(OTP_signUp)){
                        edtOTP.requestFocus();
                        edtOTP.setError(context.getResources().getString(R.string.error_OTP));
                        error1 = true;
                    }
                    if (!error1) {
                        //Save db
                        String username, password, phone;
                        int OTP;
                        username = txtUserName.getText().toString();
                        password = txtPassword.getText().toString();
                        phone = txtPhone.getText().toString();
                        OTP = Integer.parseInt(edtOTP.getText().toString());

                        boolean flag = Loading_Screen.db.insertAccountData(username, phone, password, OTP, 0);
                        if (flag) {
                            Toast.makeText(context, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent_saved = new Intent(context, SignIn_Screen.class);
                            startActivity(intent_saved);
                        } else {
                            Toast.makeText(context, "Lỗi!", Toast.LENGTH_SHORT).show();
                        }
                    }

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