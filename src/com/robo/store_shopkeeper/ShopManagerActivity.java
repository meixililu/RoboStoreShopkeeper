package com.robo.store_shopkeeper;

import java.util.HashMap;

import org.apache.http.Header;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.robo.store_shopkeeper.dao.GetShopInfoResponse;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.LogUtil;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.ToastUtil;

public class ShopManagerActivity extends BaseActivity implements View.OnClickListener {

	private ScrollView content_layout;
	private ImageView shop_edit_img;
	private ImageView shop_address_edit_img;
	private ImageView shop_location_img;
	private ImageView contact_edit_img;
	private EditText input_shop_des;
	private EditText input_shop_position;
	
	private EditText user_name;
	private EditText user_cellphone;
	private EditText emergency_contat;
	private EditText emergency_contat_cellphone;
	
	private TextView shop_id_tv;
	private TextView shop_service_tv;
	private TextView company_id_tv;
	private TextView open_time_tv;
	
	private GetShopInfoResponse mGetShopInfoResponse;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_manager);
		init();
		RequestData();
	}
	
	private void init(){
		content_layout = (ScrollView) findViewById(R.id.content_layout);
		shop_edit_img = (ImageView) findViewById(R.id.shop_edit_img);
		shop_address_edit_img = (ImageView) findViewById(R.id.shop_address_edit_img);
		shop_location_img = (ImageView) findViewById(R.id.shop_location_img);
		contact_edit_img = (ImageView) findViewById(R.id.contact_edit_img);
		input_shop_position = (EditText) findViewById(R.id.input_shop_position);
		input_shop_des = (EditText) findViewById(R.id.input_shop_des);
		
		user_name = (EditText) findViewById(R.id.user_name);
		user_cellphone = (EditText) findViewById(R.id.user_cellphone);
		emergency_contat = (EditText) findViewById(R.id.emergency_contat);
		emergency_contat_cellphone = (EditText) findViewById(R.id.emergency_contat_cellphone);
		
		shop_id_tv = (TextView) findViewById(R.id.shop_id_tv);
		shop_service_tv = (TextView) findViewById(R.id.shop_service_tv);
		company_id_tv = (TextView) findViewById(R.id.company_id_tv);
		open_time_tv = (TextView) findViewById(R.id.open_time_tv);
		
		content_layout.setVisibility(View.GONE);
		
		
		shop_edit_img.setOnClickListener(this);
		shop_location_img.setOnClickListener(this);
		shop_address_edit_img.setOnClickListener(this);
		contact_edit_img.setOnClickListener(this);
	}
	
	private void setData(){
		content_layout.setVisibility(View.VISIBLE);
		input_shop_des.setText(mGetShopInfoResponse.getMemo());
		input_shop_position.setText(mGetShopInfoResponse.getAddress());
		
		user_name.setText(mGetShopInfoResponse.getLinkMan());
		user_cellphone.setText(mGetShopInfoResponse.getLinkMobile());
		emergency_contat.setText(mGetShopInfoResponse.getSosMan());
		emergency_contat_cellphone.setText(mGetShopInfoResponse.getSosTel());
		
		shop_id_tv.setText(mGetShopInfoResponse.getShopId());
		shop_service_tv.setText(mGetShopInfoResponse.getBusinessNO());
		company_id_tv.setText(mGetShopInfoResponse.getBusinessNO());
		open_time_tv.setText(mGetShopInfoResponse.getOpendate());
		
	}
	
	private void RequestData(){
		showProgressbar();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("shopId", "");
		RoboHttpClient.post(HttpParameter.shopsUrl,"getShopInfo", params, new TextHttpResponseHandler(){
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				ToastUtil.diaplayMesLong(ShopManagerActivity.this, "连接失败，请重试！");
			}
			@Override
			public void onSuccess(int arg0, Header[] arg1, String result) {
				LogUtil.DefalutLog(result);
				mGetShopInfoResponse = (GetShopInfoResponse) ResultParse.parseResult(result,GetShopInfoResponse.class);
				if(ResultParse.handleResutl(ShopManagerActivity.this, mGetShopInfoResponse)){
					setData();
				}
			}
			@Override
			public void onFinish() {
				hideProgressbar();
			}
		});
	}
	
	private boolean validSubmitData(){
		
		
		return true;
	}
	
	private void RequestDataModify(){
		if(validSubmitData()){
			showProgressbar();
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("shopId", mGetShopInfoResponse.getShopId());
			params.put("memo", mGetShopInfoResponse.getMemo());
			params.put("address", mGetShopInfoResponse.getAddress());
			params.put("sosTel", mGetShopInfoResponse.getSosTel());
			params.put("sosMan", mGetShopInfoResponse.getSosMan());
			params.put("linkMan", mGetShopInfoResponse.getLinkMan());
			params.put("linkMobile", mGetShopInfoResponse.getLinkMobile());
			params.put("latitude", mGetShopInfoResponse.getLatitude());
			params.put("longitude", mGetShopInfoResponse.getLongitude());
			RoboHttpClient.post(HttpParameter.shopsUrl,"updateShopInfo", params, new TextHttpResponseHandler(){
				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					ToastUtil.diaplayMesLong(ShopManagerActivity.this, "连接失败，请重试！");
				}
				@Override
				public void onSuccess(int arg0, Header[] arg1, String result) {
					LogUtil.DefalutLog(result);
					mGetShopInfoResponse = (GetShopInfoResponse) ResultParse.parseResult(result,GetShopInfoResponse.class);
					if(ResultParse.handleResutl(ShopManagerActivity.this, mGetShopInfoResponse)){
						setData();
					}
				}
				@Override
				public void onFinish() {
					hideProgressbar();
				}
			});
		}
	}
	
	@Override
	public void onClickEmptyLayoutRefresh() {
		RequestData();
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		case R.id.shop_edit_img:
			input_shop_des.setEnabled(!input_shop_des.isEnabled());
			if(input_shop_des.isEnabled()){
				input_shop_des.requestFocus();
				input_shop_des.setSelection(input_shop_des.getText().toString().length());
				showIME(input_shop_des);
			}
			break;
		case R.id.shop_address_edit_img:
			input_shop_position.setEnabled(!input_shop_position.isEnabled());
			if(input_shop_position.isEnabled()){
				input_shop_position.requestFocus();
				input_shop_position.setSelection(input_shop_position.getText().toString().length());
				showIME(input_shop_position);
			}
			break;
		case R.id.contact_edit_img:
			user_name.setEnabled(!user_name.isEnabled());
			user_cellphone.setEnabled(!user_cellphone.isEnabled());
			emergency_contat.setEnabled(!emergency_contat.isEnabled());
			emergency_contat_cellphone.setEnabled(!emergency_contat_cellphone.isEnabled());
			if(user_name.isEnabled()){
				user_name.requestFocus();
				user_name.setSelection(user_name.getText().toString().length());
				showIME(user_name);
			}
			break;
		}
	}
	
	private void showIME(View view){
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
		imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);  
	}
//	private void showOrHideIME(){
//		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
//		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);  
//	}
}
