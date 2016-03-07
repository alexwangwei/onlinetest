package com.alex.onlinetest.student.action.model;

import java.util.List;

import com.alex.onlinetest.hbm.Question;

public class ExamDeskModel {
	
	private Integer taskId;
	private Integer questionId;
	
	private Question question;
	
	private List<String> mul;
	private String single;
	private String openQuestion;
	private String yesorno;
	private String type;
	
	private boolean isFirst;
	private boolean isLast;
	
	private Integer aviableSeconds;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<String> getMul() {
		return mul;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public void setMul(List<String> mul) {
		this.mul = mul;
	}

	public String getSingle() {
		return single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

	public String getOpenQuestion() {
		return openQuestion;
	}

	public void setOpenQuestion(String openQuestion) {
		this.openQuestion = openQuestion;
	}

	public String getYesorno() {
		return yesorno;
	}

	public void setYesorno(String yesorno) {
		this.yesorno = yesorno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public Integer getAviableSeconds() {
		return aviableSeconds;
	}

	public void setAviableSeconds(Integer aviableSeconds) {
		this.aviableSeconds = aviableSeconds;
	}

	public ExamDeskModel() {
		super();
	}
	
	

}
