package cn.itcast.core.dao;

import java.util.List;





import org.apache.ibatis.annotations.Param;

import cn.itcast.core.bean.Customer;

public interface CustomerDao {

	List<Customer> selectCustomerList(Customer customer);
	Integer selectCustomerListCount(Customer customer);
	Customer getCustomerById(Long id);
	void updateCustomer(Customer customer);
	void deleteCustomer(Long[] ids);
	void insert(Customer customer);
	List<Customer> getCustomerForExcel();
	void importCustomerForExcel(@Param("list")List<Customer> customerList);
	List<Object> statisticsCustomer();
	List<Object> statisticsIndustry();
}