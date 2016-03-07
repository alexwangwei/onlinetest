package com.alex.onlinetest.admin.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.GroupManagement;
import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.ScheduleManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;
import com.alex.onlinetest.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ScheduleListAction extends ActionSupport {
	
	//页面参数
	private int pageIndex;
	private int scheduleId;
	
	//返回数据
	private String errmsg;
	private PageInfo pi;
	
	//业务组件
	private ScheduleManagement schm;
	
	//页面参数Get，Set
	public int getPageIndex() {
		return pageIndex;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	//返回数据的Get，Set
	
	
	public String getErrmsg() {
		return errmsg;
	}

	public PageInfo getPi() {
		return pi;
	}

	public void setPi(PageInfo pi) {
		this.pi = pi;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public void setSchm(ScheduleManagement schm) {
		this.schm = schm;
	}

	//默认控制逻辑
	public String listSchedule() throws Exception {
		
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		pi = schm.getResultByPage((pageIndex - 1) * Constant.PAGESIZE, Constant.PAGESIZE);
		if (pi != null) {
			ServletActionContext.getRequest().setAttribute("schlist", pi);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	public String delSchedule() throws Exception {
		this.schm.deleteById(this.scheduleId);
		return SUCCESS;
	}
	
	
	
}
