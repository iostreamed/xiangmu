package cn.itcast.core.service;

import java.util.List;
import java.util.Map;

import cn.itcast.common.utils.Page;
import cn.itcast.core.bean.Customer;



public interface CustomerService {

	// 查询客户列表
	public Page<Customer> findCustomerList(Integer page, Integer rows, 
			String custName,  String custSource,String custIndustry,String custLevel);

	public Customer getCustomerById(Long id);

	public void updateCustomer(Customer customer);

	public void deleteCustomer(Long[] ids);
	
	public void insert(Customer customer);
	
	public List<Customer> getCustomerForExcel();
	
	public void importCustomerForExcel(List<Customer> customerList);
	
	public Map<String, Object> ststisticsCustomer();
}
