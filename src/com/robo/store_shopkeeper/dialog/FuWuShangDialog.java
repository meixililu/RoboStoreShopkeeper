package com.robo.store_shopkeeper.dialog;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.robo.store_shopkeeper.R;
import com.robo.store_shopkeeper.adapter.SupplierDialogListAdapter;
import com.robo.store_shopkeeper.dao.QueryCoInfoVo;


public class FuWuShangDialog extends Dialog {

	private ListView content_lv; 
	private List<QueryCoInfoVo> list;
	private LayoutInflater mInflater;
	private onFuWuShangDialogListener mListener;
	private SupplierDialogListAdapter mAdapter;
	
	public FuWuShangDialog(Context context,List<QueryCoInfoVo> list) {
		super(context,R.style.dialog);
		this.list = list;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_supplier_list);
		mInflater = LayoutInflater.from(getContext());
		content_lv = (ListView) findViewById(R.id.content_lv);
		mAdapter = new SupplierDialogListAdapter(getContext(),mInflater,list,mListener);
		
		content_lv.setAdapter(mAdapter);
	}
	
	
	public interface onFuWuShangDialogListener{
		public void onItemClick(String coId);
	}


	public onFuWuShangDialogListener getmListener() {
		return mListener;
	}

	public void setmListener(onFuWuShangDialogListener mListener) {
		this.mListener = mListener;
	}
	
}
