package com.robo.store_shopkeeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.robo.store_shopkeeper.adapter.StockListAdapter;
import com.robo.store_shopkeeper.dao.GetShopStorageListResponse;
import com.robo.store_shopkeeper.dao.GetShopStorageListVo;
import com.robo.store_shopkeeper.http.HttpParameter;
import com.robo.store_shopkeeper.http.RoboHttpClient;
import com.robo.store_shopkeeper.http.TextHttpResponseHandler;
import com.robo.store_shopkeeper.util.LogUtil;
import com.robo.store_shopkeeper.util.ResultParse;
import com.robo.store_shopkeeper.util.Settings;
import com.robo.store_shopkeeper.util.ToastUtil;

public class StockListActivity extends BaseActivity implements OnClickListener{

	private LayoutInflater inflater;
	private ListView mListView;
	private StockListAdapter mRukuHistoryListAdapter;
	private List<GetShopStorageListVo> list;
	
//	private View footerView;
//	private LinearLayout load_more_data;
//	private TextView no_more_data;
	public int pageIndex = 0;
	private boolean isLoadMoreData;
	private boolean isFinishloadData = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_list);
		init();
		RequestData();
	}
	
	private void init(){
		inflater = LayoutInflater.from(this);
		list = new ArrayList<GetShopStorageListVo>();
		mRukuHistoryListAdapter = new StockListAdapter(this, inflater, list);
		initSwipeRefresh();
		mListView = (ListView) findViewById(R.id.content_lv);
		
//		footerView = inflater.inflate(R.layout.list_footer_view, null);
//		load_more_data = (LinearLayout) footerView.findViewById(R.id.load_more_data);
//		no_more_data = (TextView) footerView.findViewById(R.id.no_more_data);
//		footerView.setVisibility(View.GONE);
//		mListView.addFooterView(footerView);
//		setListOnScrollListener();
		
		mListView.setAdapter(mRukuHistoryListAdapter);
	}
	
	public void setListOnScrollListener(){
		mListView.setOnScrollListener(new OnScrollListener() {  
            private int lastItemIndex;
            @Override  
            public void onScrollStateChanged(AbsListView view, int scrollState) { 
                if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && lastItemIndex == mRukuHistoryListAdapter.getCount() - 1) {  
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
//		footerView.setVisibility(View.GONE);
		mRukuHistoryListAdapter.notifyDataSetChanged();
//		load_more_data.setVisibility(View.VISIBLE);
//		no_more_data.setVisibility(View.GONE);
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
			RoboHttpClient.post(HttpParameter.goodUrl,"getShopStorageList", params, new TextHttpResponseHandler(){
				
				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					ToastUtil.diaplayMesLong(StockListActivity.this, StockListActivity.this.getResources().getString(R.string.connet_fail));
				}
				
				@Override
				public void onSuccess(int arg0, Header[] arg1, String result) {
					GetShopStorageListResponse mResponse = (GetShopStorageListResponse) ResultParse.parseResult(result,GetShopStorageListResponse.class);
					if(ResultParse.handleResutl(StockListActivity.this, mResponse)){
						list.addAll(mResponse.getList());
						if(list.size() > 0){
							if(list.size() < Settings.pageCount && pageIndex == 0){
//								isLoadMoreData = false;
//								mListView.removeFooterView(footerView);
							}else{
//								isLoadMoreData = true;
//								footerView.setVisibility(View.VISIBLE);
//								pageIndex++;
							}
						}else{
							showEmptyLayout_Empty();
//							isLoadMoreData = false;
//							load_more_data.setVisibility(View.GONE);
//							no_more_data.setVisibility(View.VISIBLE);
						}
						mRukuHistoryListAdapter.notifyDataSetChanged();
					}else{
//						mListView.removeFooterView(footerView);
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
