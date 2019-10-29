package com.smbms.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smbms.entity.Provider;
import com.smbms.service.ProviderService;
import com.smbms.util.Page;

@Controller
public class ProviderController {
	@Resource
	private ProviderService ps;
	//显示所有供应商
	@RequestMapping("/sys/provider")
	public String findAll(@RequestParam(value="queryProCode",required=false)String queryProCode,
			@RequestParam(value="queryProName",required=false)String queryProName,		
			@RequestParam(value="pageIndex", required=false)Integer currentPage,Model model){
		if(currentPage == null){
			currentPage = 1;
		}
		Page page = new Page();
		page.setSize(5);
		int totalCount = ps.findProCount(queryProCode, queryProName);
		page.setTotalCount(totalCount);		
		if (page.getTotalPage() > 0) {
			if (currentPage >= page.getTotalPage()) {
				currentPage = page.getTotalPage();
			}
		}
		page.setCurrentPage(currentPage);
		List<Provider> proList = ps.findAll(queryProCode, queryProName,currentPage,page.getSize());	
		model.addAttribute("page", page);
		model.addAttribute("proList", proList);
		model.addAttribute("queryProCode", queryProCode);
		model.addAttribute("proName", queryProName);
		return "/pro/providerlist";
	}
	//添加供应商页面
	@RequestMapping("/sys/provideradd")
	public String addProvider(){	
		return "/pro/provideradd";
	}
	//保存添加信息
	@RequestMapping("/sys/provideraddsave")
	public String saveAdd( Provider provider){
		if(ps.addProvider(provider)){
			return "redirect:/sys/provider";
		}
		return "/pro/provideradd";
	}
	//显示供应商信息
	@RequestMapping("/sys/proview")
	public String viewProvider(@RequestParam(value="proid",required=false)Integer proid,Model model){
		Provider provider = ps.viewProvider(proid);
		model.addAttribute("provider", provider);
		return "/pro/providerview";
	}
	//修改供应商信息页面
	@RequestMapping("/sys/providermodify")
	public String modifyProvider(@RequestParam(value="proid",required=false)Integer proid,Model model){
		Provider provider = ps.viewProvider(proid);
		model.addAttribute("provider", provider);
		return "/pro/providermodify";
	}
	//保存修改信息
	@RequestMapping("/sys/providermodifysave")
	public String saveModify(Provider provider,@RequestParam(value="proid",required=false)Integer proid){
		provider.setId(ps.viewProvider(proid).getId());
		if(ps.modifyProvider(provider)){
			return "redirect:/sys/provider";
		}
		return "/pro/providermodify";
	}
	//删除供应商
	@RequestMapping("/sys/delprovider")
	@ResponseBody
	public Object deletePro(@RequestParam(value="proid") Integer proid){
		HashMap<String, String> map = new HashMap<>();
		Integer proCount = ps.findCount(proid);	//查询该供应商是否有订单
		if(proCount>0){
			map.put("delResult", String.valueOf(proCount));		
		}else{
			if(ps.deletePro(proid)){
				map.put("delResult", "true");
			}else{
				map.put("delResult", "false");
			}
		}
		return map;
	}
}
