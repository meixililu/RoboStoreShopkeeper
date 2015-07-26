package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetShopStorageListResponse extends CommonResponse {
	private List<GetShopStorageListVo> list;

	public List<GetShopStorageListVo> getList() {
		return list;
	}

	public void setList(List<GetShopStorageListVo> list) {
		this.list = list;
	}
}
