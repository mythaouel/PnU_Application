package com.example.pnu_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.adapter.OnboardingAdapter;
import com.example.fragment.AccountFragment;
import com.example.fragment.HomeFragment;
import com.example.fragment.NoLoginAccountFragment;

public class Loading_Screen extends AppCompatActivity {

    //Thời gian chờ là 2.5s
    public static MyDatabaseHelper db;
    int SPLASH_TIME_OUT = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareDatabase();

        setContentView(R.layout.activity_loading_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.ACCOUNT_TB_NAME + " WHERE " + MyDatabaseHelper.ACCOUNT_COL_STATUS + " = 1");
                if (cursor!=null && cursor.moveToFirst()){
                    Cursor cursor1 = Loading_Screen.db.getData( "SELECT  * FROM "+ MyDatabaseHelper.CUSTOMER_TB_NAME + " WHERE " + MyDatabaseHelper.CUSTOMER_COL_ACT_ID + " = " +MyDatabaseHelper.ACCOUNT_COL_ID);
                    if (cursor1!=null && cursor1.moveToFirst())
                    {
                        Intent intent = new Intent(Loading_Screen.this, MainActivity.class);
                        Toast.makeText(Loading_Screen.this, "Chào mừng "+ cursor1.getString(1) +" đến với PnU <3", Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putInt(MainActivity.KEY_USER_TO_MAIN, Integer.parseInt(cursor.getString(0)));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(Loading_Screen.this, MainActivity.class);
                        Toast.makeText(Loading_Screen.this, "Chào mừng bạn đến với PnU. Cập nhật thông tin ngay để bắt đầu mua sắm cùng PnU nhé <3", Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putInt(MainActivity.KEY_USER_TO_MAIN, Integer.parseInt(cursor.getString(0)));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }else{
                    Intent intent = new Intent(Loading_Screen.this, OnboardingActivity.class);
                    startActivity(intent);
                }

                finish();
            }
        },SPLASH_TIME_OUT);
    }

    public void prepareDatabase() {
        db=new MyDatabaseHelper(Loading_Screen.this);
//        db.createSomeTestRows();
    }
}