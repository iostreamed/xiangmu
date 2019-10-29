package com.smbms.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;





import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.StringUtils;
import com.smbms.entity.Role;
import com.smbms.entity.User;
import com.smbms.service.UserService;
import com.smbms.util.Page;

@Controller
public class UserController {
	@Resource
	private UserService us;
	//登录页面
	@RequestMapping("/login")
	public String login(){		
		return "login";
	}
	//登录操作，登录成功跳转到frame.jsp页面
	@RequestMapping(value="/dologin", method=RequestMethod.POST)
	public String doLogin(HttpSession session,@RequestParam String usercode,@RequestParam String password){
		User user = us.login(usercode, password);
		if(user != null){
			session.setAttribute("user", user);
			return "redirect:/sys/frame";
		}
		session.setAttribute("error", "用户名或密码错误");
		return "redirect:/login";
	}
	@RequestMapping("/sys/frame")
	public String frame(){		
		return "frame";
	}
	//退出登录
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return "redirect:/login";
	}
	//显示所有用户信息
	@RequestMapping(value="/sys/user",method=RequestMethod.GET)
	public String userlist(Model model,@RequestParam(value="pageIndex", required=false) Integer currentPage ,@RequestParam(value="queryname",required=false) String queryname,@RequestParam(value="queryUserRole",required=false) Integer queryUserRole){
		
		if(currentPage == null){
			currentPage = 1;
		}
		Page page = new Page();
		page.setSize(5);
		if(queryUserRole != null && queryUserRole == 0){
			queryUserRole = null;
		}
		int totalCount = us.findCount(queryname, queryUserRole);
		page.setTotalCount(totalCount);
		if (page.getTotalPage() > 0) {
			if (currentPage >= page.getTotalPage()) {
				currentPage = page.getTotalPage();
			}
		}
		page.setCurrentPage(currentPage);
		List<User> userList = us.findAll(queryname, queryUserRole, currentPage, page.getSize());
		List<Role> roleList = us.findRole();
		System.out.println(userList.size());
		model.addAttribute("roleList", roleList);
		model.addAttribute("userList",userList);
		model.addAttribute("page",page);
		model.addAttribute("queryname", queryname);
		model.addAttribute("queryUserRole", queryUserRole);
		return "/user/userlist";
	}
	//添加用户
	@RequestMapping(value="/sys/useradd")
	public String addUser(){		
		return "/user/useradd";
	}
	//保存添加信息
	@RequestMapping(value="/sys/saveuser",method=RequestMethod.POST)
	public String saveAddUser(User user){
		
		if(us.addUser(user)){
			return "redirect:/sys/user";
		}
		return "/user/useradd";
	}
	//获取所有角色
	@RequestMapping(value="sys/getrolelist",method=RequestMethod.GET)
	@ResponseBody
	public Object getRoleList(){
		List<Role> roleList = us.findRole();		
		return roleList;
	}
	//验证用户是否存在
	@RequestMapping(value="/sys/usercode")
	@ResponseBody
	public Object codeIsExit(@RequestParam(value="userCode" ,required=false) String userCode){
		HashMap<String, String> map = new HashMap<>();
		if(StringUtils.isNullOrEmpty(userCode)){	//用户编码是否为空
			map.put("userCode", "exist");
		}else{
			User user = us.findUserCode(userCode);
			if(user != null){
				map.put("userCode", "exist");
			}else{
				map.put("userCode", "noexist");
			}
		}		
		return map;
	}
	//显示用户信息
	@RequestMapping("/sys/viewUser")
	public String ViweUser(@RequestParam(value="uid",required=false) Integer uid,Model model){
		User user = us.ViewUser(uid);
		model.addAttribute("User", user);
		return "/user/userview";
	}
	//修改用户信息
	@RequestMapping("/sys/modifyUser")
	public String modifyUser(@RequestParam(value="uid",required=false) Integer uid,Model model){
		User user = us.ViewUser(uid);
		model.addAttribute("User", user);
		return "/user/usermodify";
	}
	//保存修改信息
	@RequestMapping(value="/sys/modifyusersave",method=RequestMethod.POST)
	public String saveModify(User user,@RequestParam(value="uid",required=false) Integer uid) {
		user.setId(us.ViewUser(uid).getId());
		if(us.modifyUser(user)){
			return "redirect:/sys/user";
		}
		return "/user/usermodify";
	}
	//删除用户
	@RequestMapping("/sys/deleteUser")
	@ResponseBody
	public Object deleteUser(@RequestParam Integer uid){
		HashMap<String, String> map = new HashMap<>();
		if(us.deleteUser(uid)){
			map.put("delResult", "true");
		}else{
			map.put("delResult", "false");
		}
		return map;
	}
	//修改密码
	@RequestMapping("/sys/pwdmodify")
	public String modifyPwd(){		
		return "/user/pwdmodify";
	}
	//保存修改后的密码
	@RequestMapping(value="/sys/savepwdmodify", method=RequestMethod.POST)
	public String saveModifyPwd(@RequestParam(value="newpassword") String newpassword,HttpSession session,HttpServletRequest request){
		Object obj = session.getAttribute("user");
		if(us.modifyPwd(((User)obj).getId(), newpassword)){
			request.setAttribute("message", "密码修改成功");
			session.removeAttribute("user");
			return "redirect:/login";
		}else{
			request.setAttribute("message", "密码修改失败");
		}
		return "/user/pwdmodify";
	}
	//验证密码是否合法
	@RequestMapping(value="/sys/checkpwd")
	@ResponseBody
	public Object checkPwd(@RequestParam(value="oldpassword") String oldpassword,HttpSession session){
		HashMap<String, String> map = new HashMap<>();
		if(session.getAttribute("user") == null){
			map.put("result", "sessionerror");
		}else if(StringUtils.isNullOrEmpty(oldpassword)){
			map.put("result", "error");
		}else{
			String sessionPwd = ((User)session.getAttribute("user")).getPassword();
			if(sessionPwd.equals(oldpassword)){
				map.put("result", "true");
			}else{
				map.put("result", "false");
			}			
		}
		return map;
	}
	//个人中心页面
	@RequestMapping("/sys/person")
	public String ViewPerson(HttpSession session) {
		session.getAttribute("user");
		return "/user/person";
	}
	//上传照片
	@RequestMapping("/sys/pictureadd")
	public String pictureAdd() {
		return "/user/pictureadd";
	}
	//保存照片
	@RequestMapping(value = "/sys/savepicture",method=RequestMethod.POST)
	public String savePicture(
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "attachs", required = false) MultipartFile attach
			)throws ParseException {
		String fileName = null;
		User user = (User) session.getAttribute("user");
		String errorInfo = "uploadWpError";
		boolean flag = true;
		String path = request.getSession().getServletContext()
				.getRealPath("statics" + File.separator + "uploadfiles");
		if (!attach.isEmpty()) {
			String oldFileName = attach.getOriginalFilename();
			String prefix = FilenameUtils.getExtension(oldFileName);
			int filesize = 500000;
			if (attach.getSize() > filesize) {// 上传大小不得超过 500k
				request.setAttribute(errorInfo, " * 上传大小不得超过 500k");
				flag = false;
			} else if (prefix.equalsIgnoreCase("jpg")
					|| prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg")
					|| prefix.equalsIgnoreCase("pneg")) {// 上传图片格式不正确
				 	fileName = System.currentTimeMillis()
						+ RandomUtils.nextInt(filesize, 1000000)
						+ "."+prefix;
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile); 
				} catch (IllegalStateException e) {
					e.printStackTrace();
					request.setAttribute(errorInfo, " * 上传失败！");
					flag = false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				request.setAttribute(errorInfo, " * 上传图片格式不正确");
				flag = false;
			}
		}
		System.out.println(flag);
		if (flag) {
			
			user.setImage(fileName);
			if(us.addImage(user)){
				return "redirect:/sys/person";
			}
		}
		return "/user/pictureadd";
	}
}


