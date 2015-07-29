package com.robo.store_shopkeeper.util;

import java.util.HashMap;

import org.apache.http.Header;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.LoginActivity;
import com.robo.store_shopkeeper.dao.UserLoginResponse;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;

public class LoginUtil {

	public static boolean isLogin;
	
	public static boolean isUpdate;
	
	
	public static void login(final Context mContext, final SharedPreferences mSharedPreferences){
		if(!isUpdate){
			final String userName = mSharedPreferences.getString(KeyUtil.UserNameKey, "");
			final String userPWD = mSharedPreferences.getString(KeyUtil.UserPWDKey, "");
			if(!TextUtils.isEmpty(userPWD)){
				isLogin = true;
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("userName", userName);
				params.put("password", userPWD);
				RoboHttpClient.post("operatorLogin", params, new TextHttpResponseHandler(){
					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					}
					@Override
					public void onSuccess(int arg0, Header[] arg1, String result) {
						LogUtil.DefalutLog("LoginUtil---onSuccess:"+result);
						UserLoginResponse mUserLoginResponse = (UserLoginResponse) ResultParse.parseResult(result,UserLoginResponse.class);
						if(ResultParse.handleResutl(mContext, mUserLoginResponse)){
							isUpdate = true;
							HttpParameter.accessToken = mUserLoginResponse.getAccessToken();
						}
					}
					@Override
					public void onFinish() {
					}
				});
			}
		}
	}
	
}
