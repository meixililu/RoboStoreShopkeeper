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

import com.robo.store_shopkeeper.PerMonthIncomeDetailActivity;
import com.robo.store_shopkeeper.R;
import com.robo.store_shopkeeper.dao.GetRadioVo;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.TimeUtil;

public class PerMonthIncomeListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<GetRadioVo> ordersList;
	
	public PerMonthIncomeListAdapter(Context mContext,LayoutInflater mInflater,List<GetRadioVo> goodsList){
		this.context = mContext;
		this.mInflater = mInflater;
		this.ordersList = goodsList;
	}
	
	@Override
	public int getCount() {
		return ordersList.size();
	}

	@Override
	public GetRadioVo getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.per_month_income_list_item, null);
			holder = new ViewHolder();
			holder.item_cover = (FrameLayout) convertView.findViewById(R.id.item_cover);
			holder.date_day = (TextView) convertView.findViewById(R.id.date_day);
			holder.date_month = (TextView) convertView.findViewById(R.id.date_month);
			holder.tv_sum = (TextView) convertView.findViewById(R.id.tv_sum);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final GetRadioVo mGoodsBase = ordersList.get(position);
		holder.date_day.setText(TimeUtil.customFormatDate(mGoodsBase.getTime(),TimeUtil.DayFormat1,"M")+"月");
		holder.date_month.setText(TimeUtil.customFormatDate(mGoodsBase.getTime(),TimeUtil.DayFormat1,"yyyy")+"年");
		holder.tv_sum.setText("￥" + mGoodsBase.getPrice());
		
		holder.item_cover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toDetailActivity(mGoodsBase.getTime());
			}
		});
		
		return convertView;
	}
	
	private void toDetailActivity(String id){
		Bundle bundle = new Bundle();
		bundle.putString(KeyUtil.MonthKey, id);
		Intent intent = new Intent(context, PerMonthIncomeDetailActivity.class);
		intent.putExtra(KeyUtil.BundleKey, bundle);
		context.startActivity(intent);
	}
	
	static class ViewHolder {
		FrameLayout item_cover;
		TextView date_day;
		TextView date_month;
		TextView tv_sum;
	}

}
