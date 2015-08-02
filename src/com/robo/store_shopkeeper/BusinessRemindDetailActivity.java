package com.robo.store_shopkeeper;

import com.robo.store_shopkeeper.util.KeyUtil;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BusinessRemindDetailActivity extends BaseActivity implements OnClickListener{

	private TextView title_tv;
	private TextView time_tv;
	private TextView remind_detail_tv;
	private String title,content, time;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_remind_detail);
		init();
	}
	
	private void init(){
		Bundle bundle = getIntent().getBundleExtra(KeyUtil.BundleKey);
		title = bundle.getString(KeyUtil.RemindDetailTitleKey);
		content = bundle.getString(KeyUtil.RemindDetailContentKey);
		time = bundle.getString(KeyUtil.RemindDetailTimeKey);
		
		title_tv = (TextView) findViewById(R.id.title_tv);
		time_tv = (TextView) findViewById(R.id.time_tv);
		remind_detail_tv = (TextView) findViewById(R.id.remind_detail_tv);
		
		title_tv.setText(title);
		time_tv.setText(time);
		remind_detail_tv.setText(content);
		
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
	}
}
