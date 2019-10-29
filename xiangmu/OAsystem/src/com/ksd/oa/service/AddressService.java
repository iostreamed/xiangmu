package com.ksd.oa.service;

import java.util.List;

import com.ksd.oa.dao.AddressDao;
import com.ksd.oa.entity.Address;
import com.ksd.oa.entity.User;
import com.ksd.oa.util.PageBean;

public class AddressService {
	
	private AddressDao addressDao;

	public AddressDao getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}
	
	public PageBean<Address> findByName(String userName,String name,String email,int page){
		PageBean<Address> pageBean = new PageBean<Address>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = addressDao.findCount(userName,name, email);
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
		List<Address> list = addressDao.findByUserName(userName, name, email, start, limit);
		pageBean.setList(list);
		return pageBean;		
	}
	
	public void addAddress(Address address){
		this.addressDao.addAddress(address);
	}
	
	public Address findByEmail(String email){
		return addressDao.findByEmail(email);
	}
	
	public void deleteAddress(Address address){
		this.addressDao.deleteAddress(address);
	}
	
	public Address findById(Integer id){
		return addressDao.findById(id);
	}
	
	public void updateAddress(Address address){
		this.addressDao.updateAddress(address);
	}
}
