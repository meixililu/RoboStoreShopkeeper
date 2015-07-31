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
import com.robo.store_shopkeeper.dao.GetAllOrdersVo;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.TimeUtil;

public class SellMachineTradeOrderListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<GetAllOrdersVo> ordersList;
	
	public SellMachineTradeOrderListAdapter(Context mContext,LayoutInflater mInflater,List<GetAllOrdersVo> goodsList){
		this.context = mContext;
		this.mInflater = mInflater;
		this.ordersList = goodsList;
	}
	
	@Override
	public int getCount() {
		return ordersList.size();
	}

	@Override
	public GetAllOrdersVo getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.sell_machine_trade_order_list_item, null);
			holder = new ViewHolder();
			holder.item_cover = (FrameLayout) convertView.findViewById(R.id.item_cover);
			holder.date_day = (TextView) convertView.findViewById(R.id.date_day);
			holder.date_month = (TextView) convertView.findViewById(R.id.date_month);
			holder.tv_sum = (TextView) convertView.findViewById(R.id.tv_sum);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final GetAllOrdersVo mGoodsBase = ordersList.get(position);
		holder.date_day.setText(TimeUtil.customFormatDate(mGoodsBase.getDatetime(),TimeUtil.DayFormat,"d")+"日");
		holder.date_month.setText(TimeUtil.customFormatDate(mGoodsBase.getDatetime(),TimeUtil.DayFormat,"M")+"月");
		holder.tv_sum.setText("￥" + mGoodsBase.getPrice());
		
		holder.item_cover.setOnClickListener(new OnClickListener() {
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
		TextView date_day;
		TextView date_month;
		TextView tv_sum;
		FrameLayout item_cover;
	}

}
