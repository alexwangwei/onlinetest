package com.alex.onlinetest.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.GroupManagement;
import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.QuestionManagement;
import com.alex.onlinetest.admin.service.ResultReportManagement;
import com.alex.onlinetest.admin.service.ScheduleManagement;
import com.alex.onlinetest.admin.service.SubjectManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.QuestionOption;
import com.alex.onlinetest.hbm.Schedule;
import com.alex.onlinetest.hbm.Subject;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;
import com.alex.onlinetest.util.Constant;
import com.alex.onlinetest.util.Tools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ResultViewAction extends ActionSupport {
	
	//页面参数
	private Integer scheduleid;

	//返回数据

	
	//业务组件
	private ResultReportManagement rrm;
	private ScheduleManagement schm;
	

	public Integer getScheduleid() {
		return scheduleid;
	}


	public void setScheduleid(Integer scheduleid) {
		this.scheduleid = scheduleid;
	}


	public void setRrm(ResultReportManagement rrm) {
		this.rrm = rrm;
	}

	public void setSchm(ScheduleManagement schm) {
		this.schm = schm;
	}

	//默认控制逻辑




	public String list() throws Exception {
		
		//返回数据
		//1.排期选择
		//2.对应的结果
		//3.schedule选项
		
		List<Schedule> schedule_list = schm.getAllSchedule();
		
		
		ServletActionContext.getRequest().setAttribute("schedulelist", schedule_list);
		ServletActionContext.getRequest().setAttribute("selectsid", this.scheduleid);
		if (this.scheduleid == null){
			ServletActionContext.getRequest().setAttribute("rrlist", rrm.getAllResultReport());
		} else {
			ServletActionContext.getRequest().setAttribute("rrlist", rrm.getAllResultReportBySchedule(schm.getById(this.scheduleid)));
		}
		
		
		
		return SUCCESS;
	}
	

	
	
}
