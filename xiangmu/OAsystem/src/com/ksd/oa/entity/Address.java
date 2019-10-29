package com.ksd.oa.entity;

import java.io.Serializable;

public class Address implements Serializable{
	private Integer id;
	private String userName;
	private String name;
	private String sex;
	private String mobile;
	private String email;
	private String qq;
	private String address;
	private String postcode;
	
	
	public Address() {
		super();
	}
	public Address(Integer id, String userName, String name, String sex,
			String mobile,String email, String qq, String address, String postcode) {
		super();
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.sex = sex;
		this.mobile = mobile;
		this.email=email;
		this.qq = qq;
		this.address = address;
		this.postcode = postcode;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
}
