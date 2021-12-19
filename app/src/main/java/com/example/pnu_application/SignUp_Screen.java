package com.example.pnu_application;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment.AccountFragment;

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

        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SignIn_Screen.class);
                startActivity(intent);
            }
        });
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
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                String phone = edtPhone.getText().toString();

                if(password.length()<5){
                    edtPassword.requestFocus();
                    edtPassword.setError(context.getResources().getString(R.string.error_password));
                    error = true;
                }
//                if(phone.length()!=10 || phone.length()!=11 ){
//                    edtPhone.requestFocus();
//                    edtPhone.setError(context.getResources().getString(R.string.error_phone));
//                    error = true;
//                }

                //bỏ trống ô Password
                if (TextUtils.isEmpty(password)) {
                    edtPassword.requestFocus();
                    edtPassword.setError(context.getResources().getString(R.string.loi_thieu_info));
                    error = true;
                }
                //bỏ trống ô Số điện thoại
                if (TextUtils.isEmpty(phone)) {
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
                    if(!Loading_Screen.db.isUserNameExists(userName)){
                        //check username exist
                        Intent intent = new Intent(context, OTP_Screen.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("phone", phone);
                        bundle.putString("userName", userName);
                        bundle.putString("password",password);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else
                    {
                        edtUserName.requestFocus();
                        edtUserName.setError(context.getResources().getString(R.string.error_userName));
                    }
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