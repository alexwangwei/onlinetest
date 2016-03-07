package com.alex.onlinetest.student.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.TaskManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.hbm.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TaskAction extends ActionSupport {
	
	//页面参数
	private String userid;
	private Integer tid;
	//返回数据
	private String errmsg;
	
	//业务组件
	private TaskManagement tm;
	private UserManagement um;


	public void setTm(TaskManagement tm) {
		this.tm = tm;
	}

	public void setUm(UserManagement um) {
		this.um = um;
	}

	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	//默认控制逻辑
	public String listTask() throws Exception {
		
		if (userid==null) {
			userid = ServletActionContext.getRequest().getSession().getAttribute("userid").toString();
		}
		
		User user = um.getUserByLoginId(userid);
		ServletActionContext.getRequest().setAttribute("tlist", tm.getAllActiveTaskByUser(user));
		
		return SUCCESS;
	}
	
	public String display() throws Exception {
		
		tm.updateTaskActualStart(tid, new Date());
		ServletActionContext.getRequest().setAttribute("tid", this.tid);
		
		return SUCCESS;
	}

}
