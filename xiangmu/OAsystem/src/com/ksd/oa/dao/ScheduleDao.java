package com.ksd.oa.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ksd.oa.entity.Schedule;
import com.ksd.oa.util.PageHibernateCallback;

public class ScheduleDao extends HibernateDaoSupport{

	public List<Schedule> findByUserName(String userName,int start,int limit){
		String hql="from Schedule s where s.userName=?";
		List<Schedule> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Schedule>(hql,new Object[] {userName} ,start, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	public int findCount(String userName){
		String hql = "select count(*) from Schedule s where s.userName=?";		
		List<Long> list = this.getHibernateTemplate().find(hql,userName);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	public void addSchedule(Schedule schedule){
		this.getHibernateTemplate().save(schedule);
	}
	
	public void deleteSchedule(Schedule schedule){
		this.getHibernateTemplate().delete(schedule);
	}
	
	public Schedule findById(Integer id){
		Schedule schedule= this.getHibernateTemplate().get(Schedule.class, id);
		return schedule;
	}
	
	public void updateScedule(Schedule schedule){
		this.getHibernateTemplate().update(schedule);
	}
}
