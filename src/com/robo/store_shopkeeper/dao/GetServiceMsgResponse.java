package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetServiceMsgResponse extends CommonResponse {
	private List<GetServiceMsgVo> list;

	public List<GetServiceMsgVo> getList() {
		return list;
	}

	public void setList(List<GetServiceMsgVo> list) {
		this.list = list;
	}
}
