package com.robo.store_shopkeeper.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robo.store_shopkeeper.R;
import com.robo.store_shopkeeper.dao.GetCashOrderInfoVo;
import com.robo.store_shopkeeper.util.TimeUtil;

public class CashOrderDetailListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<GetCashOrderInfoVo> ordersList;
	
	public CashOrderDetailListAdapter(Context mContext,LayoutInflater mInflater,List<GetCashOrderInfoVo> goodsList){
		this.context = mContext;
		this.mInflater = mInflater;
		this.ordersList = goodsList;
	}
	
	@Override
	public int getCount() {
		return ordersList.size();
	}

	@Override
	public GetCashOrderInfoVo getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.cash_order_detail_list_item, null);
			holder = new ViewHolder();
			holder.machine_goods_info_layout = (LinearLayout) convertView.findViewById(R.id.machine_goods_info_layout);
			holder.date_time = (TextView) convertView.findViewById(R.id.date_time);
			holder.machine_goods_info = (TextView) convertView.findViewById(R.id.machine_goods_info);
			holder.tv_sum = (TextView) convertView.findViewById(R.id.tv_sum);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			final GetCashOrderInfoVo mGoodsBase = ordersList.get(position);
			if(!TextUtils.isEmpty(mGoodsBase.getTime())){
				holder.date_time.setText(mGoodsBase.getTime());
			}
			if(!TextUtils.isEmpty(mGoodsBase.getMachineId())){
				holder.machine_goods_info.setText(mGoodsBase.getMachineId() + " 出售 " + mGoodsBase.getGoodsInfo());
			}else{
				holder.machine_goods_info.setText("售货机" + " 出售 " + mGoodsBase.getGoodsInfo());
			}
			holder.tv_sum.setText("收入：￥" + mGoodsBase.getAmount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return convertView;
	}
	
	static class ViewHolder {
		LinearLayout machine_goods_info_layout;
		TextView date_time;
		TextView machine_goods_info;
		TextView tv_sum;
	}

}
