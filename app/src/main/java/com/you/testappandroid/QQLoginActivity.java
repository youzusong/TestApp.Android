package com.you.testappandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.you.ys_qq_login.IUserInfoCallback;
import com.you.ys_qq_login.QQLogin;

import org.json.JSONObject;

public class QQLoginActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private QQLogin mQQLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlogin);

        initView();
        initListener();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (mQQLogin != null) {
            mQQLogin.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void initView() {
        mBtnLogin = findViewById(R.id.btn_login);
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("QQLogin", "点击登录");
                mQQLogin = new QQLogin(QQLoginActivity.this, "1110039343");
                mQQLogin.Login(new IUserInfoCallback() {
                    @Override
                    public void HandlerUserInfo(JSONObject o) {
                        try{
                            String nickname = o.getString("nickname");
                            Toast.makeText(QQLoginActivity.this,"您好：" + nickname, Toast.LENGTH_SHORT).show();
                        }catch (Exception ex) {

                        }
                    }
                });
            }
        });
    }
}
