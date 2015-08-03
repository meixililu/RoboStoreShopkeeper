package com.robo.store_shopkeeper;

import java.util.HashMap;

import org.apache.http.Header;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robo.store_shopkeeper.dao.GetRadioResponse;
import com.robo.store_shopkeeper.dao.GetRadioVo;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.ToastUtil;


public class PerMonthIncomeDetailActivity extends BaseActivity implements OnClickListener{

	private LinearLayout content_layout;
	private TextView huodao_fencheng;
	private TextView ad_fencheng;
	private TextView changdi_fencheng;
	private TextView goods_fencheng;
	private TextView sum_tv;
	private GetRadioVo mResponse;
	private String month;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initTitle();
		setContentView(R.layout.activity_per_month_income_detail);
		init();
		RequestData();
	}
	
	private void initTitle(){
		Bundle bundle = getIntent().getBundleExtra(KeyUtil.BundleKey);
		month = bundle.getString(KeyUtil.MonthKey);
		setTitle(month+"分成详情");
	}
	
	private void init(){
		content_layout = (LinearLayout) findViewById(R.id.content_layout);
		huodao_fencheng = (TextView) findViewById(R.id.huodao_fencheng);
		ad_fencheng = (TextView) findViewById(R.id.ad_fencheng);
		changdi_fencheng = (TextView) findViewById(R.id.changdi_fencheng);
		goods_fencheng = (TextView) findViewById(R.id.goods_fencheng);
		sum_tv = (TextView) findViewById(R.id.sum_tv);
	}
	
	private void setData(){
		content_layout.setVisibility(View.VISIBLE);
		huodao_fencheng.setText(mResponse.getGoodsWayRadio());
		ad_fencheng.setText(mResponse.getAdRatio());
		changdi_fencheng.setText(mResponse.getShopinRentRadio());
		goods_fencheng.setText(mResponse.getSelfProfitRadio());
		sum_tv.setText(mResponse.getPrice());
	}
	
	private void RequestData(){
		hideEmptyLayout();
		showProgressbar();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("shopId", "");
		params.put("month", month);
		RoboHttpClient.post(HttpParameter.shopsUrl,"getRadio", params, new TextHttpResponseHandler(){
			
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				showEmptyLayout_Error();
				ToastUtil.diaplayMesLong(PerMonthIncomeDetailActivity.this, PerMonthIncomeDetailActivity.this.getResources().getString(R.string.connet_fail));
			}
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, String result) {
				GetRadioResponse response = (GetRadioResponse) ResultParse.parseResult(result,GetRadioResponse.class);
				if(ResultParse.handleResutl(PerMonthIncomeDetailActivity.this, response)){
					if(response.getList().size() > 0){
						mResponse = response.getList().get(0);
						setData();
					}
				}else{
					showEmptyLayout_Empty();
				}
			}
			
			@Override
			public void onFinish() {
				hideProgressbar();
			}
		});
	}
	
	public void onClickEmptyLayoutRefresh(){
		RequestData();
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
	}
}
