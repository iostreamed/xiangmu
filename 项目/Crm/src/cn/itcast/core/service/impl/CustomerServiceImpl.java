package cn.itcast.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.utils.Page;
import cn.itcast.core.bean.Customer;
import cn.itcast.core.dao.BaseDictDao;
import cn.itcast.core.dao.CustomerDao;
import cn.itcast.core.service.CustomerService;
/**
 * 客户管理
 * @author lx
 *
 */

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	// 定义dao属性
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private BaseDictDao baseDictDao;


	public Page<Customer> findCustomerList(Integer page, Integer rows,
										   String custName,  String custSource,String custIndustry,String custLevel) {
		Customer customer = new Customer();
		//判断客户名称(公司名称)
		if(StringUtils.isNotBlank(custName)){
			customer.setCust_name(custName);
		}
		//判断客户信息来源
		if(StringUtils.isNotBlank(custSource)){
			customer.setCust_source(custSource);
		}
		//判断客户所属行业
		if(StringUtils.isNotBlank(custIndustry)){
			customer.setCust_industry(custIndustry);
		}
		//判断客户级别
		if(StringUtils.isNotBlank(custLevel)){
			customer.setCust_level(custLevel);
		}
		//当前页
		customer.setStart((page-1) * rows) ;
		//每页数
		customer.setRows(rows);
		//查询客户列表
		List<Customer> customers = customerDao.selectCustomerList(customer);
		//查询客户列表总记录数
		Integer count = customerDao.selectCustomerListCount(customer);
		//创建Page返回对象
		Page<Customer> result = new Page<>();
		result.setPage(page);
		result.setRows(customers);
		result.setSize(rows);
		result.setTotal(count);
		return result;

	}


	@Override
	public Customer getCustomerById(Long id) {

		Customer customer = customerDao.getCustomerById(id);
		return customer;

	}


	@Override
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);

	}


	@Override
	public void deleteCustomer(Long[] ids) {
		customerDao.deleteCustomer(ids);

	}


	// 添加
	@Override
	public void insert(Customer customer) {
		customerDao.insert(customer);
	}


	@Override
	public List<Customer> getCustomerForExcel() {
		List<Customer> customerList = customerDao.getCustomerForExcel();
		return customerList;
	}


	@Override
	public void importCustomerForExcel(List<Customer> customerList) {
		customerDao.importCustomerForExcel(customerList);
	}


	@Override
	public Map<String, Object> ststisticsCustomer() {
		List<Object> list = customerDao.statisticsCustomer();
		Long datasource = (Long) list.get(0);
		Long datasource1 = (Long) list.get(1);
		List<Object> list1 = customerDao.statisticsIndustry();
		Long dataindustry = (Long) list1.get(0);
		Long dataindustry1 = (Long) list1.get(1);
		Long dataindustry2 = (Long) list1.get(2);
		Long dataindustry3 = (Long) list1.get(3);
		Long dataindustry4 = (Long) list1.get(4);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datasource", datasource);
		map.put("datasource1", datasource1);
		map.put("dataindustry", dataindustry);
		map.put("dataindustry1", dataindustry1);
		map.put("dataindustry2", dataindustry2);
		map.put("dataindustry3", dataindustry3);
		map.put("dataindustry4", dataindustry4);
		return map;
	}
}
