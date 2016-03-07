package com.alex.onlinetest.hbm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Navigator {

	//标识属性
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "NAV_ID", unique = true, nullable = false)
	private int id;
	
	//父节点
	@Column(name="PARENT_ID", nullable=true)
	private int parentId;
	
	//节点顺序
	@Column(name="NODE_SEQUENCE")
	private int nodeSequence;
	
	//节点名
	@Column(name="NODE_NAME", unique = true)
	private String nodeName;
	
	//URL
	@Column(name="URL", nullable=true, length=250)
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getNodeSequence() {
		return nodeSequence;
	}

	public void setNodeSequence(int nodeSequence) {
		this.nodeSequence = nodeSequence;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Navigator() {

	}	
	
	
}
