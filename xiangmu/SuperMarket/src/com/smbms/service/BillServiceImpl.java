package com.smbms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smbms.dao.BillMapper;
import com.smbms.entity.Bill;
import com.smbms.entity.Provider;
@Service
@Transactional
public class BillServiceImpl implements BillService{
	@Resource
	private BillMapper bm;
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Bill> findAll(String productName, Integer providerId,
			Integer isPayment,int start,int size) {
		start=(start-1)*size;
		List<Bill> list = bm.findAll(productName, providerId, isPayment,start,size);			
		return list;
	}
	@Override
	public List<Provider> findProName() {
		List<Provider> list = bm.findProName();
		return list;
	}
	@Override
	public boolean addBill(Bill bill) {
		boolean flag = false;
		int i = bm.addBill(bill);
		if(i>0){
			flag = true;
		}
		return flag;
	}
	@Override
	public Bill viewBill(Integer id) {
		Bill bill = bm.viewBill(id);
		return bill;
	}
	@Override
	public boolean modifyBill(Bill bill) {
		boolean flag = false;
		int i = bm.modifyBill(bill);
		if(i>0){
			flag = true;
		}
		return flag;
	}
	@Override
	public boolean deleteBill(Integer id) {
		boolean flag = false;
		int i = bm.deleteBill(id);
		if(i>0){
			flag = true;
		}
		return flag;
	}
	@Override
	public int findCount(String productName, Integer providerId,
			Integer isPayment) {
		
		return bm.findCount(productName, providerId, isPayment);
	}			
}
