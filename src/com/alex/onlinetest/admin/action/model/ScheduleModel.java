package com.alex.onlinetest.admin.action.model;

import java.util.Date;
import java.util.List;

public class ScheduleModel {
	
	private Integer paperId;
	private List<String> usergroup;
	private String effectiveDate;
	private String disableDate;
	private Integer duration;
	
	
	
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public List<String> getUsergroup() {
		return usergroup;
	}
	public void setUsergroup(List<String> usergroup) {
		this.usergroup = usergroup;
	}
	
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getDisableDate() {
		return disableDate;
	}
	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
	}
	public ScheduleModel() {
		super();
	}
	
	

}
