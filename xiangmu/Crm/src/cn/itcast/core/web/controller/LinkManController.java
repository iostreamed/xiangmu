package cn.itcast.core.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.itcast.common.utils.ExportExcel;
import cn.itcast.common.utils.ImportsExcel;
import cn.itcast.common.utils.Page;
import cn.itcast.common.utils.TimeUtil;
import cn.itcast.core.bean.LinkMan;
import cn.itcast.core.service.LinkManService;

@Controller
public class LinkManController {
	@Autowired
	private LinkManService linkManService;
	
	@RequestMapping("/linkMan/list")
	public String list(@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="10")Integer rows
			,String linkName, Model model){
		Page<LinkMan> linkManlist = linkManService.findLinkManList(page, rows, linkName);
		model.addAttribute("page",linkManlist);
		model.addAttribute("linkName", linkName);
		return "linkman";
	}
	@RequestMapping("/linkMan/edit")
	@ResponseBody
	public LinkMan getLinkManById(Integer id){
		LinkMan linkMan = linkManService.getLinkManById(id);
		return linkMan;
	}
	@RequestMapping("/linkMan/update")
	@ResponseBody
	public String linkManUpdate(LinkMan linkMan){
		linkManService.updateLinkMan(linkMan);
		return "OK";
	}
	@RequestMapping("/linkMan/delete")
	@ResponseBody
	public String linkManDelete(Integer[] ids){
		try {
			linkManService.deleteLinkMan(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return"OK";
	}
	@RequestMapping("/linkMan/insert")
	public String linkManAdd(LinkMan linkMan){
		linkManService.insert(linkMan);
		return "redirect:/linkMan/list.action";
	}
	@RequestMapping("/linkMan/export")
	public void exportExcel(HttpServletResponse response) throws Exception{
		List<LinkMan> linkManList = null;
		try {
			linkManList = linkManService.getLinkManForExcel();
			for(int i = 0;i<linkManList.size();i++){
				linkManList.get(i).setLink_id(i+1);
			}
			String[] headers = { "序号", "联系人", "所属客户", "性别", "手机号码","固定电话" };
			String[] columns = { "link_id", "link_name", "cust_name", "link_sex","link_phone" ,"link_mobile"};
			String filename = TimeUtil.getDate(null, 3) + "联系人信息表.xls";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(filename.getBytes("utf-8"), "ISO8859-1"));
			OutputStream out;
			out = response.getOutputStream();
			ExportExcel<LinkMan> ex = new ExportExcel<LinkMan>();
			ex.exportExcel1(filename, headers, columns, linkManList, out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * Excel文件导入
	 */
	@RequestMapping("/linkMan/import")
	public String importExcel(HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile file = multipart.getFile("file");
		InputStream in;
		in = multipart.getInputStream();
		List<LinkMan> linkManList = new ArrayList<>();
		List<List<Object>> list = ImportsExcel.readExcel(file);
		for(int i=0;i<list.size();i++){
			List<Object> list1 = list.get(i);
			LinkMan linkMan = new LinkMan();
			linkMan.setLink_id(null);
			linkMan.setLink_name(String.valueOf(list1.get(1)));
			linkMan.setCust_name(String.valueOf(list1.get(2)));
			linkMan.setLink_sex(String.valueOf(list1.get(3)));
			linkMan.setLink_phone(String.valueOf(list1.get(4)));
			linkMan.setLink_mobile(String.valueOf(list1.get(5)));
			linkManList.add(linkMan);
		}
		linkManService.importLinkManForExcel(linkManList);
		in.close();
		return "redirect:/linkMan/list.action";
	}
}
