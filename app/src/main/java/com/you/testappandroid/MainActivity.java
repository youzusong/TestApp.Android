package com.you.testappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.you.ys_google_login.GoogleLogin;
import com.you.ys_qq_login.QQLogin;

public class MainActivity extends AppCompatActivity {

    private Button mBtnQQLogin;
    private Button mBtnGGLogin;
    private Button mBtnFBLogin;
    private Button mBtnLNLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initView(){
        mBtnQQLogin = findViewById(R.id.btn_qqlogin);
        mBtnGGLogin = findViewById(R.id.btn_gglogin);
        mBtnFBLogin = findViewById(R.id.btn_fblogin);
        mBtnLNLogin = findViewById(R.id.btn_linelogin);

    }

    private void initListener() {
        mBtnQQLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, QQLoginActivity.class);
                startActivity(intent);
            }
        });

        mBtnGGLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GoogleLoginActivity.class);
                startActivity(intent);
            }
        });

        mBtnFBLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        mBtnLNLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LineLoginActivity.class);
                startActivity(intent);
            }
        });

    }

}
