package com.robo.store_shopkeeper;

import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.materialdesign.widgets.Dialog;
import com.robo.store_shopkeeper.dao.CommonResponse;
import com.robo.store_shopkeeper.dao.QueryCoInfoResponse;
import com.robo.store_shopkeeper.dao.QueryCoInfoVo;
import com.robo.store_shopkeeper.dao.QueryGoodsInfoResponse;
import com.robo.store_shopkeeper.dialog.FuWuShangDialog;
import com.robo.store_shopkeeper.dialog.FuWuShangDialog.onFuWuShangDialogListener;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.LogUtil;
import com.robo.store_shopkeeper.util.LoginUtil;
import com.robo.store_shopkeeper.util.NumberUtil;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.SPUtil;
import com.robo.store_shopkeeper.util.ToastUtil;
import com.robo.store_shopkeeper.util.UnicodeToStr;

public class FaultRepairActivity extends BaseActivity {

	private EditText machine_id_input,error_input;
	private Button submit_btn;
	private ProgressDialog progressDialog;
	
	private String machine_id,error;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fault_repair);
		init();
	}
	
	private void init(){
		machine_id_input = (EditText) findViewById(R.id.machine_id_input);
		error_input = (EditText) findViewById(R.id.error_input);
		submit_btn = (Button) findViewById(R.id.submit_btn);
		
		submit_btn.setOnClickListener(this);
	}
	
	private boolean validData(){
		boolean isvalid = true;
		machine_id = machine_id_input.getText().toString().trim();
		error = error_input.getText().toString().trim();
		if(TextUtils.isEmpty(machine_id)){
			ToastUtil.diaplayMesShort(this, "请输入售货机编号");
			isvalid = false;
		}
		if(TextUtils.isEmpty(error)){
			ToastUtil.diaplayMesShort(this, "请输入故障说明");
			isvalid = false;
		}
		return isvalid;
	}
	
	private void RequestData(){
		if(validData()){
			final ProgressDialog progressDialog = ProgressDialog.show(this, "", "正在提交...", true, false);
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("machineId", machine_id);
			params.put("failureInfo", error);
			RoboHttpClient.get(HttpParameter.shopsUrl,"uploadFailure", params, new TextHttpResponseHandler(){

				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					ToastUtil.diaplayMesLong(FaultRepairActivity.this, "连接失败，请重试！");
				}

				@Override
				public void onSuccess(int arg0, Header[] arg1, String result) {
					CommonResponse mCommonResponse = ResultParse.parseResult(result,CommonResponse.class);
					if(ResultParse.handleResutl(FaultRepairActivity.this, mCommonResponse)){
						showSucceeDialog();
					}
				}
				
				@Override
				public void onFinish() {
					progressDialog.dismiss();
				}
			});
		}
	}
	
	private void showSucceeDialog(){
		try {
			Dialog dialog = new Dialog(this, "报修成功", "故障已报修，我们会尽快处理！");
			dialog.addAcceptButton("确定");
			dialog.setOnAcceptButtonClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					FaultRepairActivity.this.finish();
				}
			});
			dialog.setCancelable(true);
			dialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch(v.getId()){
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
