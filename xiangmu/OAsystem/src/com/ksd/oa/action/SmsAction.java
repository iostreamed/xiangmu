package com.ksd.oa.action;


import java.util.Date;
import java.util.List;

import com.ksd.oa.entity.Sms;
import com.ksd.oa.entity.User;
import com.ksd.oa.service.SmsService;
import com.ksd.oa.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SmsAction extends ActionSupport implements ModelDriven<Sms> {
	private Sms sms=new Sms();
	public Sms getModel() {
		return sms;
	}
	
	private SmsService smsService;
	public SmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}
	private int page;
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public String smslist(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		PageBean<Sms> smslist = smsService.findByUserName(user.getUserName(),page);
		ActionContext.getContext().getSession().put("smslist", smslist);
		return "smslist";
	}
	
	public String addSms(){
		return "addSms";
	}
	
	public String sendSms(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<User> list = smsService.findUser();
		for(User user1 : list){
			if(user1.getUserName().equals(sms.getUserName()) && !user1.getUserName().equals(user.getUserName())){
				Sms sms1 = new Sms(null, sms.getUserName(),user.getUserName() , sms.getMessage(), new Date(), 1);
				smsService.addSms(sms1);
				return  "sendSuccess";
			}
		}
		this.addActionError("没有此用户，发送失败");
		return "addSms";		
	}
	public String updateIsRead(){
		Sms sms1 = smsService.findById(sms.getId());
		sms1.setIsRead(2);
		smsService.updateIsRead(sms1);
		noReadCount();
		return "updateIsRead";
	}
	
	public String deleteSms(){
		Sms sms1 = smsService.findById(sms.getId());
		smsService.deleteSms(sms1);
		return "deleteSms";
	}
	
	public String noReadCount(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		int noReadCount = smsService.findByNoReadCount(user.getUserName());
		ActionContext.getContext().getSession().put("noReadCount", noReadCount);
		return"noReadCount";
	}
}
