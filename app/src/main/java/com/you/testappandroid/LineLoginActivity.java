package com.you.testappandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.you.ys_line_login.LineLogin;

public class LineLoginActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private LineLogin mLNLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_login);

        initView();
        initListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (null != mLNLogin) {
            mLNLogin.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initView(){
        mBtnLogin = findViewById(R.id.btn_login);
        mLNLogin = new LineLogin(this);
    }

    private void initListener(){
        mBtnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mLNLogin.signIn();
            }
        });
    }
}
