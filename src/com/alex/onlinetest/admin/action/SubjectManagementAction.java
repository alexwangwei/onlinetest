package com.alex.onlinetest.admin.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.GroupManagement;
import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.SubjectManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Subject;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;
import com.alex.onlinetest.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SubjectManagementAction extends ActionSupport {
	
	//页面参数
	private Integer subjectId;
	private String subjectName;
	
	//返回数据
	private String errmsg;
	private List<Subject> subjects;
	
	//业务组件
	private SubjectManagement sm;
	
	//页面参数Get，Set
	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	//返回数据的Get，Set
	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public void setSm(SubjectManagement sm) {
		this.sm = sm;
	}

	//默认控制逻辑
	
	public String addSubject() throws Exception {
		
		if (subjectName!=null && !subjectName.trim().equals("")) {
			Subject sub = new Subject();
			sub.setSubjectName(subjectName);
			
			//save
			if (!sm.addSubject(sub)) {
				return ERROR;
			} 
		}
		
		return SUCCESS;
	}
	
	public String delSubject() throws Exception {
		
		if (subjectId != null) {
			if (sm.deleteById(subjectId)) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}

	public String updateSubject() throws Exception {
		return SUCCESS;
	}
	
	public String listSubject() throws Exception {
		subjects = sm.getAllSubject();
		ServletActionContext.getRequest().setAttribute("subjects", subjects);
		return SUCCESS;
	}
	
	
}
