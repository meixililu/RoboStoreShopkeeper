package com.robo.store_shopkeeper;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MoreActivity extends BaseActivity implements OnClickListener{

	private FrameLayout check_update_cover;
	private TextView soft_version_text;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
		init();
	}
	
	private void init(){
		check_update_cover = (FrameLayout) findViewById(R.id.check_update_cover);
		soft_version_text = (TextView) findViewById(R.id.soft_version_text);
		
		check_update_cover.setOnClickListener(this);
		soft_version_text.setText(getVersion());
	}
	
	public String getVersion() {
		try {
			PackageManager manager = this.getPackageManager();
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return "1.0";
		}
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		case R.id.check_update_cover:
			checkSoftUpdate();
			break;
		}
	}
	
	private void checkSoftUpdate(){
		
	}
}
