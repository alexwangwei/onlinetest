package com.alex.onlinetest.hbm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Role {

	//标识属性
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	private int id;
	
	//角色名属性
	@Column(name="ROLE_NAME", unique=true, nullable=false, length=25)
	private String roleName;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Role(String roleName) {
		this.roleName = roleName;
	}
	public Role() {

	}
	
	
}
