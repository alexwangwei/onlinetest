package com.alex.onlinetest.hbm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.alex.onlinetest.util.Constant.QUESTION_STATUS;
import com.alex.onlinetest.util.Constant.SCHEDULE_STATUS;
import com.alex.onlinetest.util.Tools;

@Entity
@Table(name="Schedule")
public class Schedule {
	
	//标识属性
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "SCHEDULE_ID", unique = true, nullable = false)
	private Integer id;
	
	//流水号，由日期产生
	@Column(name = "NAME", nullable = false)
	private String name;
	
	//考试时长
	@Column(name = "DURATION", nullable = false)
	private Integer duration;
	
	//试卷
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PAPER_NAME")
	private Paper paper;
	
	//参考对象
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="SCHEDULE_USERGROUP", joinColumns={
			@JoinColumn(name="SCHEDULE_ID", nullable=false, updatable=false)
	},inverseJoinColumns={
			@JoinColumn(name="GROUP_ID", nullable=false, updatable=false)
	})
	private Set<UserGroup> usergroup = new HashSet<UserGroup>();
	
	//生效时间
	@Column(name = "EFFECTIVE_DATE", nullable = false)
	private Date effectiveDate;
	
	//时效时间
	@Column(name = "DIABLE_DATE", nullable = false)
	private Date disableDate;
	
	//状态
	@Column(name = "STATUS", nullable = false)
	private SCHEDULE_STATUS status;
	
	//Task
	@OneToMany(fetch=FetchType.EAGER, mappedBy="schedule")
	private List<Task> tasks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Set<UserGroup> getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(Set<UserGroup> usergroup) {
		this.usergroup = usergroup;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(Date disableDate) {
		this.disableDate = disableDate;
	}

	public SCHEDULE_STATUS getStatus() {
		return status;
	}

	public void setStatus(SCHEDULE_STATUS status) {
		this.status = status;
	}

	public Schedule() {
		super();
	}

	
	public String getFormatedEffectiveDate() throws Exception {
		return Tools.formatDateToString(this.effectiveDate);
	}
	
	public String getFormatedDisableDate() throws Exception {
		return Tools.formatDateToString(this.disableDate);
	}
	
}
