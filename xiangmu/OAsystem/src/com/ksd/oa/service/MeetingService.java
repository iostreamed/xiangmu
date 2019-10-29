package com.ksd.oa.service;

import java.util.List;

import com.ksd.oa.dao.MeetingDao;
import com.ksd.oa.entity.Meeting;
import com.ksd.oa.util.PageBean;

public class MeetingService {
	
	private MeetingDao meetingDao;

	public MeetingDao getMeetingDao() {
		return meetingDao;
	}

	public void setMeetingDao(MeetingDao meetingDao) {
		this.meetingDao = meetingDao;
	}
	
	public PageBean<Meeting> findAll(String sender,int page){
		PageBean<Meeting> pageBean = new PageBean<Meeting>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = meetingDao.findCount(sender);
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
		List<Meeting> list = meetingDao.findAll(sender, start, limit);
		pageBean.setList(list);
		return  pageBean;
	}
	
	public void addMeeting(Meeting meeting){
		this.meetingDao.addMeeting(meeting);
	}
	
	public void deleteMeeting(Meeting meeting){
		this.meetingDao.deleteMeeting(meeting);
	}
	
	public Meeting findById(Integer id){
		return this.meetingDao.findById(id);
	}
	
	public void updateMeeting(Meeting meeting){
		this.meetingDao.updateMeeting(meeting);
	}
}
