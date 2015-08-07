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


public class BusinessRemindMenuDialog extends Dialog implements OnClickListener{

	private Button all_btn,gztx_btn,qhtx_btn; 
	private Button hdsjsp_btn,hdsjxp_btn,spxj_btn,shjxjzttx_btn; 
	private onButtonClick mListener;
	
	public BusinessRemindMenuDialog(Context context) {
		super(context,R.style.dialog);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_business_remind_menu);
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		all_btn = (Button) findViewById(R.id.all_btn);
		gztx_btn = (Button) findViewById(R.id.gztx_btn);
		qhtx_btn = (Button) findViewById(R.id.qhtx_btn);
		hdsjsp_btn = (Button) findViewById(R.id.hdsjsp_btn);
		hdsjxp_btn = (Button) findViewById(R.id.hdsjxp_btn);
		spxj_btn = (Button) findViewById(R.id.spxj_btn);
		shjxjzttx_btn = (Button) findViewById(R.id.shjxjzttx_btn);
		
		all_btn.setOnClickListener(this);
		gztx_btn.setOnClickListener(this);
		qhtx_btn.setOnClickListener(this);
		hdsjsp_btn.setOnClickListener(this);
		hdsjxp_btn.setOnClickListener(this);
		spxj_btn.setOnClickListener(this);
		shjxjzttx_btn.setOnClickListener(this);
	}
	
	public interface onButtonClick{
		public void onItemClick(String type);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.all_btn:
			if(mListener != null){
				mListener.onItemClick("");
			}
			BusinessRemindMenuDialog.this.dismiss();
			break;
		case R.id.gztx_btn:
			if(mListener != null){
				mListener.onItemClick(gztx_btn.getText().toString());
			}
			BusinessRemindMenuDialog.this.dismiss();
			break;
		case R.id.qhtx_btn:
			if(mListener != null){
				mListener.onItemClick(qhtx_btn.getText().toString());
			}
			BusinessRemindMenuDialog.this.dismiss();
			break;
		case R.id.hdsjsp_btn:
			if(mListener != null){
				mListener.onItemClick(hdsjsp_btn.getText().toString());
			}
			BusinessRemindMenuDialog.this.dismiss();
			break;
		case R.id.hdsjxp_btn:
			if(mListener != null){
				mListener.onItemClick(hdsjxp_btn.getText().toString());
			}
			BusinessRemindMenuDialog.this.dismiss();
			break;
		case R.id.spxj_btn:
			if(mListener != null){
				mListener.onItemClick(spxj_btn.getText().toString());
			}
			BusinessRemindMenuDialog.this.dismiss();
			break;
		case R.id.shjxjzttx_btn:
			if(mListener != null){
				mListener.onItemClick(shjxjzttx_btn.getText().toString());
			}
			BusinessRemindMenuDialog.this.dismiss();
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
