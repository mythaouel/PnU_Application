package com.example.pnu_application;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment.AccountFragment;

import java.util.Random;

public class SignUp_Screen extends AppCompatActivity{

    //Button btnSignUp, btnSignIn1;
    ImageView imvBack;
    Context context;
    EditText edtUserName, edtPassword, edtPhone;
    Button btnSignUp, btnSignIn;
    CheckBox chkPassword1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        linkViews();
        addEvents();
        context = this;

    }

    private void linkViews() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtPhone = findViewById(R.id.edtPhone);
        chkPassword1 = findViewById(R.id.chkPassword1);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);

        imvBack = findViewById(R.id.imvBack);

    }
    private void addEvents() {

        chkPassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = chkPassword1.isChecked();
                if(checked){
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                edtPassword.setSelection(edtPassword.length());
            }
        });
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
                //b??? tr???ng ?? Password
                if (TextUtils.isEmpty(password)) {
                    edtPassword.requestFocus();
                    edtPassword.setError(context.getResources().getString(R.string.loi_thieu_info));
                    error = true;
                }
                if(phone.length()!=10 && phone.length()!=11){
                    edtPhone.requestFocus();
                    edtPhone.setError(context.getResources().getString(R.string.error_phone));
                    error = true;
                }
                //b??? tr???ng ?? S??? ??i???n tho???i
                if (TextUtils.isEmpty(phone)) {
                    edtPhone.requestFocus();
                    edtPhone.setError(context.getResources().getString(R.string.loi_thieu_info));
                    error = true;
                }
                if(Loading_Screen.db.isUserNameExists(userName)){
                    edtUserName.requestFocus();
                    edtUserName.setError(context.getResources().getString(R.string.error_userName));
                    error = true;
                }
                //b??? tr???ng ?? t??n ????ng nh???p
                if(TextUtils.isEmpty(userName)){
                    edtUserName.requestFocus();
                    edtUserName.setError(context.getResources().getString(R.string.loi_thieu_info));
                    error = true;
                }
                if (!error) {
                    boolean error1 = false;
                    if(phone.charAt(0) != '0'){
                        edtPhone.requestFocus();
                        edtPhone.setError(context.getResources().getString(R.string.error_phone));
                        error1 = true;
                    }
                    if(phone.charAt(1) != '3' && phone.charAt(1) != '7' && phone.charAt(1) != '8' && phone.charAt(1) != '9'){
                        edtPhone.requestFocus();
                        edtPhone.setError(context.getResources().getString(R.string.error_phone));
                        error1 = true;
                    }
                    if(!error1) {
                        Random random = new Random();
                        int max = 999999;
                        int min = 100000;
                        int OTP = random.nextInt(max - min + 1) + min;
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp_Screen.this);
                        builder.setTitle("OTP");
                        builder.setIcon(android.R.drawable.ic_menu_info_details);
                        builder.setMessage("OTP c???a b???n l??: " + OTP);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(context, OTP_Screen.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("phone", phone);
                                bundle.putString("userName", userName);
                                bundle.putString("password", password);
                                bundle.putString("OTP", String.valueOf(OTP));
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            }
        });
    }

}