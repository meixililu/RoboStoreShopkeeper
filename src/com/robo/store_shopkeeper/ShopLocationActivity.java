package com.robo.store_shopkeeper;

import android.content.Intent;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.robo.store_shopkeeper.util.KeyUtil;
import com.robo.store_shopkeeper.util.LogUtil;
import com.robo.store_shopkeeper.util.NumberUtil;

public class ShopLocationActivity extends BaseActivity {

	//15:A8:73:78:D0:EF:81:C7:68:2D:0A:91:D9:AD:AF:80:1C:F1:09:60;com.robo.store_shopkeeper debuy
	//14:D6:DF:35:58:68:E5:E6:02:14:0D:EB:2B:67:16:79:0D:82:E1:05;com.robo.store_shopkeeper formal
	public double latitude,longitude;
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_location);
		init();
	}
	
	private void init(){
		Bundle mBundle = getIntent().getBundleExtra(KeyUtil.BundleKey);
		if(mBundle != null){
			String mLatitude = mBundle.getString(KeyUtil.LatitudeKey);
			String mLongitude = mBundle.getString(KeyUtil.LongitudeKey);
			LogUtil.DefalutLog(mLatitude+","+mLongitude);
			latitude = NumberUtil.StringToDouble(mLatitude);
			longitude = NumberUtil.StringToDouble(mLongitude);
		}
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); 
		LatLng point = new LatLng(latitude, longitude); 
		//构建Marker图标  
		BitmapDescriptor bitmap = BitmapDescriptorFactory  
		    .fromResource(R.drawable.icon_poi);  
		
		OverlayOptions options = new MarkerOptions()
	    .position(point)  //设置marker的位置
	    .icon(bitmap)  //设置marker图标
	    .zIndex(9)  //设置marker所在层级
	    .draggable(true);  //设置手势拖拽
		mBaiduMap.addOverlay(options);
		
		MapStatus mMapStatus = new MapStatus.Builder()
        .target(point)
        .zoom(18)
        .build();
		MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
		mBaiduMap.animateMapStatus(mapStatusUpdate);
		//将marker添加到地图上
		mBaiduMap.setOnMarkerDragListener(new OnMarkerDragListener() {
		    public void onMarkerDrag(Marker marker) {
		        //拖拽中
		    }
		    public void onMarkerDragEnd(Marker marker) {
		    	Intent intent = new Intent();
				intent.putExtra(KeyUtil.LatitudeKey, marker.getPosition().latitude + "");
				intent.putExtra(KeyUtil.LongitudeKey, marker.getPosition().longitude + "");
				setResult(RESULT_OK, intent);
		    }
		    public void onMarkerDragStart(Marker marker) {
		        //开始拖拽
		    }
		});
	}
	
    @Override  
    protected void onResume() {  
        super.onResume();  
        mMapView.onResume();  
    }
    
    @Override  
    protected void onPause() {  
        super.onPause();  
        mMapView.onPause();  
    }
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();  
	}
	
}
