package com.robo.store_shopkeeper;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class BillManagerActivity extends BaseActivity implements View.OnClickListener {

	private FrameLayout per_month_income_cover,per_month_expend_cover;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bill_manager);
		init();
	}
	
	private void init(){
		per_month_income_cover = (FrameLayout) findViewById(R.id.per_month_income_cover);
		per_month_expend_cover = (FrameLayout) findViewById(R.id.per_month_expend_cover);
		
		per_month_income_cover.setOnClickListener(this);
		per_month_expend_cover.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		case R.id.per_month_income_cover:
			toActivity(GoodsRukuActivity.class, null);
			break;
		case R.id.per_month_expend_cover:
			toActivity(GoodsRukuActivity.class, null);
			break;
		}
	}
}
