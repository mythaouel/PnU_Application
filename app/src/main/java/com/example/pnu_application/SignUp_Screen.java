package com.example.pnu_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUp_Screen extends AppCompatActivity{

    //Button btnSignUp, btnSignIn1;
    ImageView imvBack;
    Context context;
    EditText edtUserName, edtPassword, edtPhone;
    Button btnSignUp, btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        linkViews();
        addEvents();
        context = this;
    }

    private void addEvents() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SignIn_Screen.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;
                String userName = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String sdt = edtPhone.getText().toString().trim();


                if(password.length()<5){
                    edtPassword.requestFocus();
                    edtPassword.setError(context.getResources().getString(R.string.error_password));
                    error = true;
                }

                //bỏ trống ô Password
                if (TextUtils.isEmpty(password)) {
                    edtPassword.requestFocus();
                    edtPassword.setError(context.getResources().getString(R.string.loi_thieu_info));
                    error = true;
                }
                //bỏ trống ô Số điện thoại
                if (TextUtils.isEmpty(sdt)) {
                    edtPhone.requestFocus();
                    edtPhone.setError(context.getResources().getString(R.string.loi_thieu_info));
                    error = true;
                }
                //bỏ trống ô tên đăng nhập
                if(TextUtils.isEmpty(userName)){
                    edtUserName.requestFocus();
                    edtUserName.setError(context.getResources().getString(R.string.loi_thieu_info));
                    error = true;
                }
                if (!error) {
                    Intent intent = new Intent(context, OTP_Screen.class);
                    startActivity(intent);
//                    gửi lại thông tin qua màn hình đăng nhập
//                    Intent intent = new Intent();
//                    //gửi dữ liệu
//                    intent.putExtra(SignIn_Screen.KEY_USER_TO_MAIN, userName);
//                    setResult(RESULT_OK, intent);
//                    finish();
                }
            }
        });
    }

    private void linkViews() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtPhone = findViewById(R.id.edtPhone);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        imvBack = findViewById(R.id.imvBack);
    }
}