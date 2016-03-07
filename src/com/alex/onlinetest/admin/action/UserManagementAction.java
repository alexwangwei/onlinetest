package com.alex.onlinetest.admin.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.GroupManagement;
import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;
import com.alex.onlinetest.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserManagementAction extends ActionSupport {
	
	//页面参数
	private Integer id;
	private String loginId;
	private String userName;
	private String password;
	private int pageIndex;
	
	//页面参数Get，Set
	public int getPageIndex() {
		return pageIndex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}



	//返回数据
	private String errmsg;
	private List<UserGroup> usergroup;
	private PageInfo pi;
	
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

	public List<UserGroup> getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(List<UserGroup> usergroup) {
		this.usergroup = usergroup;
	}

	//业务组件
	private UserManagement um;
	
	public void setUm(UserManagement um) {
		this.um = um;
	}


	//默认控制逻辑

	public String addUser() throws Exception {
		
		if (this.loginId != null && !this.loginId.equals("") && this.userName != null && !this.userName.equals("") && this.password != null && !this.password.equals("")) {
			User usr = new User();
			usr.setLoginId(loginId);
			usr.setDisplayName(userName);
			usr.setPassword(password);
			if (um.addUser(usr)) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		}
		return null;
	}
	
	public String delUser() throws Exception {
		
		if (id != null) {
			if (um.delUserById(id)) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}

	public String updateUser() throws Exception {
		return null;
	}
	
	public String listUser() throws Exception {
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		pi = um.getResultByPage((pageIndex - 1) * Constant.PAGESIZE, Constant.PAGESIZE);
		if (pi != null) {
			ServletActionContext.getRequest().setAttribute("userlist", pi);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	
}
