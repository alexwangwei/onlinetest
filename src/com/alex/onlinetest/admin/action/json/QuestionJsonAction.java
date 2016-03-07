package com.alex.onlinetest.admin.action.json;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.alex.onlinetest.admin.service.QuestionManagement;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.util.Constant;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/admin/json")
@ParentPackage("json-default")
public class QuestionJsonAction extends ActionSupport {
	
	//用户Ajax返回数据
	private SortedMap<String, Object> data = new TreeMap<String, Object>();
	
	//Struts的属性驱动模式，自动填充页面的属性到这里
	private String questionType;	//问题类型
	private String questionLevel;	//问题难易程度
	private String questionSubject;	//问题主题
	private String questionValue;	//问题分值
	
	//Spring 注入组件
	private QuestionManagement qm;
	
	private static Logger logger = Logger.getLogger(QuestionJsonAction.class); 

	public SortedMap<String, Object> getData() {
		return data;
	}
	public void setData(SortedMap<String, Object> data) {
		this.data = data;
	}
	public void setQm(QuestionManagement qm) {
		this.qm = qm;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestionLevel() {
		return questionLevel;
	}
	public void setQuestionLevel(String questionLevel) {
		this.questionLevel = questionLevel;
	}
	public String getQuestionSubject() {
		return questionSubject;
	}
	public void setQuestionSubject(String questionSubject) {
		this.questionSubject = questionSubject;
	}
	public String getQuestionValue() {
		return questionValue;
	}
	public void setQuestionValue(String questionValue) {
		this.questionValue = questionValue;
	}
	
	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}
	
	
	/**
	 * 直接返回系统中所拥有的所有问题
	 * @return
	 * @throws Exception
	 */
	@Action(value="getallquestion", className="questionjsonAction", results={
			@Result(name="success", type="json", params={
					"root","data"
			})
	})
	public String getAllQuestion() throws Exception {
		this.data.clear();
		
		List<Question> questions = qm.getAllQuestion();
		
		this.data.put("total", questions.size());
		
		for (int i=0; i<questions.size(); i++) {
			Question question = questions.get(i);
			//将Hibernate的Subject-->Question的关联取消，否则返回数据较多
			question.getSubject().setQuestions(null);
			this.data.put("ID"+String.valueOf(i), question);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 只判断questionSubject参数，根据该参数的值返回相应的结果
	 * @return
	 * @throws Exception
	 */
	@Action(value="getallquestionbysubject", className="questionjsonAction", results={
			@Result(name="success", type="json", params={
					"root","data"
			})
	})
	public String getAllQuestionBySubject() throws Exception {
		
		this.data.clear();
		int n=1;
		
		//按questionSubject进行筛选
		if (this.questionSubject!=null) {
			List<Question> questions = qm.getAllQuestion();
			for (int i=0; i<questions.size(); i++) {
				Question question = questions.get(i);
				if (question.getSubject().getSubjectName().equals(questionSubject)) {
					question.getSubject().setQuestions(null);
					this.data.put("ID"+String.valueOf(n++), question);
				}
			}
		}
		
		this.data.put("total", n-1);
		
		return SUCCESS;
	}

	/**
	 * 只判断questionLevel参数，根据该参数的值返回相应的结果
	 * @return
	 * @throws Exception
	 */
	@Action(value="getallquestionbylevel", className="questionjsonAction", results={
			@Result(name="success", type="json", params={
					"root","data"
			})
	})
	public String getAllQuestionByLevel() throws Exception {
		this.data.clear();
		int n=1;
		Constant.QUESTION_LEAVEL level;
		
		//按questionSubject进行筛选
		if (this.questionLevel!=null) {
			List<Question> questions = qm.getAllQuestion();
			for (int i=0; i<questions.size(); i++) {
				Question question = questions.get(i);
				switch(questionLevel)
				{
				case "容易":
					level = Constant.QUESTION_LEAVEL.EASY;
					break;
				case "一般":
					level = Constant.QUESTION_LEAVEL.MIDDLE;
					break;
				case "困难":
					level = Constant.QUESTION_LEAVEL.DIFFICULT;
					break;
				default:
					level = null;
					break;
				}
				if (question.getQuestionLevel() == level) {
					question.getSubject().setQuestions(null);
					this.data.put("ID"+String.valueOf(n++), question);
				}
			}
		}
		
		this.data.put("total", n-1);
		return SUCCESS;
	}
	
	/**
	 * 只判断questionType参数，根据该参数的值返回相应的结果
	 * @return
	 * @throws Exception
	 */
	@Action(value="getallquestionbytype", className="questionjsonAction", results={
			@Result(name="success", type="json", params={
					"root","data"
			})
	})
	public String getAllQuestionByType() throws Exception {
		this.data.clear();
		int n=1;
		Constant.QUESTION_TYPE type;
		
		//按questionSubject进行筛选
		if (this.questionType!=null) {
			List<Question> questions = qm.getAllQuestion();
			for (int i=0; i<questions.size(); i++) {
				Question question = questions.get(i);
				switch(questionType)
				{
				case "单选题":
					type = Constant.QUESTION_TYPE.SINGLE;
					break;
				case "多选题":
					type = Constant.QUESTION_TYPE.MUL;
					break;
				case "判断题":
					type = Constant.QUESTION_TYPE.YESNO;
					break;
				case "问答题":
					type = Constant.QUESTION_TYPE.OPENQUESTION;
					break;
				default:
					type = null;
					break;
				}
				if (question.getQuestionType() == type) {
					question.getSubject().setQuestions(null);
					this.data.put("ID"+String.valueOf(n++), question);
				}
			}
		}
		this.data.put("total", n-1);
		return SUCCESS;
	}
	
	/**
	 * 只判断questionValue参数，根据该参数的值返回相应的结果
	 * @return
	 * @throws Exception
	 */
	@Action(value="getallquestionbyvalue", className="questionjsonAction", results={
			@Result(name="success", type="json", params={
					"root","data"
			})
	})
	public String getAllQuestionByValue() throws Exception {
		this.data.clear();
		int n=1;
		
		//按questionSubject进行筛选
		if (this.questionValue!=null) {
			List<Question> questions = qm.getAllQuestion();
			for (int i=0; i<questions.size(); i++) {
				Question question = questions.get(i);
				if (question.getValue()==Integer.parseInt(questionValue)) {
					question.getSubject().setQuestions(null);
					this.data.put("ID"+String.valueOf(n++), question);
				}
			}
		}
		this.data.put("total", n-1);
		return SUCCESS;
	}
}
