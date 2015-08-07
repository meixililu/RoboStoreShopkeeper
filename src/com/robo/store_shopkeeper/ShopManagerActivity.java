package com.robo.store_shopkeeper;

import java.util.HashMap;

import org.apache.http.Header;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.robo.store_shopkeeper.dao.CommonResponse;
import com.robo.store_shopkeeper.dao.GetShopInfoResponse;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.LogUtil;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.ToastUtil;

public class ShopManagerActivity extends BaseActivity implements View.OnClickListener {

	public static final int requestCode = 10005;
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
	private String shop_des,shop_position,name,cellphone,contat,contat_cellphone;
	
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
		boolean isvalid = true;
		shop_des = input_shop_des.getText().toString().trim();
		if(TextUtils.isEmpty(shop_des)){
			ToastUtil.diaplayMesShort(this, "店铺介绍不能为空");
			isvalid = false;
		}
		shop_position = input_shop_position.getText().toString().trim();
		if(TextUtils.isEmpty(shop_position)){
			ToastUtil.diaplayMesShort(this, "店铺地址不能为空");
			isvalid = false;
		}
		name = user_name.getText().toString().trim();
		if(TextUtils.isEmpty(name)){
			ToastUtil.diaplayMesShort(this, "联系人不能为空");
			isvalid = false;
		}
		cellphone = user_cellphone.getText().toString().trim();
		if(TextUtils.isEmpty(cellphone)){
			ToastUtil.diaplayMesShort(this, "联系电话不能为空");
			isvalid = false;
		}
		contat = emergency_contat.getText().toString().trim();
		if(TextUtils.isEmpty(contat)){
			ToastUtil.diaplayMesShort(this, "紧急联系人不能为空");
			isvalid = false;
		}
		contat_cellphone = emergency_contat_cellphone.getText().toString().trim();
		if(TextUtils.isEmpty(contat_cellphone)){
			ToastUtil.diaplayMesShort(this, "紧急联系电话不能为空");
			isvalid = false;
		}
		return isvalid;
	}
	
	private void RequestDataModify(){
		if(validSubmitData()){
			showProgressbar();
			mGetShopInfoResponse.setMemo(shop_des);
			mGetShopInfoResponse.setAddress(shop_position);
			mGetShopInfoResponse.setSosMan(contat);
			mGetShopInfoResponse.setSosTel(contat_cellphone);
			mGetShopInfoResponse.setLinkMan(name);
			mGetShopInfoResponse.setLinkMobile(cellphone);
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
//					ToastUtil.diaplayMesShort(ShopManagerActivity.this, "连接失败，请重试！");
				}
				@Override
				public void onSuccess(int arg0, Header[] arg1, String result) {
					LogUtil.DefalutLog(result);
					CommonResponse mResponse = (CommonResponse) ResultParse.parseResult(result,CommonResponse.class);
					if(ResultParse.handleResutl(ShopManagerActivity.this, mResponse)){
//						ToastUtil.diaplayMesShort(ShopManagerActivity.this, "修改成功");
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
			}else{
				RequestDataModify();
			}
			break;
		case R.id.shop_address_edit_img:
			input_shop_position.setEnabled(!input_shop_position.isEnabled());
			if(input_shop_position.isEnabled()){
				input_shop_position.requestFocus();
				input_shop_position.setSelection(input_shop_position.getText().toString().length());
				showIME(input_shop_position);
			}else{
				RequestDataModify();
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
			}else{
				RequestDataModify();
			}
			break;
		case R.id.shop_location_img:
			toShopLocation();
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		if(requestCode == arg0 && RESULT_OK == arg1){
			if(arg2 != null){
				String lat = arg2.getStringExtra(KeyUtil.LatitudeKey);
				String lon = arg2.getStringExtra(KeyUtil.LongitudeKey);
				if(!TextUtils.isEmpty(lat) && !TextUtils.isEmpty(lon)){
					LogUtil.DefalutLog("onActivityResult---requestCode:"+requestCode);
					mGetShopInfoResponse.setLatitude(lat);
					mGetShopInfoResponse.setLongitude(lon);
					RequestDataModify();
				}
			}
		}
	}
	
	private void toShopLocation(){
		Bundle bundle = new Bundle();
		bundle.putString(KeyUtil.LatitudeKey, mGetShopInfoResponse.getLatitude());
		bundle.putString(KeyUtil.LongitudeKey, mGetShopInfoResponse.getLongitude());
		toActivityForResult(ShopLocationActivity.class, bundle, requestCode);
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
