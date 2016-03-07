package com.alex.onlinetest.admin.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.GroupManagement;
import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.QuestionManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;
import com.alex.onlinetest.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QuestionManagementAction extends ActionSupport {
	
	//页面参数
	private Integer questionId;
	private String groupName;
	private int pageIndex;
	
	//返回数据
	private String errmsg;
	private List<Question> questionList;
	private PageInfo pi;
	
	//业务组件
	private QuestionManagement qm;
	
	//页面参数Get，Set


	public String getGroupName() {
		return groupName;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
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

	
	public void setQm(QuestionManagement qm) {
		this.qm = qm;
	}

	//默认控制逻辑
	public String listQuestion() throws Exception {
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		pi = qm.getResultByPage((pageIndex - 1) * Constant.PAGESIZE, Constant.PAGESIZE);
		if (pi != null) {
			ServletActionContext.getRequest().setAttribute("qlist", pi);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	public String delQuestion() throws Exception {
		
		if (this.questionId != null) {
			if (qm.deleteById(this.questionId)) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return ERROR;
		}
		
	}
	
	public String newQuestion() throws Exception {
		return SUCCESS;
	}
	
	
}
