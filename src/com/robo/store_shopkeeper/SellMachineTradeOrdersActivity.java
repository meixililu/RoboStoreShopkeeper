package com.robo.store_shopkeeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;

import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.robo.store_shopkeeper.adapter.SellMachineTradeOrderListAdapter;
import com.robo.store_shopkeeper.dao.GetAllOrdersResponse;
import com.robo.store_shopkeeper.dao.GetAllOrdersVo;
import com.robo.store_shopkeeper.dialog.CashOrderMenuDialog;
import com.robo.store_shopkeeper.dialog.CashOrderMenuDialog.onButtonClick;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.LogUtil;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.Settings;
import com.robo.store_shopkeeper.util.ToastUtil;

public class SellMachineTradeOrdersActivity extends BaseActivity implements OnClickListener{

	private LayoutInflater inflater;
	private ListView mListView;
	private TextView search_type_btn;
	private SellMachineTradeOrderListAdapter mCashOrderListAdapter;
	private List<GetAllOrdersVo> list;
	
	private View footerView;
	private LinearLayout load_more_data;
	private TextView no_more_data;
	public int pageIndex = 0;
	private boolean isLoadMoreData;
	private boolean isFinishloadData = true;
	private String type;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initTitle();
		setContentView(R.layout.activity_cash_order_list);
		init();
		RequestData();
	}
	
	private void initTitle(){
		Bundle bundle1 = getIntent().getBundleExtra(KeyUtil.BundleKey);
		type = bundle1.getString(KeyUtil.OnlineOffLineKey);
		if(type.equals("0")){
			setTitle("线下交易订单");
		}else{
			setTitle("线上交易订单");
		}
	}
	
	private void init(){
		inflater = LayoutInflater.from(this);
		list = new ArrayList<GetAllOrdersVo>();
		mCashOrderListAdapter = new SellMachineTradeOrderListAdapter(this, inflater, list);
		initSwipeRefresh();
		search_type_btn = (TextView) findViewById(R.id.search_type_btn);
		search_type_btn.setVisibility(View.GONE);;
		mListView = (ListView) findViewById(R.id.content_lv);
		
		footerView = inflater.inflate(R.layout.list_footer_view, null);
		load_more_data = (LinearLayout) footerView.findViewById(R.id.load_more_data);
		no_more_data = (TextView) footerView.findViewById(R.id.no_more_data);
		footerView.setVisibility(View.GONE);
		mListView.addFooterView(footerView);
		
		setListOnScrollListener();
		mListView.setAdapter(mCashOrderListAdapter);
		
	}
	
	public void setListOnScrollListener(){
		mListView.setOnScrollListener(new OnScrollListener() {  
            private int lastItemIndex;
            @Override  
            public void onScrollStateChanged(AbsListView view, int scrollState) { 
                if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && lastItemIndex == mCashOrderListAdapter.getCount() - 1) {  
                	LogUtil.DefalutLog("onScrollStateChanged---update");
            		if(isLoadMoreData){
            			RequestData();
            		}
                }  
            }  
            @Override  
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {  
                lastItemIndex = firstVisibleItem + visibleItemCount - 2;  
            }  
        });
	}
	
	public void onSwipeRefreshLayoutRefresh(){
		clearList();
		RequestData();
	}
	
	public void clearList(){
		pageIndex = 0;
		list.clear();
		footerView.setVisibility(View.GONE);
		mCashOrderListAdapter.notifyDataSetChanged();
		load_more_data.setVisibility(View.VISIBLE);
		no_more_data.setVisibility(View.GONE);
	}
	
	private void RequestData(){
		if(isFinishloadData){
			hideEmptyLayout();
			isFinishloadData = false;
			if(pageIndex == 0){
				showProgressbar();
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("shopId", "");
			params.put("flag", type);//0线下/1线上
//			params.put("pageIndex", pageIndex);
//			params.put("pageCount", Settings.pageCount);
			RoboHttpClient.post(HttpParameter.orderUrl,"getAllOrders", params, new TextHttpResponseHandler(){
				
				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					ToastUtil.diaplayMesLong(SellMachineTradeOrdersActivity.this, SellMachineTradeOrdersActivity.this.getResources().getString(R.string.connet_fail));
				}
				
				@Override
				public void onSuccess(int arg0, Header[] arg1, String result) {
					GetAllOrdersResponse mResponse = (GetAllOrdersResponse) ResultParse.parseResult(result,GetAllOrdersResponse.class);
					if(ResultParse.handleResutl(SellMachineTradeOrdersActivity.this, mResponse)){
						list.addAll(mResponse.getList());
						if(list.size() > 0){
							if(list.size() < Settings.pageCount && pageIndex == 0){
								isLoadMoreData = false;
								mListView.removeFooterView(footerView);
							}else{
								isLoadMoreData = true;
								footerView.setVisibility(View.VISIBLE);
								pageIndex++;
							}
						}else{
							showEmptyLayout_Empty();
							isLoadMoreData = false;
							load_more_data.setVisibility(View.GONE);
							no_more_data.setVisibility(View.VISIBLE);
						}
						mCashOrderListAdapter.notifyDataSetChanged();
					}else{
						mListView.removeFooterView(footerView);
					}
				}
				
				@Override
				public void onFinish() {
					isFinishloadData = true;
					onSwipeRefreshLayoutFinish();
					hideProgressbar();
				}
			});
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
