package com.ksd.oa.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ksd.oa.entity.Meeting;
import com.ksd.oa.util.PageHibernateCallback;

public class MeetingDao extends HibernateDaoSupport{
	
	public List<Meeting> findAll(String sender,int start,int limit){
		String hql = "from Meeting m where 1=1";
		if(sender!=null && !"".equals(sender)){
			hql +=" and m.sender like'%"+sender+"%'";			
		}
		List<Meeting> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Meeting>(hql,new Object[] {} ,start, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	public int findCount(String sender){
		String hql = "select count(*) from Meeting m where 1=1";
		if(sender!=null && !"".equals(sender)){
			hql +=" and m.sender like'%"+sender+"%'";
		}
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	public void addMeeting(Meeting meeting){
		this.getHibernateTemplate().save(meeting);
	}
	
	public void deleteMeeting(Meeting meeting){
		this.getHibernateTemplate().delete(meeting);
	}
	
	public Meeting findById(Integer id){
		Meeting meeting = this.getHibernateTemplate().get(Meeting.class, id);
		return meeting;
	}
	
	public void updateMeeting(Meeting meeting){
		this.getHibernateTemplate().update(meeting);
	}
}
