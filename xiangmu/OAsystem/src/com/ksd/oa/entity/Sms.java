package com.ksd.oa.entity;

import java.util.Date;

public class Sms {
	private Integer id;
	private String userName;
	private String sender;
	private String message;
	private Date sendTime;
	private Integer isRead;
	public Sms() {
		super();
	}
	public Sms(Integer id,String userName ,String sender , String message,
			Date sendTime, Integer isRead) {
		super();
		this.id = id;
		this.sender = sender;
		this.userName = userName;
		this.message = message;
		this.sendTime = sendTime;
		this.isRead = isRead;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
}
