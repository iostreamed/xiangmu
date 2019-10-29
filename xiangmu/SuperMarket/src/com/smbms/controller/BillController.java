package com.smbms.controller;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;
import com.smbms.entity.Bill;
import com.smbms.entity.Provider;
import com.smbms.service.BillService;
import com.smbms.util.Page;

@Controller
public class BillController {
	@Resource
	private BillService bs;
	//显示所有订单信息
	@RequestMapping("/sys/bill")
	public String billList(@RequestParam(value="pageIndex", required=false)Integer currentPage,Model model,
			@RequestParam(value="productName",required=false) String productName,
			@RequestParam(value="providerId", required=false) Integer providerId,
			@RequestParam(value="isPayment", required=false) Integer isPayment){
		if(currentPage == null){
			currentPage = 1;
		}
		Page page = new Page();
		page.setSize(5);
		if(providerId != null && providerId==0){
			providerId = null;
		}
		if(isPayment != null && isPayment==0){
			isPayment = null;
		}
		int totalCount = bs.findCount(productName, providerId, isPayment);	
		page.setTotalCount(totalCount);
		if (page.getTotalPage() > 0) {
			if (currentPage >= page.getTotalPage()) {
				currentPage = page.getTotalPage();
			}
		}
		page.setCurrentPage(currentPage);
		List<Bill> billList = bs.findAll(productName, providerId, isPayment,currentPage,page.getSize());	
		List<Provider> providerList = bs.findProName();		
		model.addAttribute("page", page);
		model.addAttribute("billList", billList);
		model.addAttribute("providerList", providerList);
		model.addAttribute("productName", productName);
		model.addAttribute("providerId", providerId);
		model.addAttribute("isPayment", isPayment);
		return "/bill/billlist";
	}
	//添加订单页面
	@RequestMapping("/sys/billadd")
	public String addBill(){		
		return "/bill/billadd";
	}
	//查找供应商
	@RequestMapping("/sys/billselect")
	@ResponseBody
	public Object getProvider(){
		List<Provider> providerList = bs.findProName();
		return providerList;
	}
	//保存添加的信息
	@RequestMapping("/sys/dobilladd")
	public String doBillAdd(Bill bill){
		if(bs.addBill(bill)){
			return "redirect:/sys/bill";
		}
		return "redirect:/sys/billadd";
	}
	//显示订单信息
	@RequestMapping("/sys/billview")	
	public String viewBill(@RequestParam(value="billid",required=false)Integer billid,Model model){
		Bill bill = bs.viewBill(billid);
		model.addAttribute("bill", bill);
		return "/bill/billview";
	}
	//修改订单页面
	@RequestMapping("/sys/billmodify")
	public String modifyBill(@RequestParam(value="billid",required=false)Integer billid,Model model){
		Bill bill = bs.viewBill(billid);
		model.addAttribute("bill", bill);
		return "/bill/billmodify";
	}
	//保存修改信息
	@RequestMapping(value="/sys/dobillmodify",method=RequestMethod.POST)
	public String saveModify(Bill bill,@RequestParam(value="billid",required=false)Integer billid){
		if(bs.modifyBill(bill)){
			return "redirect:/sys/bill";
		}
		return "/bill/billmodify";
	}
	//删除订单
	@RequestMapping("/sys/billdel")
	@ResponseBody
	public Object deleteBill(@RequestParam Integer billid){
		HashMap<String, String> map = new HashMap<>();
		if(StringUtils.isNullOrEmpty(bs.viewBill(billid).getBillCode())){
			map.put("delResult", "noexist");
		}else{
			Bill bill = bs.viewBill(billid);
			if(bill.getIsPayment()==1){
				map.put("delResult", "exist");
			}else{
				if(bs.deleteBill(billid)){
					map.put("delResult", "true");
				}else{
					map.put("delResult", "false");
				}
			}			
		}
		return map;
	}
}
