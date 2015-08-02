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

import com.robo.store_shopkeeper.BusinessRemindDetailActivity;
import com.robo.store_shopkeeper.R;
import com.robo.store_shopkeeper.dao.GetServiceMsgVo;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.TimeUtil;

public class BusinessRemindListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<GetServiceMsgVo> ordersList;
	
	public BusinessRemindListAdapter(Context mContext,LayoutInflater mInflater,List<GetServiceMsgVo> goodsList){
		this.context = mContext;
		this.mInflater = mInflater;
		this.ordersList = goodsList;
	}
	
	@Override
	public int getCount() {
		return ordersList.size();
	}

	@Override
	public GetServiceMsgVo getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.business_remind_list_item, null);
			holder = new ViewHolder();
			holder.item_cover = (FrameLayout) convertView.findViewById(R.id.item_cover);
			holder.time_tv = (TextView) convertView.findViewById(R.id.time_tv);
			holder.title_tv = (TextView) convertView.findViewById(R.id.title_tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final GetServiceMsgVo mGoodsBase = ordersList.get(position);
		final String showTime = TimeUtil.compareDate(mGoodsBase.getCreateTime());
		holder.time_tv.setText(showTime);
		holder.title_tv.setText(mGoodsBase.getMsgType());
		holder.item_cover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toDetailActivity(mGoodsBase.getMsgType(),mGoodsBase.getMsgContent(),showTime);
			}
		});
		return convertView;
	}
	
	private void toDetailActivity(String title,String content, String time){
		Bundle bundle = new Bundle();
		bundle.putString(KeyUtil.RemindDetailTitleKey, title);
		bundle.putString(KeyUtil.RemindDetailContentKey, content);
		bundle.putString(KeyUtil.RemindDetailTimeKey, time);
		Intent intent = new Intent(context, BusinessRemindDetailActivity.class);
		intent.putExtra(KeyUtil.BundleKey, bundle);
		context.startActivity(intent);
	};
	
	static class ViewHolder {
		FrameLayout item_cover;
		TextView time_tv;
		TextView title_tv;
	}

}
