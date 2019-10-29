package cn.itcast.core.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import cn.itcast.common.utils.Page;
import cn.itcast.core.bean.Visit;
import cn.itcast.core.service.VisitService;

@Controller
public class VisitController {
	@Autowired
	private VisitService visitService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		
		//转换日期
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
	}
	@RequestMapping("/visit/list")
	public String list(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer rows,String visit_interviewer, Model model){
		Page<Visit> visitlist = visitService.selectVisitList(page, rows, visit_interviewer);
		model.addAttribute("page",visitlist);
		model.addAttribute("visit_interviewer", visit_interviewer);
		return "visit";
	}
	@RequestMapping("/visit/delete")
	@ResponseBody
	public String deleteVisit(Integer id){
		visitService.deleteVisit(id);
		return "OK";
	}
	@RequestMapping("/visit/insert")
	public String addVisit(Visit visit){
		visitService.insertVisit(visit);
		return "redirect:/visit/list.action";
	}
}
