package com.ksd.oa.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ksd.oa.entity.Address;
import com.ksd.oa.entity.User;
import com.ksd.oa.util.PageHibernateCallback;

public class AddressDao extends HibernateDaoSupport{
	
	
	public List<Address> findByUserName(String userName,String name,String email,int start,int limit){
		String hql="from Address a where a.userName=?";
		if(name!=null && !"".equals(name)){
			hql += " and a.name like'%"+name+"%'";
		}
		if(email!=null && !"".equals(email)){
			hql +=" and a.email like '%"+email+"%'";
		}
		List<Address> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Address>(hql,new Object[] {userName} ,start, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	public int findCount(String userName,String name,String email){
		String hql = "select count(*) from Address a where a.userName=?";
		if(name!=null && !"".equals(name)){
			hql += " and a.name like'%"+name+"%'";
		}
		if(email!=null && !"".equals(email)){
			hql +=" and a.email like '%"+email+"%'";
		}
		List<Long> list = this.getHibernateTemplate().find(hql,userName);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	public Address findById(Integer id){
		Address address = this.getHibernateTemplate().get(Address.class, id);
		return address;
	}
	
	public void addAddress(Address address){
		this.getHibernateTemplate().save(address);
	}
	
	public Address findByEmail(String email){
		String hql = "from Address where email=?";
		List<Address> list = this.getHibernateTemplate().find(hql,email);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public void deleteAddress(Address address){
		this.getHibernateTemplate().delete(address);
	}
	
	public void updateAddress(Address address){
		this.getHibernateTemplate().update(address);
	}
}
