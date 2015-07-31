package com.robo.store_shopkeeper;

import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.robo.store_shopkeeper.dao.CommonResponse;
import com.robo.store_shopkeeper.dao.QueryCoInfoResponse;
import com.robo.store_shopkeeper.dao.QueryCoInfoVo;
import com.robo.store_shopkeeper.dao.QueryGoodsInfoResponse;
import com.robo.store_shopkeeper.dialog.FuWuShangDialog;
import com.robo.store_shopkeeper.dialog.FuWuShangDialog.onFuWuShangDialogListener;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.LogUtil;
import com.robo.store_shopkeeper.util.NumberUtil;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.ToastUtil;
import com.robo.store_shopkeeper.util.UnicodeToStr;

public class GoodsReportOrReturnActivity extends BaseActivity {

	private TextView history_btn,error_txt;
	private EditText goods_code_input,goods_info_input,number_input,delivery_number_input,service_keyword_input,damage_memo;
	private ImageButton scan_btn;
	private ImageView refresh_goods_info;
	private Button search_btn,submit_btn;
	private ProgressDialog progressDialog;
	
	private String goodsBarcode,deliveryCode,coId,coName,damageMemo;
	private String count;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("商品报损退货管理");
		setContentView(R.layout.activity_goods_report_return);
		init();
	}
	
	private void init(){
		getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		history_btn = (TextView) findViewById(R.id.history_btn);
		error_txt = (TextView) findViewById(R.id.error_txt);
		refresh_goods_info = (ImageView) findViewById(R.id.refresh_goods_info);
		
		goods_code_input = (EditText) findViewById(R.id.goods_code_input);
		goods_info_input = (EditText) findViewById(R.id.goods_info_input);
		number_input = (EditText) findViewById(R.id.number_input);
		delivery_number_input = (EditText) findViewById(R.id.delivery_number_input);
		service_keyword_input = (EditText) findViewById(R.id.service_keyword_input);
		damage_memo = (EditText) findViewById(R.id.damage_memo);
		
		scan_btn = (ImageButton) findViewById(R.id.scan_btn);
		search_btn = (Button) findViewById(R.id.search_btn);
		submit_btn = (Button) findViewById(R.id.submit_btn);
		
		history_btn.setOnClickListener(this);
		scan_btn.setOnClickListener(this);
		search_btn.setOnClickListener(this);
		submit_btn.setOnClickListener(this);
		refresh_goods_info.setOnClickListener(this);
		goods_code_input.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					RequestGoodInfoData();
				}
			}
		});
	}
	
	private void RequestGoodInfoData(){
		//6923450657935
		goodsBarcode = goods_code_input.getText().toString().trim();
		if(!TextUtils.isEmpty(goodsBarcode)){
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("goodsBarcode", goodsBarcode);
			RoboHttpClient.post(HttpParameter.goodUrl,"queryGoodsInfo", params, new TextHttpResponseHandler(){
				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					LogUtil.DefalutLog("连接失败，请重试！");
					goods_info_input.setText("");
				}
				@Override
				public void onSuccess(int arg0, Header[] arg1, String result) {
					QueryGoodsInfoResponse mCommonResponse = (QueryGoodsInfoResponse) ResultParse.parseResult(result,QueryGoodsInfoResponse.class);
					if(ResultParse.handleResutl(GoodsReportOrReturnActivity.this, mCommonResponse)){
						goods_info_input.setText(mCommonResponse.getGoodsName());
					}else{
						goods_info_input.setText("");
					}
				}
				@Override
				public void onFinish() {
				}
			});
		}
	}
	
	private boolean valisData(){
		boolean isvalid = true;
		goodsBarcode = goods_code_input.getText().toString().trim();
		count = number_input.getText().toString().trim();
		deliveryCode = delivery_number_input.getText().toString().trim();
		damageMemo = damage_memo.getText().toString().trim();
		int number = NumberUtil.StringToInt(count);
		if(TextUtils.isEmpty(goodsBarcode)){
			ToastUtil.diaplayMesShort(this, "请输入商品条码数字");
			isvalid = false;
		}
		if(TextUtils.isEmpty(deliveryCode)){
			ToastUtil.diaplayMesShort(this, "请输入配送单号");
			isvalid = false;
		}
		if(TextUtils.isEmpty(damageMemo)){
			ToastUtil.diaplayMesShort(this, "请输入报损退货说明");
			isvalid = false;
		}
		if(TextUtils.isEmpty(coId)){
			ToastUtil.diaplayMesShort(this, "请输入服务提供商");
			isvalid = false;
		}
		if(number < 1){
			ToastUtil.diaplayMesShort(this, "报损数量必须大于0");
			isvalid = false;
		}
		
		return isvalid;
	}
	
	private void RequestData(){
		if(valisData()){
			final ProgressDialog progressDialog = ProgressDialog.show(this, "", "正在提交...", true, false);
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("goodsBarcode", goodsBarcode);
			params.put("count", count);
			params.put("deliveryId", deliveryCode);
			params.put("coId", coId);
			params.put("damageMemo", damageMemo);
			RoboHttpClient.post(HttpParameter.goodUrl,"goodsDamageStorage", params, new TextHttpResponseHandler(){

				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					ToastUtil.diaplayMesLong(GoodsReportOrReturnActivity.this, "连接失败，请重试！");
				}

				@Override
				public void onSuccess(int arg0, Header[] arg1, String result) {
					CommonResponse mCommonResponse = ResultParse.parseResult(result,CommonResponse.class);
					if(ResultParse.handleResutl(GoodsReportOrReturnActivity.this, mCommonResponse)){
						ToastUtil.diaplayMesShort(GoodsReportOrReturnActivity.this, "报损成功");
					}
				}
				
				@Override
				public void onFinish() {
					progressDialog.dismiss();
				}
			});
		}
	}
	
	private boolean validFuFuData(){
		boolean isvalid = true;
		coName = service_keyword_input.getText().toString().trim();
		if(TextUtils.isEmpty(coName)){
			ToastUtil.diaplayMesShort(this, "请输入服务提供商");
			isvalid = false;
		}
		return isvalid;
	}
	
	private void RequestFuWuData(){
		if(validFuFuData()){
			final ProgressDialog progressDialog = ProgressDialog.show(this, "", "正在加载...", true, false);
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("coName", UnicodeToStr.toUnicode(coName));
			RoboHttpClient.post(HttpParameter.goodUrl,"queryCoInfo", params, new TextHttpResponseHandler(){

				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					ToastUtil.diaplayMesShort(GoodsReportOrReturnActivity.this, "连接失败，请重试！");
				}

				@Override
				public void onSuccess(int arg0, Header[] arg1, String result) {
					QueryCoInfoResponse mCommonResponse = (QueryCoInfoResponse) ResultParse.parseResult(result,QueryCoInfoResponse.class);
					if(ResultParse.handleResutl(GoodsReportOrReturnActivity.this, mCommonResponse)){
						if(mCommonResponse.getList().size() > 0){
							showSupplierDialog(mCommonResponse.getList());
						}else{
							ToastUtil.diaplayMesShort(GoodsReportOrReturnActivity.this, "没有相关信息，请重试");
						}
					}
				}
				
				@Override
				public void onFinish() {
					progressDialog.dismiss();
				}
			});
		}
	}
	
	private void showSupplierDialog(List<QueryCoInfoVo> list){
		final FuWuShangDialog mDialog = new FuWuShangDialog(this,list);
		mDialog.setmListener(new onFuWuShangDialogListener() {
			@Override
			public void onItemClick(String cid, String name) {
				coId = cid;
				service_keyword_input.setText(name);
				mDialog.dismiss();
			}
		});
		mDialog.show();
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		case R.id.history_btn:
			toActivity(RukuHistoryActivity.class, null);
			break;
		case R.id.refresh_goods_info:
			RequestGoodInfoData();
			break;
		case R.id.scan_btn:
			break;
		case R.id.search_btn:
			RequestFuWuData();
			break;
		case R.id.submit_btn:
			RequestData();
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	
}
