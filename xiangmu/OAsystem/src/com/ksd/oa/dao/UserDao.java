package com.ksd.oa.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ksd.oa.entity.User;
import com.ksd.oa.util.PageHibernateCallback;




public class UserDao extends HibernateDaoSupport{
	
	//用户登录方法
		@SuppressWarnings("unchecked")
		public User login(User user) {
			String hql = "from User where userName=? and password=? and userStatus=?";
			List<User> list = this.getHibernateTemplate().find(hql, user.getUserName(),user.getPassword(),1);
			if(list != null && list.size() > 0){
				return list.get(0);
			}
			return null;
		}
		
		@SuppressWarnings("unchecked")
		public List<User> findAll(String userName,Integer userStatus,int start,int limit){
			String hql="from User u where 1=1 ";
			if(userName!=null && !"".equals(userName)){
				hql += " and u.userName like'%"+userName+"%'";
			}
			if(userStatus!=null && !"".equals(userStatus)){
				hql +=" and u.userStatus='"+userStatus+"'";
			}
			List<User> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<User>(hql,new Object[] {} ,start, limit));
			if(list != null && list.size() > 0){
				return list;
			}
			return null;
		}
		
		public User findById(Integer id){
			User user = this.getHibernateTemplate().get(User.class, id);
			return user;
		}
		
		@SuppressWarnings("unchecked")
		public int findCount(String userName,Integer userStatus){
			String hql = "select count(*) from User u where 1=1";
			if(userName!=null && !"".equals(userName)){
				hql += " and u.userName like'%"+userName+"%'";
			}
			if(userStatus!=null && !"".equals(userStatus)){
				hql +=" and u.userStatus='"+userStatus+"'";
			}
			List<Long> list = this.getHibernateTemplate().find(hql);
			if(list != null && list.size() > 0){
				return list.get(0).intValue();
			}
			return 0;
		}
		
		public void addUser(User user){
			this.getHibernateTemplate().save(user);
		}
		
		public void updateUser(User user){
			this.getHibernateTemplate().update(user);
		}
		
		public void deleteUser(User user){
			this.getHibernateTemplate().delete(user);
		}
		
		@SuppressWarnings("unchecked")
		public User findByName(String userName){
			String hql = "from User where userName=?";
			List<User> list = this.getHibernateTemplate().find(hql,userName);
			if(list != null && list.size() > 0){
				return list.get(0);
			}
			return null;
		}
}
