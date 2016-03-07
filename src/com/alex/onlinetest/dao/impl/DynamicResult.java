package com.alex.onlinetest.dao.impl;

import java.util.List;

public class DynamicResult {
	
	private int recordCount;
	
	private List result;

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public DynamicResult() {
		super();
	}

	public DynamicResult(int recordCount, List result) {
		super();
		this.recordCount = recordCount;
		this.result = result;
	}
	
	

}
