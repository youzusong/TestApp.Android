package com.you.testappandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.you.ys_google_login.GoogleLogin;
import com.you.ys_qq_login.IUserInfoCallback;
import com.you.ys_qq_login.QQLogin;

import org.json.JSONObject;

public class GoogleLoginActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private Button mBtnSignOut;
    private GoogleLogin mGGLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);

        initView();
        initListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (mGGLogin != null) {
            mGGLogin.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initView() {
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnSignOut = findViewById(R.id.btn_singout);
        mGGLogin = new GoogleLogin(GoogleLoginActivity.this);
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("QQLogin", "点击登录");
                mGGLogin.login();
            }
        });

        mBtnSignOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mGGLogin.signOut();
            }
        });
    }

}
