package com.you.ys_qq_login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

class QQUserInfoListener implements IUiListener {

    private Context mContext;
    private IUserInfoCallback mCbUserinfo;

    QQUserInfoListener(Context context, IUserInfoCallback cbUserinfo){
        mContext = context;
        mCbUserinfo = cbUserinfo;
    }

    @Override
    public void onComplete(Object o) {
        if(o == null) {
            return;
        }

        try{
            JSONObject obj = (JSONObject) o;

            Log.i("QQLogin", "获取信息 - 成功");
            Log.i("QQLogin", obj.toString());

            mCbUserinfo.HandlerUserInfo(obj);

        }catch (Exception ex){

        }

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(UiError uiError) {

    }

}
