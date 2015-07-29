package com.robo.store_shopkeeper;

import java.util.HashMap;

import org.apache.http.Header;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.dao.UserLoginResponse;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.LogUtil;
import com.robo.store_shopkeeper.util.LoginUtil;
import com.robo.store_shopkeeper.util.Md5;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.SPUtil;
import com.robo.store_shopkeeper.util.Settings;
import com.robo.store_shopkeeper.util.ToastUtil;

public class LoginActivity extends BaseActivity {

	private EditText username_input,pwd_input;
	private TextView error_txt;
	private Button login_btn;
	private String userName,pwd;
	private ProgressDialog progressDialog;
	private SharedPreferences mSharedPreferences;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getResources().getString(R.string.login));
		setContentView(R.layout.activity_login);
		init();
	}
	
	private void init(){
		mSharedPreferences = SPUtil.getSharedPreferences(this);
		userName = mSharedPreferences.getString(KeyUtil.UserNameKey, "");
		username_input = (EditText) findViewById(R.id.username_input);
		pwd_input = (EditText) findViewById(R.id.pwd_input);
		error_txt = (TextView) findViewById(R.id.error_txt);
		login_btn = (Button) findViewById(R.id.login_btn);
		
		if(!TextUtils.isEmpty(userName)){
			username_input.setText(userName);
			username_input.setSelection(userName.length());
		}
		login_btn.setOnClickListener(this);
	}
	
	private boolean validData(){
		boolean isvalid = true;
		userName = username_input.getText().toString().trim();
		pwd = pwd_input.getText().toString().trim();
		if(TextUtils.isEmpty(userName)){
			ToastUtil.diaplayMesShort(this, "请输入账号");
			isvalid = false;
		}
		if(TextUtils.isEmpty(pwd)){
			ToastUtil.diaplayMesShort(this, "请输入密码");
			isvalid = false;
		}
		return isvalid;
	}
	
	private void RequestData(){
		error_txt.setText("");
		if(validData()){
			showSucceeDialog();
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("userName", userName);//bjwfj
			params.put("password", Md5.d5(pwd));//88888
			RoboHttpClient.post("operatorLogin", params, new TextHttpResponseHandler(){

				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					ToastUtil.diaplayMesLong(LoginActivity.this, "连接失败，请重试！");
				}

				@Override
				public void onSuccess(int arg0, Header[] arg1, String result) {
					LogUtil.DefalutLog(result);
					UserLoginResponse mUserLoginResponse = (UserLoginResponse) ResultParse.parseResult(result,UserLoginResponse.class);
					if(ResultParse.handleResutl(LoginActivity.this, mUserLoginResponse)){
						HttpParameter.accessToken = mUserLoginResponse.getAccessToken();
						SPUtil.saveSharedPreferences(mSharedPreferences, KeyUtil.UserPWDKey, Md5.d5(pwd));
						LoginUtil.isLogin = true;
						toActivity(MainActivity.class, null);
						LoginActivity.this.finish();
					}else{
						error_txt.setText(mUserLoginResponse.getErrorMsg());
					}
				}
				
				@Override
				public void onFinish() {
					SPUtil.saveSharedPreferences(mSharedPreferences, KeyUtil.UserNameKey, userName);
					progressDialog.dismiss();
				}
			});
		}
	}
	
	private void showSucceeDialog(){
		progressDialog = ProgressDialog.show(this, "", "正在登录...", true, false);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		case R.id.login_btn:
			RequestData();
			break;
		}
	}
	
	
}
