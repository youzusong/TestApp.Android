package com.you.testappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GoogleLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);


        startActivityForResult(null,1);
    }


}
