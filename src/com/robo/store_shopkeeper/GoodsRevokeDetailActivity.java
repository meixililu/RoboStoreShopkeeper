package com.robo.store_shopkeeper;

import java.util.HashMap;

import org.apache.http.Header;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robo.store_shopkeeper.dao.CommonResponse;
import com.robo.store_shopkeeper.dao.GetUndoInfoResponse;
import com.robo.store_shopkeeper.dao.GetUndoInfoVo;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.TimeUtil;
import com.robo.store_shopkeeper.util.ToastUtil;


public class GoodsRevokeDetailActivity extends BaseActivity implements OnClickListener{

	private LayoutInflater mInflater;
	private LinearLayout content_layout;
	private TextView title_time;
	private TextView service_name_tv;
	private TextView revoke_submit_time;
	private LinearLayout goods_list;
	private EditText delivery_number_input;
	private Button confirm_to_revoke;
	private LinearLayout revoke_time_layout;
	private GetUndoInfoResponse mResponse;
	private String undoId;
	private String deliveryCode;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_revoke_detail);
		init();
		RequestData();
	}
	
	private void init(){
		mInflater = LayoutInflater.from(this);
		Bundle bundle = getIntent().getBundleExtra(KeyUtil.BundleKey);
		undoId = bundle.getString(KeyUtil.UndoIdKey);
		
		content_layout = (LinearLayout) findViewById(R.id.content_layout);
		title_time = (TextView) findViewById(R.id.title_time);
		service_name_tv = (TextView) findViewById(R.id.service_name_tv);
		goods_list = (LinearLayout) findViewById(R.id.goods_list);
		delivery_number_input = (EditText) findViewById(R.id.delivery_number_input);
		confirm_to_revoke = (Button) findViewById(R.id.confirm_to_revoke);
		revoke_submit_time = (TextView) findViewById(R.id.revoke_submit_time);
		revoke_time_layout = (LinearLayout) findViewById(R.id.revoke_time_layout);
		
		confirm_to_revoke.setOnClickListener(this);
	}
	
	private void setData(){
		content_layout.setVisibility(View.VISIBLE);
		//0：未撤回1：已撤回
		if(mResponse.getUndoStatus() == 0){
			revoke_time_layout.setVisibility(View.GONE);
		}else{
			delivery_number_input.setEnabled(false);
			revoke_submit_time.setText(mResponse.getUndotime());
			delivery_number_input.setText(mResponse.getDeliveryId());
			confirm_to_revoke.setVisibility(View.GONE);
			revoke_time_layout.setVisibility(View.VISIBLE);
		}
		title_time.setText(TimeUtil.compareDate(mResponse.getUndotime()));
		service_name_tv.setText(mResponse.getCoName());
		initList();
	}
	
	private void initList(){
		for(GetUndoInfoVo bean : mResponse.getList()){
			View view = mInflater.inflate(R.layout.goods_revoke_detail_list_item, null);
			TextView goods_id = (TextView) view.findViewById(R.id.goods_id);
			TextView goods_name = (TextView) view.findViewById(R.id.goods_name);
			TextView goods_number = (TextView) view.findViewById(R.id.goods_number);
			goods_id.setText(bean.getGoodsBarcode());
			goods_name.setText(bean.getGoodsName());
			goods_number.setText(bean.getCount()+"");
			goods_list.addView(view);
		}
	}
	
	private void RequestData(){
		hideEmptyLayout();
		showProgressbar();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("shopId", "");
		params.put("undoId", undoId);
		RoboHttpClient.post(HttpParameter.goodUrl,"getUndoInfo", params, new TextHttpResponseHandler(){
			
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				showEmptyLayout_Error();
				ToastUtil.diaplayMesLong(GoodsRevokeDetailActivity.this, GoodsRevokeDetailActivity.this.getResources().getString(R.string.connet_fail));
			}
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, String result) {
				mResponse = (GetUndoInfoResponse) ResultParse.parseResult(result,GetUndoInfoResponse.class);
				if(ResultParse.handleResutl(GoodsRevokeDetailActivity.this, mResponse)){
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
		switch (v.getId()) {
		case R.id.confirm_to_revoke:
			SubmitData();
			break;
		}
	}
	
	private boolean validData(){
		boolean isvalid = true;
		deliveryCode = delivery_number_input.getText().toString().trim();
		if(TextUtils.isEmpty(deliveryCode)){
			ToastUtil.diaplayMesShort(this, "请输入配送单号");
			isvalid = false;
		}
		return isvalid;
	}
	
	private void SubmitData(){
		if(validData()){
			final ProgressDialog progressDialog = ProgressDialog.show(this, "", "正在撤回...", true, false);
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("shopId", "");
			params.put("undoId", undoId);
			params.put("deliveryId", deliveryCode);
			RoboHttpClient.post(HttpParameter.goodUrl,"goodsUndo", params, new TextHttpResponseHandler(){

				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					ToastUtil.diaplayMesLong(GoodsRevokeDetailActivity.this, "连接失败，请重试！");
				}

				@Override
				public void onSuccess(int arg0, Header[] arg1, String result) {
					CommonResponse mCommonResponse = ResultParse.parseResult(result,CommonResponse.class);
					if(ResultParse.handleResutl(GoodsRevokeDetailActivity.this, mCommonResponse)){
						ToastUtil.diaplayMesShort(GoodsRevokeDetailActivity.this, "撤回成功");
						GoodsRevokeDetailActivity.this.finish();
					}
				}
				
				@Override
				public void onFinish() {
					progressDialog.dismiss();
				}
			});
		}
	}
}
