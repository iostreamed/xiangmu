package com.ksd.oa.service;

import java.util.List;

import com.ksd.oa.dao.NoticeDao;
import com.ksd.oa.entity.Notice;
import com.ksd.oa.util.PageBean;

public class NoticeService {
	
	private NoticeDao noticeDao;

	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	public PageBean<Notice> findAll(int page){
		PageBean<Notice> pageBean = new PageBean<Notice>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = noticeDao.findCount();
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
		List<Notice> list = this.noticeDao.findAll(start, limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	public void addNotice(Notice notice){
		this.noticeDao.addNotice(notice);
	}
	
	public Notice findById(Integer id){
		return this.noticeDao.findById(id);
	}
	
	public void deleteNotice(Notice notice){
		this.noticeDao.deleteNotice(notice);
	}
	
	public void updateNotice(Notice notice){
		this.noticeDao.updateNotice(notice);
	}
	
	public Notice findByLeast(){
		return this.noticeDao.findByLeast();
	}
}
