package cn.itcast.core.bean;

import java.io.Serializable;
import java.util.Date;

public class Visit implements Serializable {
	private Integer visit_id;
	private Long user_id;
	private Date visit_time;
	private String visit_interviewer;//拜访的客户
	private String visit_address;
	private String visit_detail;//拜访详情
	private Date visit_nexttime;//下次拜访时间
	private Integer start;
	private Integer rows;
	public Visit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getVisit_id() {
		return visit_id;
	}
	public void setVisit_id(Integer visit_id) {
		this.visit_id = visit_id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Date getVisit_time() {
		return visit_time;
	}
	public void setVisit_time(Date visit_time) {
		this.visit_time = visit_time;
	}
	public String getVisit_interviewer() {
		return visit_interviewer;
	}
	public void setVisit_interviewer(String visit_interviewer) {
		this.visit_interviewer = visit_interviewer;
	}
	public String getVisit_address() {
		return visit_address;
	}
	public void setVisit_address(String visit_address) {
		this.visit_address = visit_address;
	}
	public String getVisit_detail() {
		return visit_detail;
	}
	public void setVisit_detail(String visit_detail) {
		this.visit_detail = visit_detail;
	}
	public Date getVisit_nexttime() {
		return visit_nexttime;
	}
	public void setVisit_nexttime(Date visit_nexttime) {
		this.visit_nexttime = visit_nexttime;
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
