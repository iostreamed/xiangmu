package com.smbms.entity;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class User {
	private Integer id;			//用户ID
	private String usercode;	//用户编码
	private String username;	//用户姓名
	private String password;	//密码
	private Integer gender;		//性别（1.男  2.女）
	private Date birthday;		//生日
	private String phone;		//电话号码
	private String address;		//地址
	private Integer userRole;	//用户角色
	private Integer createBy;	//创建人
	private Date createDate;	//创建时间
	private Integer modifyBy;	//修改人
	private Date modifyDate;	//修改时间
	private Integer age;		//年龄
	private Role role;			//角色
	private String image;		//照片
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender=gender;
	}

	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		Calendar c1 = Calendar.getInstance();
		int nowYear = c1.get(Calendar.YEAR);
		c1.setTime(birthday);
		int birthYear = c1.get(Calendar.YEAR);
		int age = nowYear-birthYear;
		if(age <0){
			age = 0;
		}
		this.age=age;
		this.birthday = birthday;
	}
	public Integer getAge() {
		return age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
