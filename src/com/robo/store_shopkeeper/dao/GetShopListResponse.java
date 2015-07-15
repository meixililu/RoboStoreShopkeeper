package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetShopListResponse extends CommonResponse{
	private List<ShopBase> list;

	public List<ShopBase> getList() {
		return list;
	}

	public void setList(List<ShopBase> list) {
		this.list = list;
	}



}
