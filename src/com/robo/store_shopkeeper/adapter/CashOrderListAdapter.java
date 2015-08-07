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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.robo.store_shopkeeper.CashOrderDetailActivity;
import com.robo.store_shopkeeper.R;
import com.robo.store_shopkeeper.dao.GetCashOrderListVo;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.TimeUtil;

public class CashOrderListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<GetCashOrderListVo> ordersList;
	
	public CashOrderListAdapter(Context mContext,LayoutInflater mInflater,List<GetCashOrderListVo> goodsList){
		this.context = mContext;
		this.mInflater = mInflater;
		this.ordersList = goodsList;
	}
	
	@Override
	public int getCount() {
		return ordersList.size();
	}

	@Override
	public GetCashOrderListVo getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.cash_order_list_item, null);
			holder = new ViewHolder();
			holder.item_cover = (FrameLayout) convertView.findViewById(R.id.item_cover);
			holder.date_day = (TextView) convertView.findViewById(R.id.date_day);
			holder.date_month = (TextView) convertView.findViewById(R.id.date_month);
			holder.tv_sum = (TextView) convertView.findViewById(R.id.tv_sum);
			holder.check_or_pay_btn = (Button) convertView.findViewById(R.id.check_or_pay_btn);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final GetCashOrderListVo mGoodsBase = ordersList.get(position);
		holder.date_day.setText(TimeUtil.customFormatDate(mGoodsBase.getDate(),TimeUtil.DayFormat,"d")+"日");
		holder.date_month.setText(TimeUtil.customFormatDate(mGoodsBase.getDate(),TimeUtil.DayFormat,"M")+"月");
		holder.tv_sum.setText("￥" + mGoodsBase.getAmount());
		
//		// 0：未结算1：已结算
		if(mGoodsBase.getCheckOutStatus() == 0){
			holder.check_or_pay_btn.setText("未支付");
			holder.check_or_pay_btn.setBackgroundResource(R.drawable.btn_login_selector);
		}else{
			holder.check_or_pay_btn.setText("已支付");
			holder.check_or_pay_btn.setBackgroundResource(R.drawable.btn_identity_selector);
		}
		holder.item_cover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toDetailActivity(mGoodsBase.getSettleId());
			}
		});
		holder.check_or_pay_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toDetailActivity(mGoodsBase.getSettleId());
			}
		});
		
		return convertView;
	}
	
	private void toDetailActivity(String id){
		Bundle bundle = new Bundle();
		bundle.putString(KeyUtil.SettleIdKey, id);
		Intent intent = new Intent(context, CashOrderDetailActivity.class);
		intent.putExtra(KeyUtil.BundleKey, bundle);
		context.startActivity(intent);
	}
	
	static class ViewHolder {
		FrameLayout item_cover;
		TextView date_day;
		TextView date_month;
		TextView tv_sum;
		Button check_or_pay_btn;
	}

}
