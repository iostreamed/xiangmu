package com.smbms.service;

import java.util.List;

import com.smbms.entity.Bill;
import com.smbms.entity.Provider;

public interface BillService {
	List<Bill> findAll(String productName,Integer providerId,Integer isPayment,int start,int size);
	List<Provider> findProName();
	int findCount(String productName,Integer providerId,Integer isPayment);
	boolean addBill(Bill bill);	
	Bill viewBill(Integer id);
	boolean modifyBill(Bill bill);
	boolean deleteBill(Integer id);
}
