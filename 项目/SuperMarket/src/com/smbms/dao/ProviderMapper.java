package com.smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.entity.Provider;

public interface ProviderMapper {	
	//查询所有供应商
	List<Provider> findAll(@Param("proCode")String proCode,@Param("proName")String proName,@Param("start")int satrt,@Param("size")int size);
	//查询所有供应商个数，用于分页
	int findProCount(@Param("proCode")String proCode,@Param("proName")String proName);
	//添加供应商
	int addProvider(Provider provider);
	//显示供应商信息
	Provider viewProvider(Integer id);
	//修改供应商信息
	int modifyProvider(Provider provider);
	//查询供应商下是否有订单，判断供应商是否可以删除
	int findCount(Integer id);
	//删除供应商
	int deletePro(Integer id);
}
