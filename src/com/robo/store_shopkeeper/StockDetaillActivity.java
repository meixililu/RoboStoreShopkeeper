package com.robo.store_shopkeeper;

import java.util.HashMap;

import org.apache.http.Header;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robo.store_shopkeeper.dao.GetShopStorageInfoResponse;
import com.robo.store_shopkeeper.dao.WayGoodsCountVo;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.ToastUtil;


public class StockDetaillActivity extends BaseActivity implements OnClickListener{

	private LayoutInflater mInflater;
	private LinearLayout content_layout,list_layout;
	private TextView ruku_goods_code;
	private TextView ruku_goods_name;
	private TextView ruku_goods_number;
	private TextView ruku_goods_service;
	private TextView ruku_macid;
	private GetShopStorageInfoResponse mResponse;
	private String instorageId,machineId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_detail);
		init();
		RequestData();
	}
	
	private void init(){
		mInflater = LayoutInflater.from(this);
		Bundle bundle = getIntent().getBundleExtra(KeyUtil.BundleKey);
		instorageId = bundle.getString(KeyUtil.InStorageIdKey);
		machineId = bundle.getString(KeyUtil.MachineIdKey);
		
		content_layout = (LinearLayout) findViewById(R.id.content_layout);
		list_layout = (LinearLayout) findViewById(R.id.list_layout);
		ruku_goods_code = (TextView) findViewById(R.id.ruku_goods_code);
		ruku_goods_name = (TextView) findViewById(R.id.ruku_goods_name);
		ruku_goods_number = (TextView) findViewById(R.id.ruku_goods_number);
		ruku_goods_service = (TextView) findViewById(R.id.ruku_goods_service);
		ruku_macid = (TextView) findViewById(R.id.ruku_macid);
		
	}
	
	private void setData(){
		content_layout.setVisibility(View.VISIBLE);
		ruku_goods_code.setText(mResponse.getGoodsBarcode());
		ruku_goods_name.setText(mResponse.getGoodsName());
		ruku_goods_number.setText(mResponse.getStorageCount()+"");
		ruku_goods_service.setText(mResponse.getCoName());
		ruku_macid.setText(mResponse.getMachineId());
		initList();
	}
	
	private void initList(){
		for(WayGoodsCountVo bean : mResponse.getList()){
			View view = mInflater.inflate(R.layout.stock_detail_list_item, null);
			TextView ruku_macid = (TextView) view.findViewById(R.id.ruku_macid);
			ruku_macid.setText(bean.getWayId() + " / " + bean.getWayGoodsCount());
			list_layout.addView(view);
		}
	}
	
	private void RequestData(){
		hideEmptyLayout();
		showProgressbar();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("shopId", "");
		params.put("goodsBarcode", instorageId);
		params.put("machineId", machineId);
		RoboHttpClient.post(HttpParameter.goodUrl,"getShopStorageInfo", params, new TextHttpResponseHandler(){
			
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				showEmptyLayout_Error();
				ToastUtil.diaplayMesLong(StockDetaillActivity.this, StockDetaillActivity.this.getResources().getString(R.string.connet_fail));
			}
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, String result) {
				mResponse = (GetShopStorageInfoResponse) ResultParse.parseResult(result,GetShopStorageInfoResponse.class);
				if(ResultParse.handleResutl(StockDetaillActivity.this, mResponse)){
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
	}
}
