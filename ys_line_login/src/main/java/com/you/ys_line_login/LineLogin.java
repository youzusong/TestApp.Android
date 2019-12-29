package com.you.ys_line_login;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.linecorp.linesdk.LineCredential;
import com.linecorp.linesdk.LineProfile;
import com.linecorp.linesdk.Scope;
import com.linecorp.linesdk.auth.LineAuthenticationParams;
import com.linecorp.linesdk.auth.LineLoginApi;
import com.linecorp.linesdk.auth.LineLoginResult;

import java.util.Arrays;

public class LineLogin {

    public static final Integer RC_SIGN_IN = 30001;
    public static final String CHANNEL_ID = "1653717040";

    private Activity mActivity;

    public LineLogin(Activity activity) {
        mActivity = activity;
    }

    public void signIn() {
        Log.i("LineLogin", "signIn");

        LineAuthenticationParams params = new LineAuthenticationParams.Builder()
                .scopes(Arrays.asList(Scope.PROFILE))
                .build();

        Intent loginIntent = LineLoginApi.getLoginIntent(
                mActivity,
                CHANNEL_ID,
                params);

        mActivity.startActivityForResult(loginIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("LineLogin", "onActivityResult");
        Log.i("LineLogin", "requestCode:" + requestCode + ",resultCode:" + resultCode);

        if(requestCode == RC_SIGN_IN) {
            LineLoginResult result = LineLoginApi.getLoginResultFromIntent(data);
            switch (result.getResponseCode()) {
                case SUCCESS:
                    Toast.makeText(mActivity,"成功登录", Toast.LENGTH_SHORT).show();

                    String accessToken = result.getLineCredential().getAccessToken().getTokenString();
                    LineProfile profile = result.getLineProfile();
                    LineCredential cred = result.getLineCredential();


                    break;

                case CANCEL:
                    Toast.makeText(mActivity,"取消登录", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    Toast.makeText(mActivity,"登录失败", Toast.LENGTH_SHORT).show();
                    Log.i("LineLogin", "Login Failed : " + result.getErrorData().toString());
                    break;
            }
        }
    }
}

