package com.ksd.oa.entity;

import java.util.Date;

public class Meeting {
	private Integer id;
	private String sender;
	private Date startTime;
	private Date endTime;
	private String address;
	private String title;
	private String content;
	public Meeting() {
		super();
	}
	public Meeting(Integer id, String sender, Date startTime, Date endTime,
			String address,String title, String content) {
		super();
		this.id = id;
		this.sender = sender;
		this.startTime = startTime;
		this.endTime = endTime;
		this.address=address;
		this.title = title;
		this.content = content;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
