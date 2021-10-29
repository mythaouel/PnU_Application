package com.example.pnu_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.fragment.HomeFragment;

public class Loading_Screen extends AppCompatActivity {

    //Thời gian chờ là 2.5s
    int SPLASH_TIME_OUT = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Home_Fragment.class - màn hình sau khi load xong
                Intent intent = new Intent(Loading_Screen.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        },SPLASH_TIME_OUT);
    }
}