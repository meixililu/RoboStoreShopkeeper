package com.robo.store_shopkeeper;

import android.os.Bundle;

import com.robo.store_shopkeeper.adapter.ShopActivityPagerAdapter;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.NumberUtil;
import com.robo.store_shopkeeper.view.CustomViewPager;
import com.robo.store_shopkeeper.view.PagerSlidingTabStrip;

public class ShopLocationActivity extends BaseActivity {

	private PagerSlidingTabStrip indicator;
	private CustomViewPager viewPager;
	private ShopActivityPagerAdapter mAdapter;
	public static double latitude,longitude;
	public static String shopMemo;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getResources().getString(R.string.shop_location));
		setContentView(R.layout.activity_shop_location);
		init();
	}
	
	private void init(){
		Bundle mBundle = getIntent().getBundleExtra(KeyUtil.BundleKey);
		if(mBundle != null){
			String mLatitude = mBundle.getString(KeyUtil.LatitudeKey);
			String mLongitude = mBundle.getString(KeyUtil.LongitudeKey);
			shopMemo = mBundle.getString(KeyUtil.ShopMemoKey);
			latitude = NumberUtil.StringToDouble(mLatitude);
			longitude = NumberUtil.StringToDouble(mLongitude);
		}
		viewPager = (CustomViewPager) findViewById(R.id.pager);
		indicator = (PagerSlidingTabStrip) findViewById(R.id.indicator);
		mAdapter = new ShopActivityPagerAdapter(this.getSupportFragmentManager(),this);
		
		viewPager.setAdapter(mAdapter);
		indicator.setViewPager(viewPager);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		latitude = 0;
		longitude = 0;
		shopMemo = null;
	}
	
}
