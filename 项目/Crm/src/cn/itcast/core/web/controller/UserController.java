package cn.itcast.core.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.itcast.common.utils.Page;
import cn.itcast.core.bean.User;
import cn.itcast.core.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	//登录页面
	@RequestMapping(value="/user/gologin")
	public String gologin(HttpSession session) {
		session.invalidate();
		return "login";
	}
	//注册
	@RequestMapping(value="/user/register")
	public String RegisterableService(User user,HttpServletRequest request) {
		user.setUserrole("普通员工");
		if(userService.selectUserByUsername(user.getUsername())!=null){
			request.setAttribute("message", "账号已存在");
			return "login";
		}
		userService.register(user);
		return "login";
	}
	//登录实现代码
	@RequestMapping(value="/user/login")
	public String LoginableService(User user,HttpSession session) {
		user = userService.login(user.getUsername(), user.getUserpassword());

		if(user != null) {
			session.setAttribute("user", user);
			return "redirect:/customer/statistics.action";
		}
		return "login";
	}
	
	@RequestMapping("/user/list")
	public String list(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer rows,Model model){
		Page<User> userlist = userService.selectUserList(page, rows);
		model.addAttribute("page",userlist);	
		return "user";
	}
	@RequestMapping("/user/delete")
	@ResponseBody
	public String deleteUser(Integer id){
		userService.delete(id);
		return "OK";
	}
	
	@RequestMapping(value="/user/update")
	public String updateUser(HttpSession session,@RequestParam(value="username")String username,@RequestParam(value="nickname")String nickname){
		User user = (User) session.getAttribute("user");
		user.setUsername(username);
		user.setNickname(nickname);
		userService.updateUser(user);
		return "redirect:/user/person.action";
	}
	
	@RequestMapping(value="/user/person")
	public String person(HttpSession session){
		session.getAttribute("user");
		return "person";
	}
	@RequestMapping(value="/user/uploadImage")
	public String addImage(HttpSession session,MultipartFile file,HttpServletRequest request){
		String fileName = null;
		User user = (User) session.getAttribute("user");
		String errorInfo = "uploadError";
		boolean flag = true;
		String path = request.getSession().getServletContext().getRealPath("image"+File.separator+"upload");
		if(file != null){
			String oldFileName = file.getOriginalFilename();
			String prefix = FilenameUtils.getExtension(oldFileName);
			int filesize = 500000;
			if(file.getSize()>filesize){
				request.setAttribute(errorInfo, " * 上传大小不得超过 500k");
				flag = false;
			}else if (prefix.equalsIgnoreCase("jpg")
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
					file.transferTo(targetFile); 
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
		if (flag) {
			user.setImage(fileName);
			userService.updateUser(user);
			return "redirect:/user/person.action";		
		}
		return "redirect:/user/person.action";
	}
	@RequestMapping("/user/repassword")
	public String upPassword(@RequestParam(value="newpassword")String newpassword,@RequestParam(value="renewpassword")String renewpassword,HttpSession session){
		User user = (User) session.getAttribute("user");
		if(newpassword.equals(renewpassword)){
			user.setUserpassword(newpassword);
			userService.updateUser(user); 
			session.removeAttribute("user");
			return "redirect:/user/gologin.action";
		}
		return "redirect:/customer/statistics.action";
	}
}
