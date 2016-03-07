package com.alex.onlinetest.hbm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.alex.onlinetest.util.Constant.QUESTION_STATUS;

@Entity
@Table(name="Paper")
public class Paper {

	//标识属性
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "PAPER_ID", unique = true, nullable = false)
	private Integer id;
	
	//试卷名
	@Column(name = "PAPER_NAME", unique = true, nullable = false)
	private String paperName;
	
	//考试时长
	@Column(name = "DURATION", nullable = true)
	private Integer duration;
	
	//试题
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="PAPER_QUESTION", joinColumns={
			@JoinColumn(name="PAPER_ID", nullable=false, updatable=false)
	},inverseJoinColumns={
			@JoinColumn(name="QUESTION_ID", nullable=false, updatable=false)
	})
	@MapKeyColumn(name="SEQUENCE")
	private Map<Integer,Question> questions = new TreeMap<Integer,Question>();
	
	//出题者
	@Column(name = "CREATOR", nullable = false)
	private String creator;
	
	//出题时间
	@Column(name = "CREATE_DTIME", nullable = false)
	private Date createDTime;
	
	//状态
	@Column(name = "STATUS", nullable = false)
	private QUESTION_STATUS status; 
	
	//审阅者
	@Column(name = "REVIEWER", nullable = true)
	private String reviewer;
	
	//审阅时间
	@Column(name = "REVIEW_DTIME", nullable = true)
	private Date reviewDTime;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Integer getDuration() {
		if (this.duration == null) {
			Integer total = 0;
			for (int i=0; i<this.questions.size(); i++) {
				total = total + this.questions.get(i).getDuration();
			}
			return total;
		} else {
			return this.duration;
		}
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Map<Integer, Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Map<Integer, Question> questions) {
		this.questions = questions;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDTime() {
		return createDTime;
	}

	public void setCreateDTime(Date createDTime) {
		this.createDTime = createDTime;
	}

	public QUESTION_STATUS getStatus() {
		return status;
	}

	public void setStatus(QUESTION_STATUS status) {
		this.status = status;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public Date getReviewDTime() {
		return reviewDTime;
	}

	public void setReviewDTime(Date reviewDTime) {
		this.reviewDTime = reviewDTime;
	}
	
	public Integer getAmountOfQuestion() {
		return this.questions.size();
	}
	
	public Integer getTotalValue() {
		Integer totalValue = 0;
		
		for (Question value : this.questions.values()) {
			totalValue = totalValue + value.getValue();
		}
		return totalValue;
	}
	
	public Paper() {

	}
	
	
}
