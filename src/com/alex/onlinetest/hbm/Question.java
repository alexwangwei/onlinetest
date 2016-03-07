package com.alex.onlinetest.hbm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.alex.onlinetest.util.Constant.QUESTION_LEAVEL;
import com.alex.onlinetest.util.Constant.QUESTION_STATUS;
import com.alex.onlinetest.util.Constant.QUESTION_TYPE;

@Entity
@Table(name="Question")
public class Question {
	
	//标识属性
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "QUESTION_ID", unique = true, nullable = false)
	private Integer id;
	
	//问题所属主题
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SUBJECT_NAME")
	private Subject subject;
	
	//问题类型
	@Column(name = "QUESTION_TYPE", nullable = false)
	private QUESTION_TYPE questionType;
	
	//问题难易程度
	@Column(name = "QUESTION_LEVEL", nullable = false)
	private QUESTION_LEAVEL questionLevel;
	
	//问题分值
	@Column(name = "VALUE", nullable = false)
	private Integer value;
	
	//问题描述
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	//出题人
	@Column(name = "CREATOR", nullable = false)
	private String creator;
	
	//审批人
	@Column(name = "REVIEWER", nullable = true)
	private String reviewer;
	
	//创建时间
	@Column(name = "CREATE_DTIME", nullable = false)
	private Date createDTime;
	
	//审批时间
	@Column(name = "REVIEW_DTIME", nullable = true)
	private Date reviewDTime;
	
	//状态
	@Column(name = "STATUS", nullable = false)
	private QUESTION_STATUS status; 
	
	//选项
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="question")
	@OrderBy("sequence ASC")
	Set<QuestionOption> options = new HashSet<QuestionOption>();
	
	//正确答案
	@Column(name = "REF_ANSWER", nullable = false)
	private String refAnswer;
	
	//试题做题时间，秒
	@Column(name = "DURATION", nullable = false)
	private Integer duration;
	
	//映射关系
	@OneToMany(fetch=FetchType.EAGER, mappedBy="question")
	private List<Item> items; 
	
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="questions")
	private List<Paper> papers; 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public QUESTION_TYPE getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QUESTION_TYPE questionType) {
		this.questionType = questionType;
	}

	public QUESTION_LEAVEL getQuestionLevel() {
		return questionLevel;
	}

	public void setQuestionLevel(QUESTION_LEAVEL questionLevel) {
		this.questionLevel = questionLevel;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public Date getCreateDTime() {
		return createDTime;
	}

	public void setCreateDTime(Date createDTime) {
		this.createDTime = createDTime;
	}

	public Date getReviewDTime() {
		return reviewDTime;
	}

	public void setReviewDTime(Date reviewDTime) {
		this.reviewDTime = reviewDTime;
	}

	public QUESTION_STATUS getStatus() {
		return status;
	}

	public void setStatus(QUESTION_STATUS status) {
		this.status = status;
	}

	public Set<QuestionOption> getOptions() {
		return options;
	}

	public void setOptions(Set<QuestionOption> options) {
		this.options = options;
	}

	public String getRefAnswer() {
		return refAnswer;
	}

	public void setRefAnswer(String refAnswer) {
		this.refAnswer = refAnswer;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Question() {
	
	}
	
	
	
}
