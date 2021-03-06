package com.robo.store_shopkeeper.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.robo.store_shopkeeper.R;
import com.robo.store_shopkeeper.SearchActivity;
import com.robo.store_shopkeeper.CashOrderListActivity;
import com.robo.store_shopkeeper.dao.ShopBase;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.view.PinnedSectionListView.PinnedSectionListAdapter;

public class ShopSearchListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<ShopBase> AllDataList;
	
	public ShopSearchListAdapter(Context mContext,LayoutInflater mInflater,List<ShopBase> shopList){
		this.context = mContext;
		this.mInflater = mInflater;
		this.AllDataList = shopList;
	}
	
	@Override
	public int getCount() {
		return AllDataList.size();
	}

	@Override
	public ShopBase getItem(int position) {
		return AllDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ShopBase mShopBase = AllDataList.get(position);
		ContentHolder holder0 = null;
		if (convertView == null) {
				holder0 = new ContentHolder();
				convertView = mInflater.inflate(R.layout.shop_list_item_content, null);
				holder0.item_cover = (FrameLayout) convertView.findViewById(R.id.item_cover);
				holder0.shop_name = (TextView) convertView.findViewById(R.id.shop_name);
				holder0.shop_distance = (TextView) convertView.findViewById(R.id.shop_distance);
				convertView.setTag(holder0);
		} else {
				holder0 = (ContentHolder) convertView.getTag();
		}
			holder0.shop_name.setText(mShopBase.getShopName());
			holder0.shop_distance.setText(mShopBase.getDistance());
			holder0.item_cover.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle mBundle = new Bundle();
					mBundle.putString(KeyUtil.ShopDetailTitleKey, mShopBase.getShopName());
					mBundle.putString(KeyUtil.ShopDetailIdKey, mShopBase.getShopId());
					Intent intent = new Intent(context,CashOrderListActivity.class);
					intent.putExtra(KeyUtil.BundleKey, mBundle);
					context.startActivity(intent);
				}
			});
		return convertView;
	}
	
	static class ContentHolder {
		FrameLayout item_cover;
		TextView shop_name;
		TextView shop_distance;
	}

}
