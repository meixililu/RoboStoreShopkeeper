package com.robo.store_shopkeeper.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.robo.store_shopkeeper.GoodsDetailActivity;
import com.robo.store_shopkeeper.R;
import com.robo.store_shopkeeper.dao.GetUndoListVo;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.TimeUtil;

public class GoodsRevokeListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<GetUndoListVo> ordersList;
	
	public GoodsRevokeListAdapter(Context mContext,LayoutInflater mInflater,List<GetUndoListVo> goodsList){
		this.context = mContext;
		this.mInflater = mInflater;
		this.ordersList = goodsList;
	}
	
	@Override
	public int getCount() {
		return ordersList.size();
	}

	@Override
	public GetUndoListVo getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.goods_revoke_list_item, null);
			holder = new ViewHolder();
			holder.goods_revoke_cover = (FrameLayout) convertView.findViewById(R.id.goods_revoke_cover);
			holder.goods_revoke_title = (TextView) convertView.findViewById(R.id.goods_revoke_title);
			holder.goods_revoke_order = (TextView) convertView.findViewById(R.id.goods_revoke_order);
			holder.goods_revoke_time = (TextView) convertView.findViewById(R.id.goods_revoke_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final GetUndoListVo mGoodsBase = ordersList.get(position);
		holder.goods_revoke_title.setText(mGoodsBase.getTitle());
		String status = "";
		if(mGoodsBase.getUndoStatus() == 1){
			holder.goods_revoke_order.setTextColor(context.getResources().getColor(R.color.text_grey));
			status = "已撤回";
		}else{
			holder.goods_revoke_order.setTextColor(context.getResources().getColor(R.color.app_color));
			status = "未撤回";
		}
		holder.goods_revoke_order.setText("["+ status +"]");
		holder.goods_revoke_time.setText(TimeUtil.compareDate(mGoodsBase.getDatetime()));
		
		//0：未撤回 1：已撤回
		holder.goods_revoke_cover.setOnClickListener(new OnClickListener() {
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
		FrameLayout goods_revoke_cover;
		TextView goods_revoke_title;
		TextView goods_revoke_order;
		TextView goods_revoke_time;
	}

}
