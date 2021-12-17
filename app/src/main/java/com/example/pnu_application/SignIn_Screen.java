package com.example.pnu_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SignIn_Screen extends AppCompatActivity implements View.OnClickListener{
    Button btnSignIn,btnSignUp1;
    Context context;
    EditText edtUserName, edtPassword;
    public static final String KEY_USER_TO_MAIN = "KEY_USER_TO_MAIN";
    public static final String KEY_PASSWORD_TO_MAIN = "KEY_PASSWORD_TO_MAIN";

    public static final String KEY_USER_FROM_REGISTER = "KEY_USER_FROM_REGISTER";

    public static final int REQUEST_CODE_REGISTER = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        linkViews();
        addEvents();
        context=this;
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.btnSignIn:
                signIn();
                break;
            case R.id.btnSignUp1:
                signUp();
                break;
        }
    }


    private void signIn() {
        boolean error = false;
        String userName = edtUserName.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if(password.length()<5){
            edtPassword.requestFocus();
            edtPassword.setError(context.getResources().getString(R.string.error_password));
            error = true;
        }


        //bỏ trống ô Password
        if(TextUtils.isEmpty(password)){
            edtPassword.requestFocus();
            edtPassword.setError(context.getResources().getString(R.string.loi_thieu_info));
            error = true;
        }

        //bỏ trống ô tên đăng nhập
        if(TextUtils.isEmpty(userName)){
            edtUserName.requestFocus();
            edtUserName.setError(context.getResources().getString(R.string.loi_thieu_info));
            error = true;
        }
        if(!error){
            Intent intent = new Intent(context, MainActivity.class);
            //gửi dữ liệu
            intent.putExtra(KEY_USER_TO_MAIN, userName);
            intent.putExtra(KEY_PASSWORD_TO_MAIN, password);
            startActivity(intent);
        }

    }
    private void signUp() {
        Intent intent = new Intent(context, SignUp_Screen.class);
        startActivity(intent);
    }

    private void addEvents() {
        btnSignIn.setOnClickListener(this);
        btnSignUp1.setOnClickListener(this);
        edtPassword.setOnClickListener(this);
    }
    private void linkViews() {
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp1 = findViewById(R.id.btnSignUp1);

        edtPassword=findViewById(R.id.edtPassword);
        edtUserName= findViewById(R.id.edtUserName);

    }
}