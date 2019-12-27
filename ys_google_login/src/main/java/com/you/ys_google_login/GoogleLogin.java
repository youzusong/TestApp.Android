package com.you.ys_google_login;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

public class GoogleLogin {

    public static final Integer RC_SIGN_IN = 20001;

    private Activity mActivity;

    public GoogleLogin(Activity activity) {
        mActivity = activity;
    }

    public void login() {

        Log.i("GoogleLogin", "login");

        // 设定选项值
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("244668994570-dn376h0ls5gl99si6rrp6lg13tu2ecvq.apps.googleusercontent.com")
                .requestEmail()
                .requestId()
                .requestProfile()
                .build();

        // 获取client
        GoogleSignInClient signInClient = GoogleSignIn.getClient(mActivity, gso);

        // 获取intent
        Intent signInIntent = signInClient.getSignInIntent();

        // 开启登录视窗
        mActivity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signOut() {
        GoogleSignInAccount lastAcct = GoogleSignIn.getLastSignedInAccount(mActivity);

        if (lastAcct != null) {
            // 设定选项值
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("244668994570-dn376h0ls5gl99si6rrp6lg13tu2ecvq.apps.googleusercontent.com")
                    .requestEmail()
                    .requestId()
                    .requestProfile()
                    .build();

            // 获取client
            GoogleSignInClient signInClient = GoogleSignIn.getClient(mActivity, gso);

            signInClient.signOut().addOnCompleteListener(mActivity, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(mActivity, "已登出", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("GoogleLogin", "onActivityResult");
        Log.i("GoogleLogin", "requestCode:" + requestCode + ",resultCode:" + resultCode);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        Log.i("GoogleLogin", "handleSignInResult");

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            //updateUI(account);
            Toast.makeText(mActivity, "GG登录成功", Toast.LENGTH_LONG).show();

            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();

            JSONObject obj = new JSONObject();
            try {
                obj.put("displayName", account.getDisplayName());
                obj.put("givenName", account.getGivenName());
                obj.put("familyName", account.getFamilyName());
                obj.put("email", account.getEmail());
                obj.put("id", account.getId());

            } catch (Exception ex) {

            }

            Log.i("GoogleLogin", "signInResult:successed=");
            Log.i("GoogleLogin", obj.toString());

        } catch (ApiException e) {
            Toast.makeText(mActivity, "GG登录失败", Toast.LENGTH_LONG).show();

            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.i("GoogleLogin", "signInResult:failed code=" + e.getStatusCode() + ", " + e.getMessage());
            //updateUI(null);
        }
    }

}
