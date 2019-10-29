package com.ksd.oa.entity;

import java.util.Date;

public class User {
	private Integer id;			
	private String userName;
	private String password;
	private Integer userType;
	private Integer userStatus;
	private Date createTime;
	private Date modifyTime;
	private Date loginTime;
	private String nikeName;
	
	public User() {
		super();
	}
	
	public User(Integer id, String userName, String password, Integer userType,
			Integer userStatus, Date createTime, Date modifyTime,
			Date loginTime, String nikeName) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.userStatus = userStatus;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.loginTime = loginTime;
		this.nikeName = nikeName;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getNikeName() {
		return nikeName;
	}
	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}
}
