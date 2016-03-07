package com.alex.onlinetest.hbm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ResultReport")
public class ResultReport {

	//标识属性
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "ITEM_ID", unique = true, nullable = false)
	private Integer id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TASK_ID")
	private Task task;
	
	@Column(name = "FINISH_DATE", nullable = true)
	private Date finishDate;
	
	@Column(name = "AMOUNT_OF_QUESTION", nullable = true)
	private Integer amountOfQuestion;
	
	@Column(name = "AMOUNT_OF_RIGHT", nullable = true)
	private Integer amountOfRight;
	
	@Column(name = "TOTAL_VALUE", nullable = true)
	private Integer totalValue;
	
	@Column(name = "ACTUAL_VALUE", nullable = true)
	private Integer actualValue;
	
	@Column(name = "PERCENTAGE_OF_PASS", nullable = true)
	private float percentageOfPass;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Integer getAmountOfQuestion() {
		return amountOfQuestion;
	}

	public void setAmountOfQuestion(Integer amountOfQuestion) {
		this.amountOfQuestion = amountOfQuestion;
	}

	public Integer getAmountOfRight() {
		return amountOfRight;
	}

	public void setAmountOfRight(Integer amountOfRight) {
		this.amountOfRight = amountOfRight;
	}

	public Integer getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Integer totalValue) {
		this.totalValue = totalValue;
	}

	public Integer getActualValue() {
		return actualValue;
	}

	public void setActualValue(Integer actualValue) {
		this.actualValue = actualValue;
	}

	public float getPercentageOfPass() {
		return percentageOfPass;
	}

	public void setPercentageOfPass(float percentageOfPass) {
		this.percentageOfPass = percentageOfPass;
	}

	public ResultReport() {
		super();
	}
	
	
}
