package com.robo.store_shopkeeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.robo.store_shopkeeper.adapter.CashOrderDetailListAdapter;
import com.robo.store_shopkeeper.dao.GetCashOrderInfoResponse;
import com.robo.store_shopkeeper.dao.GetCashOrderInfoVo;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.ToastUtil;

public class CashOrderDetailActivity extends BaseActivity implements OnClickListener{

	private LayoutInflater inflater;
	private LinearLayout content_layout;
	private ListView mListView;
	private TextView order_sum;
	private Button confirm_to_refund;
	private CashOrderDetailListAdapter mCashOrderListAdapter;
	private List<GetCashOrderInfoVo> list;
	
	private String settleId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cash_order_detail);
		init();
		RequestData();
	}
	
	private void init(){
		Bundle bundel = getIntent().getBundleExtra(KeyUtil.BundleKey);
		settleId = bundel.getString(KeyUtil.SettleIdKey);
		inflater = LayoutInflater.from(this);
		list = new ArrayList<GetCashOrderInfoVo>();
		mCashOrderListAdapter = new CashOrderDetailListAdapter(this, inflater, list);
		initSwipeRefresh();
		content_layout = (LinearLayout) findViewById(R.id.content_layout);
		mListView = (ListView) findViewById(R.id.content_lv);
		order_sum = (TextView) findViewById(R.id.order_sum);
		confirm_to_refund = (Button) findViewById(R.id.confirm_to_refund);
		mListView.setAdapter(mCashOrderListAdapter);
	}
	
	public void onSwipeRefreshLayoutRefresh(){
		clearList();
		RequestData();
	}
	
	public void clearList(){
		list.clear();
		mCashOrderListAdapter.notifyDataSetChanged();
	}
	
	private void RequestData(){
		hideEmptyLayout();
		showProgressbar();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("shopId", "");
		params.put("settleId", settleId);
		RoboHttpClient.post(HttpParameter.orderUrl,"getCashOrderInfo", params, new TextHttpResponseHandler(){
			
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				showEmptyLayout_Error();
				ToastUtil.diaplayMesLong(CashOrderDetailActivity.this, CashOrderDetailActivity.this.getResources().getString(R.string.connet_fail));
			}
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, String result) {
				GetCashOrderInfoResponse mResponse = (GetCashOrderInfoResponse) ResultParse.parseResult(result,GetCashOrderInfoResponse.class);
				if(ResultParse.handleResutl(CashOrderDetailActivity.this, mResponse)){
					setData(mResponse);
					list.addAll(mResponse.getList());
					mCashOrderListAdapter.notifyDataSetChanged();
					content_layout.setVisibility(View.VISIBLE);
				}else{
					showEmptyLayout_Empty();
				}
			}
			
			@Override
			public void onFinish() {
				onSwipeRefreshLayoutFinish();
				hideProgressbar();
			}
		});
	}
	
	private void setData(GetCashOrderInfoResponse mResponse){
		setTitle(mResponse.getDate() + "结账详情");
		order_sum.setText("共计金额：￥" + mResponse.getAmount());
		if(mResponse.getCheckOutStatus() == 0){
			confirm_to_refund.setText("未支付");
		}else{
			confirm_to_refund.setText("已支付");
		}
	}
	
	@Override
	public void onClickEmptyLayoutRefresh() {
		onSwipeRefreshLayoutRefresh();
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
	}
	
	
}
