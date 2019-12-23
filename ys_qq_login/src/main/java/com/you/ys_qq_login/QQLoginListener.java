package com.you.ys_qq_login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

class QQLoginListener implements IUiListener {

    private Context mContext;
    private Tencent mTencent;
    private IUserInfoCallback mCbUserinfo;

    QQLoginListener(Context context, Tencent tencent, IUserInfoCallback cbUserinfo) {
        mContext = context;
        mTencent = tencent;
        mCbUserinfo = cbUserinfo;
    }

    @Override
    public void onComplete(Object o) {
        Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();

        // 获取信息
        try {
            JSONObject obj = (JSONObject) o;

            Log.i("QQLogin", "QQ登录 - 成功");
            Log.i("QQLogin", obj.toString());

            String openid = obj.getString("openid");
            String accessToken = obj.getString("access_token");
            String expiresIn = obj.getString("expires_in");

            Log.i("QQLogin", "获取信息...");

            mTencent.setOpenId(openid);
            mTencent.setAccessToken(accessToken, expiresIn);

            UserInfo userInfo = new UserInfo(mContext, mTencent.getQQToken());
            userInfo.getUserInfo(new QQUserInfoListener(mContext, mCbUserinfo));

        } catch (JSONException ex) {

        }
    }

    @Override
    public void onCancel() {
        Toast.makeText(mContext, "登录取消", Toast.LENGTH_SHORT).show();

        Log.i("QQLogin", "QQ登录 - 取消");
    }

    @Override
    public void onError(UiError uiError) {
        Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT).show();

        Log.i("QQLogin", "QQ登入 - 失败");
        Log.i("QQLogin", uiError.errorMessage);
    }

}
