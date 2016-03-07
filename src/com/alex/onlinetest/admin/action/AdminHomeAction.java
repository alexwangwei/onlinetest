package com.alex.onlinetest.admin.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.hbm.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminHomeAction extends ActionSupport {
	
	//页面参数
	//返回数据
	private String errmsg;
	
	//业务组件
	private NavigatorManagement nav;
	

	public void setNav(NavigatorManagement nav) {
		this.nav = nav;
	}


	//默认控制逻辑
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	

}
