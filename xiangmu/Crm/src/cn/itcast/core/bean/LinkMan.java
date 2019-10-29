package cn.itcast.core.bean;

import java.io.Serializable;

public class LinkMan implements Serializable{
	private Integer link_id;
	private String cust_name;//
	private String link_name;
	private String link_sex;
	private String link_phone;
	private String link_mobile;
	private Integer start;
	private Integer rows;
	public LinkMan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getLink_id() {
		return link_id;
	}
	public void setLink_id(Integer link_id) {
		this.link_id = link_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getLink_name() {
		return link_name;
	}
	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}
	public String getLink_sex() {
		return link_sex;
	}
	public void setLink_sex(String link_sex) {
		this.link_sex = link_sex;
	}
	public String getLink_phone() {
		return link_phone;
	}
	public void setLink_phone(String link_phone) {
		this.link_phone = link_phone;
	}
	public String getLink_mobile() {
		return link_mobile;
	}
	public void setLink_mobile(String link_mobile) {
		this.link_mobile = link_mobile;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
}
