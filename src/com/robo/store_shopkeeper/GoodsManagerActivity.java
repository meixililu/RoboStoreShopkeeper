package com.robo.store_shopkeeper;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class GoodsManagerActivity extends BaseActivity implements View.OnClickListener {

	private FrameLayout ruku_cover,cancle_cover,baosun_cover,kuchun_check_cover;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_manager);
		init();
	}
	
	private void init(){
		ruku_cover = (FrameLayout) findViewById(R.id.ruku_cover);
		cancle_cover = (FrameLayout) findViewById(R.id.cancle_cover);
		baosun_cover = (FrameLayout) findViewById(R.id.baosun_cover);
		kuchun_check_cover = (FrameLayout) findViewById(R.id.kuchun_check_cover);
		
		ruku_cover.setOnClickListener(this);
		cancle_cover.setOnClickListener(this);
		baosun_cover.setOnClickListener(this);
		kuchun_check_cover.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		case R.id.ruku_cover:
			toActivity(GoodsRukuActivity.class, null);
			break;
		case R.id.cancle_cover:
			toActivity(GoodsRevokeActivity.class, null);
			break;
		case R.id.kuchun_check_cover:
			toActivity(StockListActivity.class, null);
			break;
		case R.id.baosun_cover:
			toActivity(GoodsReportOrReturnActivity.class, null);
			break;
		}
	}
}
