package com.alex.onlinetest.hbm;

import java.util.ArrayList;
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

import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "USER_ID", unique = true, nullable = false)
	//标识属性
	private Integer id;
	
	@Column(name="LOGIN_ID",unique=true, nullable=false, length=25)
	//User的login id属性
	private String loginId;
	
	//User的显示名属性
	@Column(name="DISPLAY_NAME", nullable=false, length=25)
	private String displayName;
	
	@Column(name="PASSWORD", nullable=false, length=25)
	//User的密码属性
	private String password;
	
	//1-N关联关系，表明用户所拥有的角色,采用连接表
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="user_role", joinColumns={
			@JoinColumn(name="USER_ID", nullable=false, updatable=false)
	},inverseJoinColumns={
			@JoinColumn(name="ROLE_ID", nullable=false, updatable=false)
	})
	private List<Role> role = new ArrayList<Role>();
	
	//1-N关联关系，表明用户所拥有的组
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="user_group", joinColumns={
			@JoinColumn(name="USER_ID", nullable=false, updatable=false)
	},inverseJoinColumns={
			@JoinColumn(name="GROUP_ID", nullable=false, updatable=false)
	})
	private Set<UserGroup> group = new HashSet<UserGroup>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRole() {
		return role;
	}
	public void setRole(List<Role> role) {
		this.role = role;
	}
	
	public Set<UserGroup> getGroup() {
		return group;
	}
	public void setGroup(Set<UserGroup> group) {
		this.group = group;
	}
	public User(String loginId, String displayName, String password,
			List<Role> role, Set<UserGroup> group) {
		this.loginId = loginId;
		this.displayName = displayName;
		this.password = password;
		this.role = role;
		this.group = group;
	}
	public User() {

	}
	
	
}
