package com.ksd.oa.service;

import java.util.List;

import com.ksd.oa.dao.SmsDao;
import com.ksd.oa.entity.Sms;
import com.ksd.oa.entity.User;
import com.ksd.oa.util.PageBean;

public class SmsService {
	private SmsDao smsDao;

	public SmsDao getSmsDao() {
		return smsDao;
	}

	public void setSmsDao(SmsDao smsDao) {
		this.smsDao = smsDao;
	}
	
	public PageBean<Sms> findByUserName(String userName,int page){
		PageBean<Sms> pageBean = new PageBean<Sms>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = smsDao.findCount(userName);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//从哪开始
		int start = (page-1)*limit;
		//每页显示的数据集合
		List<Sms> list = smsDao.findByUserName(userName,start, limit);
		pageBean.setList(list);
		return pageBean;		
	}
	
	public void addSms(Sms sms){
		this.smsDao.addSms(sms);
	}
	
	public List<User> findUser(){
		return this.smsDao.findUser();
	}
	
	public Sms findById(Integer id){
		return this.smsDao.findById(id);
	}
	public void updateIsRead(Sms sms){
		this.smsDao.updateIsRead(sms);
	}
	public void deleteSms(Sms sms){
		this.smsDao.deleteSms(sms);
	}
	
	public int findByNoReadCount(String userName){
		return this.smsDao.findByNoReadCount(userName);
	}
} 
