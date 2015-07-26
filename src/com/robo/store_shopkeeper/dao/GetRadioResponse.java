package com.robo.store_shopkeeper.dao;

import java.util.List;

public class GetRadioResponse extends CommonResponse {
	private List<GetRadioVo> list;

	public List<GetRadioVo> getList() {
		return list;
	}

	public void setList(List<GetRadioVo> list) {
		this.list = list;
	}
}
