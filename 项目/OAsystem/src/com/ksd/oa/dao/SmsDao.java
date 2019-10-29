package com.ksd.oa.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ksd.oa.entity.Sms;
import com.ksd.oa.entity.User;
import com.ksd.oa.util.PageHibernateCallback;

public class SmsDao extends HibernateDaoSupport{
	
	public List<Sms> findByUserName(String userName,int start,int limit){
		String hql="from Sms s where s.userName=?";
		List<Sms> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Sms>(hql,new Object[] {userName} ,start, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	public int findCount(String userName){
		String hql = "select count(*) from Sms s where s.userName=?";		
		List<Long> list = this.getHibernateTemplate().find(hql,userName);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	public List<User> findUser(){
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;		
	}
	
	public void addSms(Sms sms){
		this.getHibernateTemplate().save(sms);
	}
	
	public Sms findById(Integer id){
		return this.getHibernateTemplate().get(Sms.class, id);	
	}
	public void updateIsRead(Sms sms){
		this.getHibernateTemplate().update(sms);
	}
	public void deleteSms(Sms sms){
		this.getHibernateTemplate().delete(sms);
	}
	
	public int findByNoReadCount(String userName){
		String hql = "select count(*) from Sms where userName=? and isRead=1 ";
		List<Long> list = this.getHibernateTemplate().find(hql,userName);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
}
