package com.alex.onlinetest.admin.action;

import java.util.ArrayList;
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

public class UserAndGroupManagementAction extends ActionSupport {
	
	//页面参数
	private Integer gid;	//用户选择的Group的ID
	private String groupName;	//用户选择的GroupName
	private List<String> selectedUser;	//用户选择了哪些用户，存放的为user的loginId
	
	//返回数据
	private String errmsg;
	private List<UserGroup> usergroup;
	private List<User> userlist;

	//业务组件
	private UserManagement um;
	private GroupManagement gm;
	
	//页面参数Get，Set
	public String getGroupName() {
		return groupName;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<String> getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(List<String> selectedUser) {
		this.selectedUser = selectedUser;
	}

	
	//返回数据的Get，Set
	
	public String getErrmsg() {
		return errmsg;
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

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
	
	public void setUm(UserManagement um) {
		this.um = um;
	}

	public void setGm(GroupManagement gm) {
		this.gm = gm;
	}
	

	//控制逻辑
	/**
	 * 根据selectedUser执行更新逻辑，selectedUser==null则表明需要全部清除
	 * @return
	 * @throws Exception
	 */
	public String updateUserGroup() throws Exception {
		
		if (gid==null) return ERROR;	//没有对应的组信息，无法确认需要更新哪一个用户组
		UserGroup ug = gm.getById(gid);	//根据用户选择的gid实例化UserGroup
		
		if (selectedUser == null) {
			um.updateUserAndGroupMapping(ug, null);
		} else {
			List<User> updateUserList = new ArrayList<User>();	//需要更新的用户列表，从selectedUser中实例化
			for (int i=0; i<selectedUser.size(); i++) {
				updateUserList.add(um.getUserByLoginId(selectedUser.get(i)));
			}
			um.updateUserAndGroupMapping(ug, updateUserList);
		}
		
		usergroup = null;
		userlist = null;
		ServletActionContext.getRequest().getSession().setAttribute("selectedgpid", gid);
		
		return SUCCESS;
	}
	
	public String listUserGroup() throws Exception {
		
		if (usergroup==null) {
			usergroup = gm.getAllUserGroup();	//获取组列表
		}
		
		if (userlist==null) {
			userlist = um.getAll();	//获取用户列表
		}
		
		//Action返回数据到JSP
		ServletActionContext.getRequest().setAttribute("grouplist", usergroup);
		ServletActionContext.getRequest().setAttribute("userlist", userlist);
		if (gid != null) {
			ServletActionContext.getRequest().getSession().setAttribute("selectedgpid", gid);
		}
		
		
		return SUCCESS;
	}
	
	
}
