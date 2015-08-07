package com.robo.store_shopkeeper;

import com.robo.store_shopkeeper.util.KeyUtil;

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
		//0线下/1线上
		case R.id.sell_machine_order_cover:
			Bundle bundle = new Bundle();
			bundle.putString(KeyUtil.OnlineOffLineKey, "0");
			toActivity(SellMachineTradeOrdersActivity.class, bundle);
			break;
		case R.id.online_order_cover:
			Bundle bundle1 = new Bundle();
			bundle1.putString(KeyUtil.OnlineOffLineKey, "1");
			toActivity(SellMachineTradeOrdersActivity.class, bundle1);
			break;
		case R.id.shop_sell_sum_cover:
//			toActivity(GoodsRukuActivity.class, null);
			break;
		}
	}
}
