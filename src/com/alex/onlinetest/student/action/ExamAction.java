package com.alex.onlinetest.student.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.PaperManagement;
import com.alex.onlinetest.admin.service.QuestionManagement;
import com.alex.onlinetest.admin.service.TaskManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.hbm.Item;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.Schedule;
import com.alex.onlinetest.hbm.Task;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.student.action.model.ExamDeskModel;
import com.alex.onlinetest.util.Constant;
import com.alex.onlinetest.util.Tools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExamAction extends ActionSupport {
	
	//页面参数
	private Integer tid;	//task id
	private Integer qid;
	private int isfirst;
	private int islast;
	private String direction;
	
	private String openquestion;
	private String yesno;
	private String single;
	private List<String> multiple;
	//返回数据
	private String errmsg;
	
	//业务组件
	private TaskManagement tm;
	private UserManagement um;
	private QuestionManagement qm;
	private PaperManagement pm;
	
	private Task task;

	

	public void setPm(PaperManagement pm) {
		this.pm = pm;
	}

	public String getOpenquestion() {
		return openquestion;
	}

	public void setOpenquestion(String openquestion) {
		this.openquestion = openquestion;
	}

	public String getYesno() {
		return yesno;
	}

	public void setYesno(String yesno) {
		this.yesno = yesno;
	}

	public String getSingle() {
		return single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

	public List<String> getMultiple() {
		return multiple;
	}

	public void setMultiple(List<String> multiple) {
		this.multiple = multiple;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getIsfirst() {
		return isfirst;
	}

	public void setIsfirst(int isfirst) {
		this.isfirst = isfirst;
	}

	public int getIslast() {
		return islast;
	}

	public void setIslast(int islast) {
		this.islast = islast;
	}

	public void setQm(QuestionManagement qm) {
		this.qm = qm;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public void setTm(TaskManagement tm) {
		this.tm = tm;
	}

	public void setUm(UserManagement um) {
		this.um = um;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	//默认控制逻辑
	public String doExam() throws Exception {
		
		if (tid==null) {
			tid = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("tid").toString());
		}
		
		if (qid==null) {
			qid = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("qid").toString());
		}
		
		//获取任务实例
		if (task==null) {
			task = tm.getById(tid);
		}
		
		//根据qid获取Question实例
		Question question = qm.getById(qid);
		
		if (qid==1) {
			this.isfirst = 1;
		}
		
		if (qid==tm.getSortedQuestionId(task).size()) {
			this.islast = 1;
		}
		

		//返回JSP页面数据
		//返回该Task对应的题目总数及其QuestionId的List
		ServletActionContext.getRequest().setAttribute("qlist", tm.getSortedQuestionId(task));
		ServletActionContext.getRequest().setAttribute("ques", question);
		ServletActionContext.getRequest().setAttribute("qid", qid);
		ServletActionContext.getRequest().setAttribute("tid", tid);
		ServletActionContext.getRequest().setAttribute("isfirst", this.isfirst);
		ServletActionContext.getRequest().setAttribute("islast", this.islast);
		ServletActionContext.getRequest().setAttribute("answerofuser", tm.getAnswerOfUser(tid, qid));
		ServletActionContext.getRequest().setAttribute("availableseconds", tm.getAvailableSeconds(tid));
		
		
		return SUCCESS;
	}
	
	public String saveExam() throws Exception {
		
		//保存用户回答
		if (this.single!=null) {
			tm.updateTaskItem(tid, qid, single, 5);
		}
		if (this.yesno!=null) {
			tm.updateTaskItem(tid, qid, yesno.toUpperCase(), 5);
		}
		if (this.openquestion!=null) {
			tm.updateTaskItem(tid, qid, openquestion, 5);
		}
		if (this.multiple!=null) {
			tm.updateTaskItem(tid, qid, Tools.transferListStringToString(this.multiple), 5);
		}
		
		//更新动态时间
		tm.updateTaskDynamic(tid, new Date());
		
		//根据动作代码决定上还是下
		switch (this.direction)
		{
		case "next":
			ServletActionContext.getRequest().getSession().setAttribute("qid", qid+1);
			break;
		case "pre":
			ServletActionContext.getRequest().getSession().setAttribute("qid", qid-1);
			break;
		}

		ServletActionContext.getRequest().getSession().setAttribute("tid", tid);
		
		return SUCCESS;
	}
	
	public String submitExam() throws Exception {
		//保存用户回答
		if (this.single!=null) {
			tm.updateTaskItem(tid, qid, single, 5);
		}
		if (this.yesno!=null) {
			tm.updateTaskItem(tid, qid, yesno.toUpperCase(), 5);
		}
		if (this.openquestion!=null) {
			tm.updateTaskItem(tid, qid, openquestion, 5);
		}
		if (this.multiple!=null) {
			tm.updateTaskItem(tid, qid, Tools.transferListStringToString(this.multiple), 5);
		}
		
		//更新Task和试卷状态
		tm.updateTaskActualFinish(tid, new Date());
		tm.updateTaskStatus(tid, Constant.TASK_STATUS.DONE);
		tm.updatePercentage(tid);
		
		
		//如果试卷全部是客观题，则执行自动批卷
		if (pm.isAllObjective(tm.getById(tid).getSchedule().getPaper().getId())) {
			tm.reviewTask(tid);
		}
		
		return SUCCESS;
	}
	

}
