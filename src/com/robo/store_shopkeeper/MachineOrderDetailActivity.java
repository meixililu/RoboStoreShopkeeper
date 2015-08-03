package com.robo.store_shopkeeper;

import java.util.HashMap;

import org.apache.http.Header;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robo.store_shopkeeper.dao.GetOrderDetailGoodVo;
import com.robo.store_shopkeeper.dao.GetOrderDetailResponse;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.ToastUtil;


public class MachineOrderDetailActivity extends BaseActivity implements OnClickListener{

	private LayoutInflater mInflater;
	private LinearLayout content_layout;
	private TextView ruku_time;
	private TextView ruku_macid;
	private TextView ruku_orderid;
	private TextView ruku_order_sum;
	private LinearLayout ruku_goods_list;
	private GetOrderDetailResponse mResponse;
	private String pickupId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_machine_detail);
		init();
		RequestData();
	}
	
	private void init(){
		mInflater = LayoutInflater.from(this);
		Bundle bundle = getIntent().getBundleExtra(KeyUtil.BundleKey);
		pickupId = bundle.getString(KeyUtil.PickupIdKey);
		
		content_layout = (LinearLayout) findViewById(R.id.content_layout);
		ruku_goods_list = (LinearLayout) findViewById(R.id.ruku_goods_list);
		ruku_time = (TextView) findViewById(R.id.ruku_time);
		ruku_macid = (TextView) findViewById(R.id.ruku_macid);
		ruku_orderid = (TextView) findViewById(R.id.ruku_orderid);
		ruku_order_sum = (TextView) findViewById(R.id.ruku_order_sum);
	}
	
	private void setData(){
		content_layout.setVisibility(View.VISIBLE);
		ruku_time.setText(mResponse.getDatetime());
		ruku_macid.setText(mResponse.getMachineId());
		ruku_orderid.setText(mResponse.getOrderId());
		ruku_order_sum.setText("￥"+mResponse.getPrice());
		initList();
	}
	
	private void initList(){
		for(GetOrderDetailGoodVo bean : mResponse.getList()){
			TextView view = new TextView(this);
			view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			view.setTextColor(this.getResources().getColor(R.color.app_color));
			view.setText(bean.getGoodsName() + " x " + bean.getCount());
			ruku_goods_list.addView(view);
		}
	}
	
	private void RequestData(){
		hideEmptyLayout();
		showProgressbar();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("shopId", "");
		params.put("pickupId", pickupId);
		RoboHttpClient.post(HttpParameter.orderUrl,"getOrderDetail", params, new TextHttpResponseHandler(){
			
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				showEmptyLayout_Error();
				ToastUtil.diaplayMesLong(MachineOrderDetailActivity.this, MachineOrderDetailActivity.this.getResources().getString(R.string.connet_fail));
			}
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, String result) {
				mResponse = (GetOrderDetailResponse) ResultParse.parseResult(result,GetOrderDetailResponse.class);
				if(ResultParse.handleResutl(MachineOrderDetailActivity.this, mResponse)){
					setData();
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
