package com.ksd.oa.service;

import java.util.List;

import com.ksd.oa.dao.ScheduleDao;
import com.ksd.oa.entity.Address;
import com.ksd.oa.entity.Schedule;
import com.ksd.oa.util.PageBean;

public class ScheduleService {
	private ScheduleDao scheduleDao;

	public ScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}
	
	public PageBean<Schedule> findByName(String userName,int page){
		PageBean<Schedule> pageBean = new PageBean<Schedule>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = scheduleDao.findCount(userName);
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
		List<Schedule> list = scheduleDao.findByUserName(userName, start, limit);
		pageBean.setList(list);
		return pageBean;		
	}
	
	public void addSchedule(Schedule schedule){
		this.scheduleDao.addSchedule(schedule);
	}
	
	public void deleteSchedule(Schedule schedule){
		this.scheduleDao.deleteSchedule(schedule);
	}
	
	public Schedule findById(Integer id){
		return this.scheduleDao.findById(id);
	}
	
	public void updateSchedule(Schedule schedule){
		this.scheduleDao.updateScedule(schedule);
	}
}
