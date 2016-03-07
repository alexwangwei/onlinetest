package com.alex.onlinetest.admin.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.action.model.ScheduleModel;
import com.alex.onlinetest.admin.service.GroupManagement;
import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.PaperManagement;
import com.alex.onlinetest.admin.service.ScheduleManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Schedule;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;
import com.alex.onlinetest.util.Constant;
import com.alex.onlinetest.util.Tools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class NewScheduleAction extends ActionSupport {
	
	//页面参数
	private ScheduleModel userInput;
	
	//返回数据
	
	//业务组件
	private ScheduleManagement schm;
	private PaperManagement pm;
	private GroupManagement gm;
	
	

	public void setGm(GroupManagement gm) {
		this.gm = gm;
	}

	public void setPm(PaperManagement pm) {
		this.pm = pm;
	}

	public void setSchm(ScheduleManagement schm) {
		this.schm = schm;
	}

	public ScheduleModel getUserInput() {
		return userInput;
	}

	public void setUserInput(ScheduleModel userInput) {
		this.userInput = userInput;
	}

	//默认控制逻辑
	public String save() throws Exception {
		
		Schedule schedule = new Schedule();
		//根据系统时间生成ScheduleName
		schedule.setName(Tools.getCurrentDateTime());
		schedule.setDuration(Tools.transferMin2Sec(userInput.getDuration()));
		schedule.setEffectiveDate(Tools.formatStringToDate(this.userInput.getEffectiveDate()));
		schedule.setDisableDate(Tools.formatStringToDate(this.userInput.getDisableDate()));
		
		Set<UserGroup> ugl = new HashSet<UserGroup>();
		for (int i=0; i<this.userInput.getUsergroup().size(); i++) {
			ugl.add(this.gm.getById(Integer.parseInt(this.userInput.getUsergroup().get(i))));
		}
		schedule.setUsergroup(ugl);
		schedule.setPaper(this.pm.getById(this.userInput.getPaperId()));
		schedule.setStatus(Constant.SCHEDULE_STATUS.ACTIVE);
		
		schm.addSchedule(schedule);
		
		return SUCCESS;

	}
	
	public String input() throws Exception {
		
		List<Paper> lpaper = pm.getAllPaper();
		List<UserGroup> uglist = gm.getAllUserGroup();
		
		ServletActionContext.getRequest().setAttribute("paperlist", lpaper);
		ServletActionContext.getRequest().setAttribute("usergrouplist", uglist);
		
		return SUCCESS;
	}
	
	
	
}
