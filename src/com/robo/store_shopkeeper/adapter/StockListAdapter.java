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
import com.robo.store_shopkeeper.StockDetaillActivity;
import com.robo.store_shopkeeper.dao.GetShopStorageListVo;
import com.robo.store_shopkeeper.util.KeyUtil;

public class StockListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<GetShopStorageListVo> ordersList;
	
	public StockListAdapter(Context mContext,LayoutInflater mInflater,List<GetShopStorageListVo> goodsList){
		this.context = mContext;
		this.mInflater = mInflater;
		this.ordersList = goodsList;
	}
	
	@Override
	public int getCount() {
		return ordersList.size();
	}

	@Override
	public GetShopStorageListVo getItem(int position) {
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
			convertView = mInflater.inflate(R.layout.stock_list_item, null);
			holder = new ViewHolder();
			holder.stock_cover = (FrameLayout) convertView.findViewById(R.id.stock_cover);
			holder.stock_goods = (TextView) convertView.findViewById(R.id.stock_goods);
			holder.stock_barcode = (TextView) convertView.findViewById(R.id.stock_barcode);
			holder.stock_number = (TextView) convertView.findViewById(R.id.stock_number);
			holder.stock_machine_num = (TextView) convertView.findViewById(R.id.stock_machine_num);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final GetShopStorageListVo mGoodsBase = ordersList.get(position);
		holder.stock_goods.setText(mGoodsBase.getGoodsName());
		holder.stock_barcode.setText(mGoodsBase.getGoodsBarcode());
		holder.stock_number.setText(mGoodsBase.getStorageCount()+"");
		holder.stock_machine_num.setText(mGoodsBase.getMachineCount()+"");
		if(position % 2 == 0){
			holder.stock_cover.setBackgroundColor(context.getResources().getColor(R.color.white));
		}else{
			holder.stock_cover.setBackgroundColor(context.getResources().getColor(R.color.text_tint1));
		}
		holder.stock_cover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toDetailActivity(mGoodsBase.getGoodsBarcode(), mGoodsBase.getMachineId());
			}
		});
		
		return convertView;
	}
	
	private void toDetailActivity(String id, String macid){
		Bundle bundle = new Bundle();
		bundle.putString(KeyUtil.InStorageIdKey, id);
		bundle.putString(KeyUtil.MachineIdKey, macid);
		Intent intent = new Intent(context, StockDetaillActivity.class);
		intent.putExtra(KeyUtil.BundleKey, bundle);
		context.startActivity(intent);
	}
	
	static class ViewHolder {
		FrameLayout stock_cover;
		TextView stock_goods;
		TextView stock_barcode;
		TextView stock_number;
		TextView stock_machine_num;
	}

}
