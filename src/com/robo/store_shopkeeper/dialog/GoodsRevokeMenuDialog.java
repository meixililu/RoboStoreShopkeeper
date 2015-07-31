package com.robo.store_shopkeeper.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.robo.store_shopkeeper.R;


public class GoodsRevokeMenuDialog extends Dialog implements OnClickListener{

	private Button all_btn,non_payment_btn,account_paid_btn; 
	private onButtonClick mListener;
	
	public GoodsRevokeMenuDialog(Context context) {
		super(context,R.style.dialog);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_goods_revoke_menu);
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		all_btn = (Button) findViewById(R.id.all_btn);
		non_payment_btn = (Button) findViewById(R.id.non_payment_btn);
		account_paid_btn = (Button) findViewById(R.id.account_paid_btn);
		
		all_btn.setOnClickListener(this);
		non_payment_btn.setOnClickListener(this);
		account_paid_btn.setOnClickListener(this);
	}
	
	public interface onButtonClick{
		public void onItemClick(String type);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.all_btn:
			if(mListener != null){
				mListener.onItemClick("9");
			}
			GoodsRevokeMenuDialog.this.dismiss();
			break;
		case R.id.non_payment_btn:
			if(mListener != null){
				mListener.onItemClick("0");
			}
			GoodsRevokeMenuDialog.this.dismiss();
			break;
		case R.id.account_paid_btn:
			if(mListener != null){
				mListener.onItemClick("1");
			}
			GoodsRevokeMenuDialog.this.dismiss();
			break;
		}
	}
	
	public onButtonClick getmListener() {
		return mListener;
	}

	public void setmListener(onButtonClick mListener) {
		this.mListener = mListener;
	}
}
