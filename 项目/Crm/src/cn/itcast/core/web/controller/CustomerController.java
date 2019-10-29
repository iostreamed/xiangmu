package cn.itcast.core.web.controller;

import cn.itcast.common.utils.ExportExcel; 
import cn.itcast.common.utils.ImportsExcel;
import cn.itcast.common.utils.Page;
import cn.itcast.common.utils.TimeUtil;
import cn.itcast.core.bean.BaseDict;
import cn.itcast.core.bean.Customer;
import cn.itcast.core.service.CustomerService;
import cn.itcast.core.service.SystemService;

import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * <p>Title: CustomerController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p>
 * @version 1.0
 */
@Controller
public class CustomerController {


	@Autowired
	private CustomerService customerService;
	@Autowired
	private SystemService systemService;
	@Value("${customer.from.type}")
	private String FROM_TYPE;
	@Value("${customer.industry.type}")
	private String INDUSTRY_TYPE;
	@Value("${customer.level.type}")
	private String LEVEL_TYPE;
	
	// 主页
	@RequestMapping(value = "/customer/list.action")
	public String list(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="10")Integer rows,
					   String custName, String custSource,	String custIndustry, String custLevel, Model model) {

		Page<Customer> customers = customerService.findCustomerList(page, rows, custName, custSource, custIndustry,
				custLevel);
		model.addAttribute("page", customers);

		List<BaseDict> fromType = systemService.findBaseDictListByType(FROM_TYPE);

		List<BaseDict> industryType = systemService.findBaseDictListByType(INDUSTRY_TYPE);

		List<BaseDict> levelType = systemService.findBaseDictListByType(LEVEL_TYPE);
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);

		model.addAttribute("custName", custName);
		model.addAttribute("custSource", custSource);
		model.addAttribute("custIndustry", custIndustry);
		model.addAttribute("custLevel", custLevel);
		return "customer";
	}

	//去修改界面
	@RequestMapping("/customer/edit")
	@ResponseBody
	public Customer getCustomerById(Long id) {
		Customer customer = customerService.getCustomerById(id);
		return customer;
	}

	//更新修改界面数据
	@RequestMapping("/customer/update")
	@ResponseBody
	public String customerUpdate(Customer customer) {
		customerService.updateCustomer(customer);
		return "OK";
	}
	// 删除
	@RequestMapping("/customer/delete")
	@ResponseBody
	public String customerDelete(@RequestParam Long[] ids) {	
		try {
			customerService.deleteCustomer(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "OK";
	}

	// 执行添加操作
	@RequestMapping("/customer/insert")
	public String insert(Customer customer) {
		customerService.insert(customer);
		return "redirect:/customer/list.action";
	}
	
	@RequestMapping(value="/customer/export")
	public void exportExcel(HttpServletResponse response) throws Exception{
		List<Customer> customerList = null;
		try {
			customerList = customerService.getCustomerForExcel();
			for (int i = 0; i < customerList.size(); i++) {
				customerList.get(i).setCust_id((long) (i + 1));
			}
			String[] headers = { "序号", "客户名", "客户来源", "所属行业", "客户级别","联系人","固定电话","移动电话","邮政编码","联系地址" };
			String[] columns = { "cust_id", "cust_name", "cust_source", "cust_industry","cust_level" ,"cust_linkman","cust_phone","cust_mobile","cust_zipcode","cust_address"};
			String filename = TimeUtil.getDate(null, 3) + "客户信息表.xls";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(filename.getBytes("utf-8"), "ISO8859-1"));
			OutputStream out;
			out = response.getOutputStream();
			ExportExcel<Customer> ex = new ExportExcel<Customer>();
			ex.exportExcel1(filename, headers, columns, customerList, out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@RequestMapping(value="customer/import")
	public String importExcel(HttpServletRequest request) throws Exception{
		String custSource = "";
		String custIndustry = "";
		String custLevel = "";
		try {
			MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
			MultipartFile file = multipart.getFile("file");
			InputStream in;
			in = multipart.getInputStream();
			List<Customer> customerList = new ArrayList<>();
			List<List<Object>> list = ImportsExcel.readExcel(file);
			for(int i=0;i<list.size();i++){
				List<Object> list1 = list.get(i);
				Customer customer = new Customer();
				customer.setCust_id(null);
				
				customer.setCust_name(String.valueOf(list1.get(1)));
				if(String.valueOf(list1.get(2)).equals("电话营销")){
					custSource = "6";
				}else if(String.valueOf(list1.get(2)).equals("网络营销")){
					custSource = "7";
				}
				customer.setCust_source(custSource);
				if(String.valueOf(list1.get(3)).equals("教育培训")){
					custIndustry = "1";
				}else if(String.valueOf(list1.get(3)).equals("电子商务")){
					custIndustry = "2";
				}else if(String.valueOf(list1.get(3)).equals("对外贸易")){
					custIndustry = "3";
				}else if(String.valueOf(list1.get(3)).equals("酒店旅游")){
					custIndustry = "4";
				}else if(String.valueOf(list1.get(3)).equals("房地产")){
					custIndustry = "5";
				}
				customer.setCust_industry(custIndustry);
				if(String.valueOf(list1.get(4)).equals("普通客户")){
					custLevel = "22";
				}else if(String.valueOf(list1.get(4)).equals("VIP客户")){
					custLevel = "23";
				}
				customer.setCust_level(custLevel);
				customer.setCust_linkman(String.valueOf(list1.get(5)));
				customer.setCust_phone(String.valueOf(list1.get(6)));
				customer.setCust_mobile(String.valueOf(list1.get(7)));
				customerList.add(customer);
			}
			customerService.importCustomerForExcel(customerList);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/customer/list.action";
	}
	@RequestMapping(value="/customer/statistics")
	public ModelAndView statisticsSource(){
		return new ModelAndView("statistics","map",customerService.ststisticsCustomer());
	}
}