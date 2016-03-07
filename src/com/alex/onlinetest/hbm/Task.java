package com.alex.onlinetest.hbm;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.alex.onlinetest.util.Constant.TASK_STATUS;

@Entity
@Table(name="Task")
public class Task {
	//标识属性
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "TASK_ID", unique = true, nullable = false)
	private Integer id;
	
	//任务名
	@Column(name = "TASKNAME", nullable = true)
	private String taskName;
	
	//TASK对应的Schedule
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SCHEDULE_ID")
	private Schedule schedule;
	
	//任务所有者
	@Column(name = "OWNER", nullable = false)
	private String owner;
	
	//任务完成情况
	@Column(name = "PERCENTAGE", nullable = true)
	private Integer percentage;
	
	//状态
	@Column(name = "STATUS", nullable = true)
	private TASK_STATUS status;
	
	//任务项
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="task")
	private Set<Item> items;
	
	//开始时间
	@Column(name = "ACTUAL_START", nullable = true)
	private Date actualStart;
	
	//完成时间
	@Column(name = "ACTUAL_FINISH", nullable = true)
	private Date actualFinish;
	
	//动态时间
	@Column(name = "DYNAMIC", nullable = true)
	private Date dynamic;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}



	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	public TASK_STATUS getStatus() {
		return status;
	}

	public void setStatus(TASK_STATUS status) {
		this.status = status;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
	

	public Date getActualStart() {
		return actualStart;
	}

	public void setActualStart(Date actualStart) {
		this.actualStart = actualStart;
	}

	public Date getActualFinish() {
		return actualFinish;
	}

	public void setActualFinish(Date actualFinish) {
		this.actualFinish = actualFinish;
	}

	public Date getDynamic() {
		return dynamic;
	}

	public void setDynamic(Date dynamic) {
		this.dynamic = dynamic;
	}

	public Task() {
		super();
	}
	
	
}
