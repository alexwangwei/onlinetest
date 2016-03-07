package com.alex.onlinetest.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.GroupManagement;
import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.PaperManagement;
import com.alex.onlinetest.admin.service.QuestionManagement;
import com.alex.onlinetest.admin.service.SubjectManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.QuestionOption;
import com.alex.onlinetest.hbm.Subject;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;
import com.alex.onlinetest.util.Constant;
import com.alex.onlinetest.util.Tools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/admin")
@ParentPackage("struts-default")
public class NewPaperAction extends ActionSupport {
	
	private List<String> questionType;
	private List<String> questionLevel;
	private List<Subject> subject;
	private List<Question> questions;
	private String questionids;
	private String paperName;
	private Integer duration;
	
	private SubjectManagement sm;
	private PaperManagement pm;
	private QuestionManagement qm;
	
	

	public void setPm(PaperManagement pm) {
		this.pm = pm;
	}

	public void setQm(QuestionManagement qm) {
		this.qm = qm;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getQuestionids() {
		return questionids;
	}

	public void setQuestionids(String questionids) {
		this.questionids = questionids;
	}

	public void setSm(SubjectManagement sm) {
		this.sm = sm;
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

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}


	@Action(value="newpaper", className="newpaperAction", results={
			@Result(name="success", location="/Admin/manual_new_paper_ajax.jsp")
	})
	public String newpaper() throws Exception {
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
	
	@Action(value="savepaper", className="newpaperAction", results={
			@Result(name="success", type="redirect", location="listpaper")
	})
	public String savePaper() throws Exception {
		Paper paper = new Paper();
		
		paper.setPaperName(this.paperName);
		paper.setCreateDTime(new Date());
		paper.setCreator(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
		paper.setStatus(Constant.QUESTION_STATUS.DRAFT);
		paper.setDuration(Tools.transferMin2Sec(this.duration));
		Map<Integer, Question> questions = new HashMap<Integer, Question>();
		List<Integer> qids = Tools.splitQuestionID(this.questionids);
		for (int i=0; i<qids.size(); i++){
			questions.put(i+1, this.qm.getById(qids.get(i)));
		}
		paper.setQuestions(questions);
		
		pm.addPaper(paper);
		return SUCCESS;
	}
	
}
