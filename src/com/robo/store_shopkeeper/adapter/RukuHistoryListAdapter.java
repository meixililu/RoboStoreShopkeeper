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
import com.robo.store_shopkeeper.dao.GetInStrogeListVo;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.TimeUtil;

public class RukuHistoryListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<GetInStrogeListVo> ordersList;
	
	public RukuHistoryListAdapter(Context mContext,LayoutInflater mInflater,List<GetInStrogeListVo> goodsList){
		this.context = mContext;
		this.mInflater = mInflater;
		this.ordersList = goodsList;
	}
	
	@Override
	public int getCount() {
		return ordersList.size();
	}

	@Override
	public GetInStrogeListVo getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.ruku_history_list_item, null);
			holder = new ViewHolder();
			holder.ruku_cover = (FrameLayout) convertView.findViewById(R.id.ruku_cover);
			holder.ruku_time = (TextView) convertView.findViewById(R.id.ruku_time);
			holder.ruku_date = (TextView) convertView.findViewById(R.id.ruku_date);
			holder.ruku_goods = (TextView) convertView.findViewById(R.id.ruku_goods);
			holder.ruku_number = (TextView) convertView.findViewById(R.id.ruku_number);
			holder.ruku_order = (TextView) convertView.findViewById(R.id.ruku_order);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final GetInStrogeListVo mGoodsBase = ordersList.get(position);
		holder.ruku_time.setText(TimeUtil.customFormatDate(mGoodsBase.getDatetime(),TimeUtil.DateFormat,TimeUtil.TimeFormat));
		holder.ruku_date.setText(TimeUtil.customFormatDate(mGoodsBase.getDatetime(),TimeUtil.DateFormat,TimeUtil.MonthFormat2));
		holder.ruku_goods.setText(mGoodsBase.getGoodsName());
		holder.ruku_number.setText(mGoodsBase.getCount()+"");
		holder.ruku_order.setText(mGoodsBase.getDeliveryId());
		if(position % 2 == 0){
			holder.ruku_cover.setBackgroundColor(context.getResources().getColor(R.color.white));
		}else{
			holder.ruku_cover.setBackgroundColor(context.getResources().getColor(R.color.text_tint1));
		}
		holder.ruku_cover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				toGoodsDetailActivity(mGoodsBase.getGoodsBarcode());
			}
		});
		
		return convertView;
	}
	
	private void toGoodsDetailActivity(String id){
		Intent intent = new Intent(context, GoodsDetailActivity.class);
		intent.putExtra(KeyUtil.GoodsIdKey, id);
		context.startActivity(intent);
	}
	
	static class ViewHolder {
		FrameLayout ruku_cover;
		TextView ruku_time;
		TextView ruku_date;
		TextView ruku_goods;
		TextView ruku_number;
		TextView ruku_order;
	}

}
