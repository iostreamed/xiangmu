package com.ksd.oa.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ksd.oa.entity.Worklog;
import com.ksd.oa.util.PageHibernateCallback;

public class WorklogDao extends HibernateDaoSupport{
	
	public List<Worklog> findByUserName(String userName,int start,int limit){
		String hql = "from Worklog where userName=?";
		List<Worklog> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Worklog>(hql,new Object[] {userName},start,limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public int findCount(String userName){
		String hql = "select count(*) from Worklog where userName=?";
		List<Long> list = this.getHibernateTemplate().find(hql,userName);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	public Worklog findById(Integer id){
		Worklog worklog = this.getHibernateTemplate().get(Worklog.class, id);
		return worklog;
	}
	
	public void addWorklog(Worklog worklog){
		this.getHibernateTemplate().save(worklog);
	}
	
	public void updateWorklog(Worklog worklog){
		this.getHibernateTemplate().update(worklog);
	}
	
	public void deleteWorklog(Worklog worklog){
		this.getHibernateTemplate().delete(worklog);
	}
}
