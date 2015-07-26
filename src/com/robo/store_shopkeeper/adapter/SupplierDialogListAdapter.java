package com.robo.store_shopkeeper.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.robo.store_shopkeeper.R;
import com.robo.store_shopkeeper.dao.QueryCoInfoVo;
import com.robo.store_shopkeeper.dialog.FuWuShangDialog.onFuWuShangDialogListener;

public class SupplierDialogListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	private List<QueryCoInfoVo> list;
	private onFuWuShangDialogListener mListener;
	
	public SupplierDialogListAdapter(Context mContext,LayoutInflater mInflater,List<QueryCoInfoVo> list
			,onFuWuShangDialogListener mListener){
		this.context = mContext;
		this.mInflater = mInflater;
		this.list = list;
		this.mListener = mListener;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public QueryCoInfoVo getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.dialog_supplier_list_item, null);
			holder = new ViewHolder();
			holder.item_cover = (FrameLayout) convertView.findViewById(R.id.item_cover);
			holder.supplier_name = (TextView) convertView.findViewById(R.id.supplier_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final QueryCoInfoVo mQueryCoInfoVo = list.get(position);
		holder.supplier_name.setText(mQueryCoInfoVo.getCoName());
		holder.item_cover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mListener != null){
					mListener.onItemClick(mQueryCoInfoVo.getCoId());
				}
			}
		});
		return convertView;
	}
	
	static class ViewHolder {
		FrameLayout item_cover;
		TextView supplier_name;
	}

}
