package com.alex.onlinetest.dao.impl;

import java.util.List;

public class PageInfo {
	
	//总记录数
	private int totalNum;
	//总页数
	private int pageNum;
	//当前页数
	private int current = 1;
	//每页记录数
	private int limit = 25;
	//分页记录信息
	private List result;
	//开始记录索引号
	private int start;
	private int type;
	//是否是第一页
	private boolean first;
	//是否是最后一页
	private boolean last;
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
		this.getPageNum();
	}
	public int getPageNum() {
		if (totalNum <=0) {
			this.pageNum = 1;
		} else {
			this.pageNum = this.totalNum % this.limit == 0 ? this.totalNum/this.limit:this.totalNum/this.limit+1;
		}
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getCurrent() {
		if (this.totalNum == 0) {
			return 1;
		} else if (this.current <= 1) {
			return 1;
		} else if (this.current >= this.pageNum) {
			return this.pageNum;
		}
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List getResult() {
		return result;
	}
	public void setResult(List result) {
		this.result = result;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public boolean isFirst() {
		return this.getCurrent()==1;
	}
	
	public boolean isLast() {
		return this.getCurrent()>=this.getPageNum();
	}
	
	public PageInfo() {
	}
	
	public PageInfo(List result, int totalNum, int limit) {
		this.result = result;
		this.totalNum = totalNum;
		this.limit = limit;
	}
	
	public int getCurrentPageIndex() {
		return (this.getCurrent()-1)*this.getLimit();
	}
	

}
