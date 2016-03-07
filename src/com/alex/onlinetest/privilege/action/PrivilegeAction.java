package com.alex.onlinetest.privilege.action;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.hbm.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PrivilegeAction extends ActionSupport {

	private User user;
	private boolean login;
	// 页面参数
	private String userid;
	private String password;
	// 返回数据
	private String errmsg;

	// 业务组件
	private UserManagement um;

	public void setUm(UserManagement um) {
		this.um = um;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	// 默认控制逻辑
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	// 用户登录验证
	public String login() throws Exception {

		if (userid == null || password == null) {
			// this.errmsg = "用户名和密码不能为null！";
			this.errmsg = null;
			return INPUT;
		} else if (userid.equals("") || password.equals("")) {
			this.errmsg = "用户名和密码不能为空！";
			return INPUT;
		} else {
			// 调用业务逻辑判断用户身份
			if (user == null) {
				user = new User();
			}
			user.setLoginId(userid);
			user.setPassword(password);
			if (um.validUser(user)) {
				// 更新Session
				ActionContext.getContext().getSession()
						.put("userid", user.getLoginId());
				ActionContext
						.getContext()
						.getSession()
						.put("username",
								um.getUserByLoginId(user.getLoginId())
										.getDisplayName());
				return SUCCESS;
			} else {
				this.errmsg = "用户名或密码不正确！";
				return INPUT;
			}

		}
	}

	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("userid");
		ActionContext.getContext().getSession().remove("username");
		return SUCCESS;
	}

}
