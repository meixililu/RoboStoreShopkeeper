package com.robo.store_shopkeeper;

import java.util.HashMap;

import org.apache.http.Header;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.robo.store_shopkeeper.dao.CommonResponse;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.Md5;
import com.robo.store_shopkeeper.util.NumberUtil;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.ToastUtil;
import com.robo.store_shopkeeper.util.ValidUtil;

public class GoodsRukuActivity extends BaseActivity {

	private TextView history_btn,error_txt;
	private EditText goods_code_input,goods_info_input,number_input,delivery_number_input,service_keyword_input;
	private ImageButton scan_btn;
	private Button search_btn,submit_btn;
	private ProgressDialog progressDialog;
	
	private String goodsBarcode,deliveryCode,coId;
	private String count;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("商品入库");
		setContentView(R.layout.activity_goods_ruku);
		init();
	}
	
	private void init(){
		history_btn = (TextView) findViewById(R.id.history_btn);
		error_txt = (TextView) findViewById(R.id.error_txt);
		
		goods_code_input = (EditText) findViewById(R.id.goods_code_input);
		goods_info_input = (EditText) findViewById(R.id.goods_info_input);
		number_input = (EditText) findViewById(R.id.number_input);
		delivery_number_input = (EditText) findViewById(R.id.delivery_number_input);
		service_keyword_input = (EditText) findViewById(R.id.service_keyword_input);
		
		scan_btn = (ImageButton) findViewById(R.id.scan_btn);
		search_btn = (Button) findViewById(R.id.search_btn);
		submit_btn = (Button) findViewById(R.id.submit_btn);
		
		history_btn.setOnClickListener(this);
		scan_btn.setOnClickListener(this);
		search_btn.setOnClickListener(this);
		submit_btn.setOnClickListener(this);
	}
	
	private boolean valisData(){
		boolean isvalid = true;
		goodsBarcode = goods_code_input.getText().toString().trim();
		count = number_input.getText().toString().trim();
		deliveryCode = delivery_number_input.getText().toString().trim();
		coId = service_keyword_input.getText().toString().trim();
		int number = NumberUtil.StringToInt(count);
		if(TextUtils.isEmpty(goodsBarcode)){
			ToastUtil.diaplayMesShort(this, "请输入商品条码数字");
			isvalid = false;
		}
		if(TextUtils.isEmpty(deliveryCode)){
			ToastUtil.diaplayMesShort(this, "请输入配送单号");
			isvalid = false;
		}
		if(TextUtils.isEmpty(coId)){
			ToastUtil.diaplayMesShort(this, "请输入服务提供商");
			isvalid = false;
		}
		if(number < 1){
			ToastUtil.diaplayMesShort(this, "入库数量必须大于0");
			isvalid = false;
		}
		
		return isvalid;
	}
	
	private void RequestData(){
		if(valisData()){
			showDialog();
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("goodsBarcode", goodsBarcode);
			params.put("count", count);
			params.put("deliveryId", deliveryCode);
			params.put("coId", coId);
			RoboHttpClient.get(HttpParameter.goodUrl,"goodsInStorage", params, new TextHttpResponseHandler(){

				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					ToastUtil.diaplayMesLong(GoodsRukuActivity.this, "连接失败，请重试！");
				}

				@Override
				public void onSuccess(int arg0, Header[] arg1, String result) {
					CommonResponse mCommonResponse = ResultParse.parseResult(result,CommonResponse.class);
					if(ResultParse.handleResutl(GoodsRukuActivity.this, mCommonResponse)){
						ToastUtil.diaplayMesShort(GoodsRukuActivity.this, "入库成功");
					}
				}
				
				@Override
				public void onFinish() {
					progressDialog.dismiss();
				}
			});
		}
	}
	
	private void showDialog(){
		progressDialog = ProgressDialog.show(this, "", "正在提交...", true, false);
	}
	
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
		case R.id.history_btn:
			break;
		case R.id.scan_btn:
			break;
		case R.id.search_btn:
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
