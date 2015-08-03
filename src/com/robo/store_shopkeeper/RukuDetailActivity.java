package com.robo.store_shopkeeper;

import java.util.HashMap;

import org.apache.http.Header;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robo.store_shopkeeper.dao.GetInStrogeInfoResponse;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.ToastUtil;


public class RukuDetailActivity extends BaseActivity implements OnClickListener{

	private LinearLayout content_layout;
	private TextView modify_btn;
	private TextView ruku_time;
	private TextView ruku_goods_code;
	private TextView ruku_goods_name;
	private TextView ruku_goods_number;
	private TextView ruku_goods_sid;
	private TextView ruku_goods_service;
	private TextView ruku_goods_info;
	private GetInStrogeInfoResponse mResponse;
	private String instorageId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ruku_detail);
		init();
		RequestData();
	}
	
	private void init(){
		Bundle bundle = getIntent().getBundleExtra(KeyUtil.BundleKey);
		instorageId = bundle.getString(KeyUtil.InStorageIdKey);
		
		content_layout = (LinearLayout) findViewById(R.id.content_layout);
		modify_btn = (TextView) findViewById(R.id.modify_btn);
		ruku_time = (TextView) findViewById(R.id.ruku_time);
		ruku_goods_code = (TextView) findViewById(R.id.ruku_goods_code);
		ruku_goods_name = (TextView) findViewById(R.id.ruku_goods_name);
		ruku_goods_number = (TextView) findViewById(R.id.ruku_goods_number);
		ruku_goods_sid = (TextView) findViewById(R.id.ruku_goods_sid);
		ruku_goods_service = (TextView) findViewById(R.id.ruku_goods_service);
		ruku_goods_info = (TextView) findViewById(R.id.ruku_goods_info);
		
		modify_btn.setOnClickListener(this);
	}
	
	private void setData(){
		content_layout.setVisibility(View.VISIBLE);
		ruku_time.setText(mResponse.getDatetime());
		ruku_goods_code.setText(mResponse.getGoodsBarcode());
		ruku_goods_name.setText(mResponse.getGoodsName());
		ruku_goods_number.setText(mResponse.getCount()+"");
		ruku_goods_sid.setText(mResponse.getDeliveryId());
		ruku_goods_service.setText(mResponse.getCoName());
		ruku_goods_info.setText(mResponse.getGoodsMemo());
	}
	
	private void RequestData(){
		hideEmptyLayout();
		showProgressbar();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("shopId", "");
		params.put("instorageId", instorageId);
		RoboHttpClient.post(HttpParameter.goodUrl,"getInStrogeInfo", params, new TextHttpResponseHandler(){
			
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				showEmptyLayout_Error();
				ToastUtil.diaplayMesLong(RukuDetailActivity.this, RukuDetailActivity.this.getResources().getString(R.string.connet_fail));
			}
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, String result) {
				mResponse = (GetInStrogeInfoResponse) ResultParse.parseResult(result,GetInStrogeInfoResponse.class);
				if(ResultParse.handleResutl(RukuDetailActivity.this, mResponse)){
					setData();
				}else{
					showEmptyLayout_Empty();
				}
			}
			
			@Override
			public void onFinish() {
				hideProgressbar();
			}
		});
	}
	
	public void onClickEmptyLayoutRefresh(){
		RequestData();
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.modify_btn:
			
			break;
		}
	}
}
