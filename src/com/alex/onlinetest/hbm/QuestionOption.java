package com.alex.onlinetest.hbm;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="QuestionOption")
public class QuestionOption {
	
	//标识属性
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "OPTION_ID", unique = true, nullable = false)
	private Integer id;
	
	//对应的问题
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="QUESTION_ID")
	private Question question;
	
	//选项描述
	@Column(name="OPTION_DESCRIPTION", nullable=false, length=255)
	private String optionDescription;
	
	//选项序号
	@Column(name="SEQUENCE", nullable=false)
	private Integer sequence;


	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOptionDescription() {
		return optionDescription;
	}

	public void setOptionDescription(String optionDescription) {
		this.optionDescription = optionDescription;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public QuestionOption() {

	}
	
	

}
