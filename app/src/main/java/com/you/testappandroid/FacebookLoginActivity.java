package com.you.testappandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.you.ys_facebook_login.FacebookLogin;

public class FacebookLoginActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private Button mBtnLogout;
    private FacebookLogin mFBLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);

        initView();
        initListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (null != mFBLogin) {
            mFBLogin.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initView() {
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLogout = findViewById(R.id.btn_logout);
        mFBLogin = new FacebookLogin(this);
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFBLogin.login();
            }
        });

        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFBLogin.logout();
            }
        });
    }
}
