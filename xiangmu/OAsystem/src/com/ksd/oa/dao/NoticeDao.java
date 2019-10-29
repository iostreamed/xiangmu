package com.ksd.oa.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ksd.oa.entity.Notice;
import com.ksd.oa.util.PageHibernateCallback;

public class NoticeDao extends HibernateDaoSupport{
	 
	public List<Notice> findAll(int start,int limit){
		String hql = "from Notice";
		List<Notice> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Notice>(hql,new Object[] {},start,limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	public int findCount(){
		String hql = "select count(*) from Notice ";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	public void addNotice(Notice notice){
		this.getHibernateTemplate().save(notice);
	}
	
	public Notice findById(Integer id){
		Notice notice = this.getHibernateTemplate().get(Notice.class, id);
		return notice;
	}
	
	public void deleteNotice(Notice notice){
		this.getHibernateTemplate().delete(notice);
	}
	
	public void updateNotice(Notice notice){
		this.getHibernateTemplate().update(notice);
	}
	
	public Notice findByLeast(){
		String hql = "from Notice order by sendTime desc";
		List<Notice> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
