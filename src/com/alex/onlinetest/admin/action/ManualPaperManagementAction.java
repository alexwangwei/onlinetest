package com.alex.onlinetest.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.action.model.ManualNewPaperQuery;
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

public class ManualPaperManagementAction extends ActionSupport {
	
	//页面参数
	private Paper paper;
	private ManualNewPaperQuery query;
	private Integer durationmin;
	private List<String> questionaddtopaper;
	
	//返回数据
	private String errmsg;
	private List<String> questionType;
	private List<String> questionLevel;
	private List<Subject> subject;
	private List<Question> questions;
	
	//业务组件
	private PaperManagement pm;
	private SubjectManagement sm;
	private QuestionManagement qm;
	
	//页面参数Get，Set
	
	public Paper getPaper() {
		return paper;
	}


	public List<String> getQuestionaddtopaper() {
		return questionaddtopaper;
	}


	public void setQuestionaddtopaper(List<String> questionaddtopaper) {
		this.questionaddtopaper = questionaddtopaper;
	}


	public void setQm(QuestionManagement qm) {
		this.qm = qm;
	}


	public List<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}


	public Integer getDurationmin() {
		return durationmin;
	}


	public void setDurationmin(Integer durationmin) {
		this.durationmin = durationmin;
	}


	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	
	public ManualNewPaperQuery getQuery() {
		return query;
	}

	public void setQuery(ManualNewPaperQuery query) {
		this.query = query;
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

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public void setPm(PaperManagement pm) {
		this.pm = pm;
	}

	public void setSm(SubjectManagement sm) {
		this.sm = sm;
	}

	//默认控制逻辑
	public String newPaper() throws Exception {
		
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
		
		this.paper = new Paper();
		
		ServletActionContext.getRequest().setAttribute("qType", questionType);
		ServletActionContext.getRequest().setAttribute("qLevel", questionLevel);
		ServletActionContext.getRequest().setAttribute("subject", subject);
		
		return SUCCESS;
	}
	
	public String query() throws Exception {
		
		//根据用户提交的过滤条件，筛选可供选择的问题列表
		//this.questions = this.qm.getAllQuestion();
		HashMap<String, String>filter = new HashMap<String, String>();
		if (query.getQuestionLevel()!=null) {
			filter.put("questionLevel", query.getQuestionLevel());
		}
		if (query.getQuestionType()!=null) {
			filter.put("questionType", query.getQuestionType());
		}
		if (query.getSubjectName()!=null) {
			filter.put("subject.id", query.getSubjectName());
		}
		//this.query.getQuestionValue();
		
		//this.questions = this.qm.getAllQuestionByAndFilter(filter);
		
		//在对象层实现过滤
		this.questions = this.qm.getAllQuestion();
		for (int i=0; i<questions.size(); i++) {
			if (query.getQuestionLevel()!=null) {
				String level = query.getQuestionLevel();
				switch(level){
				case "容易":
					if (questions.get(i).getQuestionLevel()!=Constant.QUESTION_LEAVEL.EASY) {
						questions.remove(i);
					}
					break;
				case "一般":
					if (questions.get(i).getQuestionLevel()!=Constant.QUESTION_LEAVEL.MIDDLE) {
						questions.remove(i);
					}
					break;
				case "困难":
					if (questions.get(i).getQuestionLevel()!=Constant.QUESTION_LEAVEL.DIFFICULT) {
						questions.remove(i);
					}
					break;
				}

			}
			if (query.getQuestionType()!=null) {
				
			}
			if (query.getSubjectName()!=null) {
				
			}
		}
		
		ServletActionContext.getRequest().getSession().setAttribute("pName", this.paper.getPaperName());
		ServletActionContext.getRequest().getSession().setAttribute("pDuration", this.paper.getDuration().toString());
		ServletActionContext.getRequest().getSession().setAttribute("questions", this.questions);
		
		return SUCCESS;
	}
	
	public String step2() throws Exception {
		
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
		
		if (this.questions == null) {
			this.questions = this.qm.getAllQuestion();
		}
		
		ServletActionContext.getRequest().setAttribute("qType", questionType);
		ServletActionContext.getRequest().setAttribute("qLevel", questionLevel);
		ServletActionContext.getRequest().setAttribute("subject", subject);
		
		ServletActionContext.getRequest().setAttribute("papername",this.paper.getPaperName());
		ServletActionContext.getRequest().setAttribute("paperduration",this.paper.getDuration());
		
		
		ServletActionContext.getRequest().getSession().setAttribute("questions", this.questions);
		
		return SUCCESS;
	}
	
	public String addToPaper() throws Exception {
		if (this.questionaddtopaper != null) {
			if (this.questionaddtopaper.size() >0) {
				//执行添加
				
				for (int i=1; i<=this.questionaddtopaper.size(); i++) {
					Integer qid = Integer.parseInt(this.questionaddtopaper.get(i-1));
					Question ques = qm.getById(qid);
					paper.getQuestions().put(i, ques);
				}
			}
		}
		return SUCCESS;
	}
	
	
	public String step3() throws Exception {
		
		ServletActionContext.getRequest().setAttribute("paper",this.paper);
		ServletActionContext.getRequest().setAttribute("papername",this.paper.getPaperName());
		ServletActionContext.getRequest().setAttribute("paperduration",this.paper.getDuration());
		
		return SUCCESS;
	}
	
	public String submitPaper() throws Exception {
		
		paper.setCreateDTime(new Date());
		paper.setCreator(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
		paper.setStatus(Constant.QUESTION_STATUS.DRAFT);
		
		paper.setDuration(Tools.transferMin2Sec(paper.getDuration()));
		
		pm.addPaper(paper);
		
		return SUCCESS;
	}
}
