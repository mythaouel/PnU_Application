package com.example.pnu_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SignIn_Screen extends AppCompatActivity{
    Button btnSignIn,btnSignUp1;
    Context context;
    EditText edtUserName, edtPassword;

    TextView txtTest,txtQuenMatKhau;
    CheckBox chkPassword;

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

    private void addEvents() {
        chkPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = chkPassword.isChecked();
                if(checked){
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                edtPassword.setSelection(edtPassword.length());
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error = false;
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();

                //kiểm tra độ dài mật khẩu
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
                    User currentUser = Loading_Screen.db.Authenticate(new User(null,userName,null, password,null,"0"));

                    if(currentUser != null){
                        int MATK = Integer.parseInt(currentUser.getUserId());
                        boolean flag= Loading_Screen.db.updateAccountStatus(1,MATK);
                        Cursor cursor = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.CUSTOMER_TB_NAME + " WHERE " + MyDatabaseHelper.CUSTOMER_COL_ACT_ID + " = " +MATK);
                        if (cursor!=null && cursor.moveToFirst()){
                            String hoTen = cursor.getString(1);
                            Toast.makeText(context, "Chào mừng "+ hoTen +" đến với PnU <3 ", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Chào mừng bạn đến với PnU. Cập nhật thông tin ngay để bắt đầu mua sắm cùng PnU nhé <3", Toast.LENGTH_SHORT).show();
                        }
//                        if(flag==true){
//                            Toast.makeText(context, "Cập nhật status thành công", Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
//                        }
                        Intent intent = new Intent(context, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt(MainActivity.KEY_USER_TO_MAIN, Integer.parseInt(currentUser.getUserId()));
                        bundle.putString(MainActivity.USER_NAME_TO_MAIN, currentUser.getUserName());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else
                    {
                        edtUserName.requestFocus();
                        Toast.makeText(context, "Đăng nhập thất bại! Vui lòng thử lại hoặc đăng ký tài khoản mới!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SignUp_Screen.class);
                startActivity(intent);
            }
        });

        txtQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    private void linkViews() {
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp1 = findViewById(R.id.btnSignUp1);

        edtPassword=findViewById(R.id.edtPassword);
        edtUserName= findViewById(R.id.edtUserName);

        txtTest = findViewById(R.id.txtTest);
        txtQuenMatKhau = findViewById(R.id.txtQuenMatKhau);

        chkPassword = findViewById(R.id.chkPassword);
    }
}