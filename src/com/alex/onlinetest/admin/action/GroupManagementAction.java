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

public class GroupManagementAction extends ActionSupport {
	
	//页面参数
	private Integer groupId;
	private String groupName;
	private int pageIndex;
	
	//页面参数Get，Set
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
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
	private GroupManagement gm;
	
	public void setGm(GroupManagement gm) {
		this.gm = gm;
	}


	//默认控制逻辑
	
	public String addGroup() throws Exception {
		UserGroup ug = new UserGroup();
		
		if (this.groupName != null && !this.groupName.trim().equals("")) {
			ug.setGroupName(this.groupName);
			
			//save
			if (gm.addUserGroup(ug)) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}
	
	public String delGroup() throws Exception {
		
		if (this.groupId != null) {
			if (gm.deleteById(this.groupId)) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}

	public String updateGroup() throws Exception {
		UserGroup ug = new UserGroup();
		
		ug = gm.getById(this.groupId);
		if (ug != null) {
			if (gm.updateUserGroupName(ug, this.groupName)) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}
	
	public String listGroup() throws Exception {
		
//		this.usergroup = gm.getAllUserGroup();
//		if (this.usergroup != null) {
//			ServletActionContext.getRequest().setAttribute("usergroup", this.usergroup);
//			return SUCCESS;
//		} else {
//			return ERROR;
//		}

		pi = gm.getResultByPage((pageIndex - 1) * Constant.PAGESIZE, Constant.PAGESIZE);
		if (pi != null) {
			ServletActionContext.getRequest().setAttribute("usergroup", pi);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	
}
