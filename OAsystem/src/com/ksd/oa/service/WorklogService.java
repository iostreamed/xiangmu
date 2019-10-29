package com.ksd.oa.service;

import java.util.List;

import com.ksd.oa.dao.WorklogDao;
import com.ksd.oa.entity.Worklog;
import com.ksd.oa.util.PageBean;

public class WorklogService {

	private WorklogDao worklogDao;

	public WorklogDao getWorklogDao() {
		return worklogDao;
	}

	public void setWorklogDao(WorklogDao worklogDao) {
		this.worklogDao = worklogDao;
	}
	
	public PageBean<Worklog> findByUserName(String userName,int page){
		PageBean<Worklog> pageBean = new PageBean<Worklog>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = worklogDao.findCount(userName);
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
		List<Worklog> list = worklogDao.findByUserName(userName, start, limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	public Worklog findById(Integer id){
		return this.worklogDao.findById(id);
	}
	
	public void addWorklog(Worklog worklog){
		this.worklogDao.addWorklog(worklog);
	}
	
	public void updateWorklog(Worklog worklog){
		this.worklogDao.updateWorklog(worklog);
	}
	
	public void deleteWorklog(Worklog worklog){
		this.worklogDao.deleteWorklog(worklog);
	}
}
