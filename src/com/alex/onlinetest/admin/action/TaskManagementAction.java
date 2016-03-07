package com.alex.onlinetest.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.GroupManagement;
import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.PaperManagement;
import com.alex.onlinetest.admin.service.QuestionManagement;
import com.alex.onlinetest.admin.service.ScheduleManagement;
import com.alex.onlinetest.admin.service.SubjectManagement;
import com.alex.onlinetest.admin.service.TaskManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Item;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.QuestionOption;
import com.alex.onlinetest.hbm.Schedule;
import com.alex.onlinetest.hbm.Subject;
import com.alex.onlinetest.hbm.Task;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;
import com.alex.onlinetest.util.Constant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TaskManagementAction extends ActionSupport {

	// 页面参数
	private Integer scheduleId;

	// 返回数据
	private String errmsg;

	// 业务组件
	private QuestionManagement qm;
	private ScheduleManagement schm;
	private TaskManagement tm;
	private GroupManagement gm;
	private PaperManagement pm;

	// 页面参数Get，Set
	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	// 返回数据的Get，Set

	public String getErrmsg() {
		return errmsg;
	}

	public void setTm(TaskManagement tm) {
		this.tm = tm;
	}

	public QuestionManagement getQm() {
		return qm;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public void setQm(QuestionManagement qm) {
		this.qm = qm;
	}

	public void setSchm(ScheduleManagement schm) {
		this.schm = schm;
	}

	public void setGm(GroupManagement gm) {
		this.gm = gm;
	}

	public void setPm(PaperManagement pm) {
		this.pm = pm;
	}

	// 默认控制逻辑

	public String publish() throws Exception {

		Schedule schedule = schm.getById(this.scheduleId); // 获取排期

		Set<UserGroup> ugl = schedule.getUsergroup();

		List<User> users = gm.getAllDistinctUsersInGroup(ugl);

		for (int n = 0; n < users.size(); n++) {
			// 逐个用户执行发布任务逻辑
			Task task = new Task();

			task.setOwner(users.get(n).getLoginId());
			task.setPercentage(0);
			task.setStatus(Constant.TASK_STATUS.NEW);
			task.setTaskName(schedule.getPaper().getPaperName());

			// 试卷
			Paper paper = schedule.getPaper();
			Map<Integer,Question> questionsmap = paper.getQuestions();
			// Items
			Set<Item> items = new HashSet<Item>();
			//遍历Map中的值
			for (Question value:questionsmap.values()) {
				Item item = new Item();
				item.setQuestion(value);
				item.setTask(task);
				items.add(item);
			}
			
//			for (int m = 0; m < questions.size(); m++) {
//				Item item = new Item();
//
//				item.setQuestion(questions.get(m));
//				item.setTask(task);
//
//				items.add(item);
//			}
			task.setItems(items);

			task.setSchedule(schedule);

			tm.addTask(task);
		}
		
		//跟新Schedule状态为已发布
		schedule.setStatus(Constant.SCHEDULE_STATUS.PUBLISHED);
		schm.updateScheduleStatus(schedule);

		return SUCCESS;
	}
}
