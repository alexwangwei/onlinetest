package com.alex.onlinetest.hbm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class UserGroup {

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "GROUP_ID", unique = true, nullable = false)	
	//标识属性
	private Integer id;
	
	@Column(name = "GROUP_NAME", unique=true, nullable=false, length=25)
	//Group名属性
	private String groupName;
	
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="usergroup")
	private List<Schedule> schedules; 
	
	
	public List<Schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public UserGroup(String groupName, User user) {
		this.groupName = groupName;
	}
	public UserGroup() {

	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		
		if (obj == null) return false;
		
		if (getClass() != obj.getClass()) return false;
		
		UserGroup other = (UserGroup) obj;
		
		if (id != other.id) return false;
		
		if (groupName == null) {
			if (other.groupName != null) return false;
		} else if (!groupName.equals(other.groupName)) return false;
		
		return true;
	}
	
	
	
}
