package com.ksd.oa.entity;

import java.util.Date;

public class Schedule {
	private Integer id;
	private String userName;
	private Date time;
	private String plan;
	public Schedule() {
		super();
	}
	public Schedule(Integer id, String userName, Date time,String plan) {
		super();
		this.id = id;
		this.userName = userName;
		this.time=time;
		this.plan=plan;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	
}
