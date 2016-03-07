package com.alex.onlinetest.admin.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.GroupManagement;
import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.PaperManagement;
import com.alex.onlinetest.admin.service.QuestionManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;
import com.alex.onlinetest.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PaperManagementAction extends ActionSupport {
	
	//页面参数
	private Integer questionId;
	private String groupName;
	private int pageIndex;
	private int paperId;
	
	//返回数据
	private String errmsg;
	private List<Question> questionList;
	private PageInfo pi;
	
	//业务组件
	private PaperManagement pm;
	
	//页面参数Get，Set


	public String getGroupName() {
		return groupName;
	}

	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
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

	public void setPm(PaperManagement pm) {
		this.pm = pm;
	}

	//默认控制逻辑
	public String listPaper() throws Exception {
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		pi = pm.getResultByPage((pageIndex - 1) * Constant.PAGESIZE, Constant.PAGESIZE);
		if (pi != null) {
			ServletActionContext.getRequest().setAttribute("plist", pi);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	public String deletePaper() throws Exception {
		
		this.pm.deleteById(this.paperId);
		return SUCCESS;
	}
	
	
}
