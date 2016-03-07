package com.alex.onlinetest.hbm;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Item")
public class Item {
	
	//标识属性
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "ITEM_ID", unique = true, nullable = false)
	private Integer id;
	
	//题目
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="QUESTION_ID")
	private Question question;
	
	//作答
	@Column(name = "ANSWER", nullable = true)
	private String answer;
	
	//耗时
	@Column(name = "ELAPSED_SECONDS", nullable = true)
	private Integer elapsedSeconds;
	
	//正确与否
	@Column(name = "MARK", nullable = true)
	private boolean mark;
	
	//实际得分
	@Column(name = "ACTUAL_VALUE", nullable = true)
	private Integer actualValue;
	
	//对应的Task
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="TASK_ID")
	private Task task;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getElapsedSeconds() {
		return elapsedSeconds;
	}

	public void setElapsedSeconds(Integer elapsedSeconds) {
		this.elapsedSeconds = elapsedSeconds;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public boolean isMark() {
		return mark;
	}

	public void setMark(boolean mark) {
		this.mark = mark;
	}

	public Integer getActualValue() {
		return actualValue;
	}

	public void setActualValue(Integer actualValue) {
		this.actualValue = actualValue;
	}

	public Item() {
		super();
	}

	
	
}
