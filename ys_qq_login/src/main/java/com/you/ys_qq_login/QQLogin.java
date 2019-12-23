package com.you.ys_qq_login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

public class QQLogin {

    private Context mContext;
    private Tencent mTencent;
    private IUserInfoCallback mCbUserinfo;

    public QQLogin(Context context, String appId) {
        mContext = context;
        mTencent = Tencent.createInstance(appId, mContext);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("QQLogin", "onActivityResult");
        Log.i("QQLogin", "requestCode:" + requestCode + ",resultCode:" + resultCode);

        // 判断是否为QQ登录行为
        if (requestCode == Constants.REQUEST_LOGIN) {
            // 判断是否QQ登录成功
            if (resultCode == Constants.UI_NONE) {
                //Tencent.onActivityResultData(requestCode, resultCode, data, new QQLoginListener(mContext, mTencent, mCbUserinfo));
                Tencent.handleResultData(data, new QQLoginListener(mContext, mTencent, mCbUserinfo));
            }
        }
    }

    public void Login(IUserInfoCallback cbUserinfo) {
        mCbUserinfo = cbUserinfo;

        if (!mTencent.isSessionValid()) {
            mTencent.login((Activity) mContext, "all", new QQLoginListener(mContext, mTencent, mCbUserinfo));
        }else{
            UserInfo userInfo = new UserInfo(mContext, mTencent.getQQToken());
            userInfo.getUserInfo(new QQUserInfoListener(mContext, mCbUserinfo));
        }


    }

}
