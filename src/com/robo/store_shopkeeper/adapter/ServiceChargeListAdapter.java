package com.robo.store_shopkeeper.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.robo.store_shopkeeper.GoodsDetailActivity;
import com.robo.store_shopkeeper.R;
import com.robo.store_shopkeeper.dao.GetServicePriceVo;
import com.robo.store_shopkeeper.util.KeyUtil;

public class ServiceChargeListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<GetServicePriceVo> ordersList;
	
	public ServiceChargeListAdapter(Context mContext,LayoutInflater mInflater,List<GetServicePriceVo> goodsList){
		this.context = mContext;
		this.mInflater = mInflater;
		this.ordersList = goodsList;
	}
	
	@Override
	public int getCount() {
		return ordersList.size();
	}

	@Override
	public GetServicePriceVo getItem(int position) {
		return ordersList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.service_charge_list_item, null);
			holder = new ViewHolder();
			holder.time_tv = (TextView) convertView.findViewById(R.id.time_tv);
			holder.money_tv = (TextView) convertView.findViewById(R.id.money_tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final GetServicePriceVo mGoodsBase = ordersList.get(position);
		holder.time_tv.setText(mGoodsBase.getTime());
		holder.money_tv.setText(mGoodsBase.getPrice());
		return convertView;
	}
	
	static class ViewHolder {
		TextView time_tv;
		TextView money_tv;
	}

}
