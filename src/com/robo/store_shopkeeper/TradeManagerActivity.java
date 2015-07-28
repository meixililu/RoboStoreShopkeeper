package com.robo.store_shopkeeper;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class TradeManagerActivity extends BaseActivity implements View.OnClickListener {

	private FrameLayout sell_machine_order_cover,online_order_cover,shop_sell_sum_cover;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trade_manager);
		init();
	}
	
	private void init(){
		sell_machine_order_cover = (FrameLayout) findViewById(R.id.sell_machine_order_cover);
		online_order_cover = (FrameLayout) findViewById(R.id.online_order_cover);
		shop_sell_sum_cover = (FrameLayout) findViewById(R.id.shop_sell_sum_cover);
		
		sell_machine_order_cover.setOnClickListener(this);
		online_order_cover.setOnClickListener(this);
		shop_sell_sum_cover.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		case R.id.sell_machine_order_cover:
			toActivity(GoodsRukuActivity.class, null);
			break;
		case R.id.online_order_cover:
			toActivity(GoodsRukuActivity.class, null);
			break;
		case R.id.shop_sell_sum_cover:
			toActivity(GoodsRukuActivity.class, null);
			break;
		}
	}
}
