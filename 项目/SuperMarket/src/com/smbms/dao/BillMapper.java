package com.smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.entity.Bill;
import com.smbms.entity.Provider;

public interface BillMapper {
	//查询所有订单
	List<Bill> findAll(@Param("productName")String productName,
			@Param("providerId")Integer providerId,
			@Param("isPayment") Integer isPayment,
			@Param("start")int start,@Param("size")int size);
	//查询供应商名称
	List<Provider> findProName();
	//查询订单总数，用于分页
	int findCount(@Param("productName")String productName,
			@Param("providerId")Integer providerId,
			@Param("isPayment") Integer isPayment);
	//添加订单
	int addBill(Bill bill);
	//显示订单信息
	Bill viewBill(Integer id);
	//修改订单
	int modifyBill(Bill bill);
	//删除订单
	int deleteBill(Integer id);
}
