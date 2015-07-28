package com.robo.store_shopkeeper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gc.materialdesign.widgets.Dialog;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.LoginUtil;
import com.robo.store_shopkeeper.util.SPUtil;

public class SettingActivity extends BaseActivity implements OnClickListener{

	private FrameLayout modify_pwd_cover,logout_cover;
	private TextView my_account_name;
	
	private SharedPreferences mSharedPreferences;
	private String userName;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		init();
	}
	
	private void init(){
		mSharedPreferences = SPUtil.getSharedPreferences(this);
		userName = mSharedPreferences.getString(KeyUtil.UserNameKey, "");
		modify_pwd_cover = (FrameLayout) findViewById(R.id.modify_pwd_cover);
		logout_cover = (FrameLayout) findViewById(R.id.logout_cover);
		my_account_name = (TextView) findViewById(R.id.my_account_name);
		
		modify_pwd_cover.setOnClickListener(this);
		logout_cover.setOnClickListener(this);
		my_account_name.setText(userName);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		case R.id.logout_cover:
			loginout();
			break;
		case R.id.modify_pwd_cover:
			toActivity(ModifyPWDActivity.class, null);
			break;
		}
	}
	
	private void loginout(){
		Dialog dialog = new Dialog(this, "温馨提示", "您确定要注销退出吗？");
		dialog.addAcceptButton("确定");
		dialog.addCancelButton("取消");
		dialog.setOnAcceptButtonClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SPUtil.saveSharedPreferences(mSharedPreferences, KeyUtil.UserPWDKey, "");
				LoginUtil.isLogin = false;
				SettingActivity.this.finish();
			}
		});
		dialog.setCancelable(true);
		dialog.show();
	}
	
}
