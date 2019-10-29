package com.ksd.oa.entity;

import java.util.Date;

public class Notice {
	private Integer id;
	private String sender;
	private String title;
	private String content;
	private Date sendTime;
	public Notice() {
		super();
	}
	public Notice(Integer id, String sender, String title, String content,
			Date sendTime) {
		super();
		this.id = id;
		this.sender = sender;
		this.title = title;
		this.content = content;
		this.sendTime = sendTime;
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
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
}
