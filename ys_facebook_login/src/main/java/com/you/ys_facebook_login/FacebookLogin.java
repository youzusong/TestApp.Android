package com.you.ys_facebook_login;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileManager;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

public class FacebookLogin {

    public static final Integer RC_SIGN_IN = 64206;

    private Activity mActivity;
    private CallbackManager mCallbackManager;
    private ProfileTracker mProfileTracker;
    private AccessTokenTracker maccessTokenTracker;

    public FacebookLogin(Activity activity) {
        mActivity = activity;
        mCallbackManager = CallbackManager.Factory.create();
        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if(currentProfile == null){
                    return;
                }

                String pId = currentProfile.getId();
                String pFirstName = currentProfile.getFirstName();
                String pMiddleName = currentProfile.getMiddleName();
                String pLastName = currentProfile.getLastName();
                String pName = currentProfile.getName();
                Uri pPicUrl = currentProfile.getProfilePictureUri(128, 128);

                Toast.makeText(mActivity, "2你好：" + pName, Toast.LENGTH_SHORT).show();

                mProfileTracker.stopTracking();
            }
        };

    }

    public void login() {
        Log.i("FacebookLogin", "login");

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn) {
            new GraphRequest(
                    accessToken,
                    "/me/permissions/",
                    null,
                    HttpMethod.DELETE,
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse graphResponse) {
                            LoginManager.getInstance().logOut();
                            relogin();
                        }
                    }).executeAsync();
        }else{
            relogin();
        }
    }

    private void relogin(){
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.i("FacebookLogin", "onSuccess");
                        Toast.makeText(mActivity, "FB登录成功", Toast.LENGTH_SHORT).show();

                        Profile profile = Profile.getCurrentProfile();
                        if(profile == null){
                            mProfileTracker.startTracking();
                        }else{
                            String pId = profile.getId();
                            String pFirstName = profile.getFirstName();
                            String pMiddleName = profile.getMiddleName();
                            String pLastName = profile.getLastName();
                            String pName = profile.getName();
                            Uri pPicUrl = profile.getProfilePictureUri(128, 128);

                            Toast.makeText(mActivity, "1你好：" + pName, Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.i("FacebookLogin", "onCancel");
                        Toast.makeText(mActivity, "FB取消登录", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.i("FacebookLogin", "onError");
                        Toast.makeText(mActivity, "FB登录失败", Toast.LENGTH_SHORT).show();

                    }
                });


        LoginManager.getInstance().logInWithReadPermissions(mActivity, Arrays.asList("public_profile"));

    }

    public void logout() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null) {
            Toast.makeText(mActivity, "FB已登出", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(mActivity, "FB正在登出", Toast.LENGTH_SHORT).show();

        new GraphRequest(
                accessToken,
                "/me/permissions/",
                null,
                HttpMethod.DELETE,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse graphResponse) {
                        LoginManager.getInstance().logOut();
                        Toast.makeText(mActivity, "FB已登出", Toast.LENGTH_SHORT).show();
                    }
                }).executeAsync();

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("FacebookLogin", "onActivityResult");
        Log.i("FacebookLogin", "requestCode:" + requestCode + ",resultCode:" + resultCode);

        if (requestCode == RC_SIGN_IN) {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}
