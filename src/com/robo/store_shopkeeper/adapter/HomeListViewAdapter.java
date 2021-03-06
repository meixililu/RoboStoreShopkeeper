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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.robo.store_shopkeeper.GoodsDetailActivity;
import com.robo.store_shopkeeper.R;
import com.robo.store_shopkeeper.dao.GoodsBase;
import com.robo.store_shopkeeper.util.CartUtil;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.ToastUtil;

public class HomeListViewAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<GoodsBase> goodsList;
	
	public HomeListViewAdapter(Context mContext,LayoutInflater mInflater,List<GoodsBase> goodsList){
		this.context = mContext;
		this.mInflater = mInflater;
		this.goodsList = goodsList;
	}
	
	@Override
	public int getCount() {
		return goodsList.size();
	}

	@Override
	public GoodsBase getItem(int position) {
		return goodsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.home_good_list_item, null);
			holder = new ViewHolder();
			holder.item_cover = (FrameLayout) convertView.findViewById(R.id.item_cover);
			holder.good_icon = (ImageView) convertView.findViewById(R.id.good_icon);
			holder.good_buy = (ImageButton) convertView.findViewById(R.id.good_buy);
			holder.good_name = (TextView) convertView.findViewById(R.id.good_name);
			holder.good_price_new = (TextView) convertView.findViewById(R.id.good_price_new);
			holder.good_price_old = (TextView) convertView.findViewById(R.id.good_price_old);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final GoodsBase mGoodsBase = goodsList.get(position);
		holder.good_name.setText(mGoodsBase.getGoodsName());
		holder.good_price_new.setText("￥" + mGoodsBase.getVipPrice());
		holder.good_price_old.setText("￥" + mGoodsBase.getRetailPrice());
		
		holder.item_cover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toGoodsDetailActivity(mGoodsBase.getGoodsBarcode());
			}
		});
		holder.good_buy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ToastUtil.diaplayMesShort(context, mGoodsBase.getGoodsName()+"已加入购物车");
				CartUtil.addToCart(mGoodsBase, 1);
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
		FrameLayout item_cover;
		ImageView good_icon;
		ImageButton good_buy;
		TextView good_name;
		TextView good_price_new;
		TextView good_price_old;
	}

}
