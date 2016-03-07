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
import com.alex.onlinetest.admin.service.SubjectManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.QuestionOption;
import com.alex.onlinetest.hbm.Subject;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;
import com.alex.onlinetest.util.Constant;
import com.alex.onlinetest.util.Tools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class NewQuestionAction extends ActionSupport {
	
	//页面参数
	private Question question;	//data model
	private String questiontype;
	private String questionlevel;
	private Integer subjectid;
	private Integer optioncount;

	//返回数据
	private String errmsg;
	private List<String> questionType;
	private List<String> questionLevel;
	private List<Subject> subject;
	
	//业务组件
	private QuestionManagement qm;
	private SubjectManagement sm;
	
	//页面参数Get，Set
	public Question getQuestion() {
		return question;
	}

	public Integer getOptioncount() {
		return optioncount;
	}

	public void setOptioncount(Integer optioncount) {
		this.optioncount = optioncount;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public String getQuestiontype() {
		return questiontype;
	}

	public void setQuestiontype(String questiontype) {
		this.questiontype = questiontype;
	}
	
	public String getQuestionlevel() {
		return questionlevel;
	}

	public void setQuestionlevel(String questionlevel) {
		this.questionlevel = questionlevel;
	}
	public Integer getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(Integer subjectid) {
		this.subjectid = subjectid;
	}
	
	//返回数据的Get，Set

	public String getErrmsg() {
		return errmsg;
	}

	public List<String> getQuestionType() {
		return questionType;
	}

	public void setQuestionType(List<String> questionType) {
		this.questionType = questionType;
	}

	public List<String> getQuestionLevel() {
		return questionLevel;
	}

	public void setQuestionLevel(List<String> questionLevel) {
		this.questionLevel = questionLevel;
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
	
	public void setSm(SubjectManagement sm) {
		this.sm = sm;
	}

	//默认控制逻辑

	public String newQuestion() throws Exception {
		
		if (this.questionType == null){
			this.questionType = new ArrayList<String>();
			this.questionType.add("单选题");
			this.questionType.add("多选题");
			this.questionType.add("判断题");
			this.questionType.add("问答题");
		}
		
		if (this.questionLevel == null) {
			this.questionLevel = new ArrayList<String>();
			this.questionLevel.add("容易");
			this.questionLevel.add("一般");
			this.questionLevel.add("困难");
		}
		
		if (this.subject == null) {
			this.subject = sm.getAllSubject();
		}
		
		ServletActionContext.getRequest().setAttribute("qType", questionType);
		ServletActionContext.getRequest().setAttribute("qLevel", questionLevel);
		ServletActionContext.getRequest().setAttribute("subject", subject);
		
		return SUCCESS;
	}
	
	public String saveQuestion() throws Exception {
				
		question.setStatus(Constant.QUESTION_STATUS.DRAFT);
		question.setCreateDTime(new Date());
		question.setCreator(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
		switch (questiontype)
		{
		case "单选题":
			question.setQuestionType(Constant.QUESTION_TYPE.SINGLE);
			break;
		case "多选题":
			question.setQuestionType(Constant.QUESTION_TYPE.MUL);
			break;
		case "判断题":
			question.setQuestionType(Constant.QUESTION_TYPE.YESNO);
			break;
		case "问答题":
			question.setQuestionType(Constant.QUESTION_TYPE.OPENQUESTION);
			break;
		}
		switch (questionlevel)
		{
		case "容易":
			question.setQuestionLevel(Constant.QUESTION_LEAVEL.EASY);
			break;
		case "一般":
			question.setQuestionLevel(Constant.QUESTION_LEAVEL.MIDDLE);
			break;
		case "困难":
			question.setQuestionLevel(Constant.QUESTION_LEAVEL.DIFFICULT);
			break;
		}
		question.setSubject(sm.getById(subjectid));
		Set<QuestionOption> opl = getOptions();
		question.setOptions(opl);
		
		//转换question的duration时间
		question.setDuration(Tools.transferMin2Sec(question.getDuration()));
		
		this.qm.addQuestion(question);
		
		return SUCCESS;
	}
	
	private Set<QuestionOption> getOptions() {
		if (this.optioncount==null) return null;
		if (this.optioncount<=0) return null;
		
		Set<QuestionOption> options = new HashSet<QuestionOption>();
		for (int i=1; i<=this.optioncount; i++) {
			QuestionOption op = new QuestionOption();
			op.setOptionDescription(ServletActionContext.getRequest().getParameter("option"+i));
			op.setQuestion(this.question);
			op.setSequence(i);
			options.add(op);
		}
		return options;

	}
	
	
}
