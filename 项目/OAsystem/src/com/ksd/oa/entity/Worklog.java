package com.ksd.oa.entity;

import java.util.Date;

public class Worklog {
	private Integer id;
	private String UserName;
	private String title;
	private String description;
	private Date logTime;
	public Worklog() {
		super();
	}
	public Worklog(Integer id, String userName, String title,
			String description, Date logTime) {
		super();
		this.id = id;
		UserName = userName;
		this.title = title;
		this.description = description;
		this.logTime = logTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	
}
