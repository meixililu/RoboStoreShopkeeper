package com.robo.store_shopkeeper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;

import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.LoginUtil;
import com.robo.store_shopkeeper.util.SPUtil;

public class LoadingActivity extends Activity {

	private SharedPreferences mSharedPreferences;
	private String userName,userPWD;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		mSharedPreferences = SPUtil.getSharedPreferences(this);
		LoginUtil.login(this, mSharedPreferences);
		new WaitTask().execute();
	}
	
	class WaitTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			toNextPage();
		}
	}
	
	private void toNextPage(){
		String userName = mSharedPreferences.getString(KeyUtil.UserNameKey, "");
		String userPWD = mSharedPreferences.getString(KeyUtil.UserPWDKey, "");
		if(!TextUtils.isEmpty(userPWD)){
			Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
			startActivity(intent);
		}else{
			Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
			startActivity(intent);
		}
		finish();
	}
}
