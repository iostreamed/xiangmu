package com.ksd.oa.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ksd.oa.dao.UserDao;
import com.ksd.oa.entity.User;
import com.ksd.oa.util.PageBean;

@Transactional
public class UserService {
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//用户登录的方法
	public User login(User user) {
		return userDao.login(user);
	}
	
	public PageBean<User> findAll(String userName,Integer userStatus,int page){
		PageBean<User> pageBean = new PageBean<User>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = userDao.findCount(userName,userStatus);
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
		List<User> list = userDao.findAll(userName,userStatus,start, limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	public User findById(Integer id){
		return userDao.findById(id);
	}
	
	public void addUser(User user){
		this.userDao.addUser(user);
	}
	
	public void updateUser(User user){
		this.userDao.updateUser(user);
	}
	
	public void deleteUser(User user){
		this.userDao.deleteUser(user);
	}
	
	public User findByName(String userName){
		return userDao.findByName(userName);
	}
}
