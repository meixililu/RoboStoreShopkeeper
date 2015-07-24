package com.robo.store_shopkeeper;

import java.util.HashMap;

import org.apache.http.Header;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.ToastUtil;
import com.robo.store_shopkeeper.util.ValidUtil;

public class GoodsRukuActivity extends BaseActivity {

	private TextView history_btn,error_txt;
	private EditText goods_code_input,goods_info_input,number_input,delivery_number_input,service_keyword_input;
	private ImageButton scan_btn;
	private Button search_btn,submit_btn;
	private ProgressDialog progressDialog;
	
	private String goodsBarcode,deliveryCode,memo;
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
		boolean isNotEmpty = false;
		goodsBarcode = goods_code_input.getText().toString().trim();
		count = number_input.getText().toString().trim();
		
		
		return isNotEmpty;
	}
	
	private void RequestData(){
		if(ValidUtil.validPhoneData(this, "")){
			showDialog();
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("goodsBarcode", "0");
			params.put("count", "0");
			params.put("deliveryId", "0");
			params.put("coId", "0");
			RoboHttpClient.get(HttpParameter.goodUrl,"applyCheckCode", params, new TextHttpResponseHandler(){

				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					ToastUtil.diaplayMesLong(GoodsRukuActivity.this, "连接失败，请重试！");
				}

				@Override
				public void onSuccess(int arg0, Header[] arg1, String result) {
					CommonResponse mCommonResponse = ResultParse.parseResult(result,CommonResponse.class);
					if(ResultParse.handleResutl(GoodsRukuActivity.this, mCommonResponse)){
						ToastUtil.diaplayMesLong(GoodsRukuActivity.this, "验证码已发送");
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
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	
}
