package com.robo.store_shopkeeper;

import java.util.HashMap;

import org.apache.http.Header;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.LogUtil;
import com.robo.store_shopkeeper.util.LoginUtil;
import com.robo.store_shopkeeper.util.SPUtil;
import com.robo.store_shopkeeper.util.ToastUtil;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	private FrameLayout user_cover,more_cover;
	private FrameLayout goods_ruku_cover,cash_checkout_cover,goods_management_cover;
	private FrameLayout deal_management_cover,bill_management_cover,business_remainder_cover;
	private FrameLayout shop_management_cover,fault_repair_cover;
	private long exitTime = 0;
	private SharedPreferences mSharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		RequestData();
		LoginUtil.login(this, mSharedPreferences);
	}
	
	private void init(){
		mSharedPreferences = SPUtil.getSharedPreferences(this);
		user_cover = (FrameLayout) findViewById(R.id.user_cover);
		more_cover = (FrameLayout) findViewById(R.id.more_cover);
		
		goods_ruku_cover = (FrameLayout) findViewById(R.id.goods_ruku_cover);
		cash_checkout_cover = (FrameLayout) findViewById(R.id.cash_checkout_cover);
		goods_management_cover = (FrameLayout) findViewById(R.id.goods_management_cover);
		
		deal_management_cover = (FrameLayout) findViewById(R.id.deal_management_cover);
		bill_management_cover = (FrameLayout) findViewById(R.id.bill_management_cover);
		business_remainder_cover = (FrameLayout) findViewById(R.id.business_remainder_cover);
		
		shop_management_cover = (FrameLayout) findViewById(R.id.shop_management_cover);
		fault_repair_cover = (FrameLayout) findViewById(R.id.fault_repair_cover);
		
		
		user_cover.setOnClickListener(this);
		more_cover.setOnClickListener(this);
		
		goods_ruku_cover.setOnClickListener(this);
		cash_checkout_cover.setOnClickListener(this);
		goods_management_cover.setOnClickListener(this);
		
		deal_management_cover.setOnClickListener(this);
		bill_management_cover.setOnClickListener(this);
		business_remainder_cover.setOnClickListener(this);
		
		shop_management_cover.setOnClickListener(this);
		fault_repair_cover.setOnClickListener(this);
	}
	
	@Override
	public void onBackPressed() {
    	if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(getApplicationContext(), this.getResources().getString(R.string.exit_program), 0).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		LogUtil.DefalutLog("MainActivity---onActivityResult");
	}
	
	private void RequestData(){
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("currentVer", String.valueOf(HttpParameter.softVerCode));
		RoboHttpClient.get("getAppVersion", params, new TextHttpResponseHandler(){

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				ToastUtil.diaplayMesLong(MainActivity.this, "连接失败，请重试！");
			}

			@Override
			public void onSuccess(int arg0, Header[] arg1, String result) {
				LogUtil.DefalutLog(result);
//				UserLoginResponse mUserLoginResponse = (UserLoginResponse) ResultParse.parseResult(result,UserLoginResponse.class);
//				if(ResultParse.handleResutl(MainActivity.this, mUserLoginResponse)){
//				}
			}
			
			@Override
			public void onFinish() {
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		LoginUtil.isUpdate = false;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.user_cover:
			break;
		case R.id.more_cover:
			break;
		case R.id.cash_checkout_cover:
			break;
		case R.id.goods_management_cover:
			break;
		case R.id.deal_management_cover:
			break;
		case R.id.bill_management_cover:
			break;
		case R.id.business_remainder_cover:
			break;
		case R.id.shop_management_cover:
			break;
		case R.id.fault_repair_cover:
			break;
		}
	}
	
	protected void toActivity(Class mClass,Bundle bundle){
		Intent intent = new Intent(this,mClass);
		if(bundle != null){
			intent.putExtra(KeyUtil.BundleKey, bundle);
		}
		startActivity(intent);
	}

}
