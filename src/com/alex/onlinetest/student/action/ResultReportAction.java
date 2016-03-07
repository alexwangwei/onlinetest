package com.alex.onlinetest.student.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.ResultReportManagement;
import com.alex.onlinetest.admin.service.TaskManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.hbm.ResultReport;
import com.alex.onlinetest.hbm.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ResultReportAction extends ActionSupport {
	
	//页面参数
	private String userid;
	private Integer tid;
	//返回数据
	private String errmsg;
	
	//业务组件
	private ResultReportManagement rrm;
	private UserManagement um;




	public void setUm(UserManagement um) {
		this.um = um;
	}


	public void setRrm(ResultReportManagement rrm) {
		this.rrm = rrm;
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
	public String resultList() throws Exception {
		
		User user = um.getUserByLoginId(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
		List<ResultReport> rrs = rrm.getAllResultReportByUser(user);
		
		ServletActionContext.getRequest().setAttribute("rrs", rrs);
		
		return SUCCESS;
	}
	

}
