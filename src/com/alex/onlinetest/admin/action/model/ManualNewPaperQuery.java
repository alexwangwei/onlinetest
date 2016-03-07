package com.alex.onlinetest.admin.action.model;

public class ManualNewPaperQuery {
	
	private String subjectName;
	
	private String questionType;
	
	private String questionLevel;
	
	private Integer questionValue;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

	public Integer getQuestionValue() {
		return questionValue;
	}

	public void setQuestionValue(Integer questionValue) {
		this.questionValue = questionValue;
	}

	public ManualNewPaperQuery() {
		super();
	}
	
	

}
