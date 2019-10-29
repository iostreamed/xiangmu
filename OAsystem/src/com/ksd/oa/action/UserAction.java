package com.ksd.oa.action;

import java.util.Date;
import java.util.List;
import java.util.Map;






import javax.servlet.http.HttpSession;

import com.ksd.oa.entity.User;
import com.ksd.oa.service.UserService;
import com.ksd.oa.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	public User getModel() {
		return user;
	}
	
	private String checkcode;
	
	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
		
	private List<User> userlist;
	
	
	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
	
	private int page;
	
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	private String oldPassword;
	private String newPassword;
	private String rePassword;
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String login(){
		return "login";
	}
	public String doLogin(){
		ActionContext ac = ActionContext.getContext();
		String checkcode1 = (String) ac.getSession().get("checkcode");				
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			this.addActionError("验证码输入错误！");
			return "login_input";
		}
		User user1 = userService.login(user);
		if(user1 != null){
			Map<String,Object> map = ac.getSession();
			Date lastLoginTime = user1.getLoginTime();
			user1.setLoginTime(new Date());
			userService.updateUser(user1);
			user1.setLoginTime(lastLoginTime);
			map.put("user", user1);
			return "login_success";
		}else{
			this.addActionError("登录失败：用户名密码不正确");
			return "login_input";
		}
	}
	
	public String personal(){
		ActionContext ac = ActionContext.getContext();
		ac.getSession().get("user");
		return "personal";
	}
	
	public String logout(){
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	
	public String resetPwd(){
		return "resetPwd";
	}
	
	public String saveNewPwd(){
		User user1=(User) ActionContext.getContext().getSession().get("user");
		if(!oldPassword.equals(user1.getPassword())){
			this.addActionError("原密码输入错误！");
		}else{
			if(newPassword.equals(rePassword)){
				user1.setPassword(newPassword);
				userService.updateUser(user1);
				ActionContext.getContext().getSession().remove("user");
				return "save_success";
			}
		}
		return "resetPwd";
	}
	public String userlist(){
		PageBean<User> userlist = userService.findAll(user.getUserName(),user.getUserStatus(),page);
		ActionContext.getContext().getSession().put("userlist", userlist);
		return"userlist";
	}
	
	public String addUser(){
		return"addUser";
	}
	
	public String saveUser(){
		User user1=userService.findByName(user.getUserName());
		if(user1!=null){
			this.addActionError("用户账号已存在！");
			return "addUser";
		}else{
			
			user1=new User(null, user.getUserName(), user.getPassword(), user.getUserType(),user.getUserStatus(), new Date(), user.getModifyTime(), user.getLoginTime(), user.getNikeName());
			userService.addUser(user1);
			return "saveUser";
		}	
	}
	
	public String deleteUser(){
		User user1 = userService.findById(user.getId());
		if(user1 != null){
			userService.deleteUser(user1);
			return "deleteUser";
		}
		return "deleteFail";
	}
	
	public String updateUser(){
		User user1 = userService.findById(user.getId());
		ActionContext.getContext().getSession().put("User", user1);
		return "updateUser";
	}
	
	public String saveUpdate(){
		 User user1 = (User) ActionContext.getContext().getSession().get("User");
		 if(user1 != null){
			user1.setModifyTime(new Date());
			user1.setUserType(user.getUserType());
			user1.setUserStatus(user.getUserStatus());
			user1.setNikeName(user.getNikeName());
			userService.updateUser(user1);
			return "saveUpdate";
		}
		return "updateUser";
	}
	
	public String updateStatus(){
		User user1 = userService.findById(user.getId());
		if(user1 != null){
			if(user1.getUserStatus()==1){
				user1.setUserStatus(2);
				userService.updateUser(user1);
				return "updateStatus";
				
			}else{
				user1.setUserStatus(1);
				userService.updateUser(user1);
				return "updateStatus";
			}
		}
		return "updateFail";
	}
	
	public String rePassword(){
		User user1 = userService.findByName(user.getUserName());
		if(user1 != null){
			user1.setPassword("123456");
			userService.updateUser(user1);
			return "rePassword";
		}
		this.addActionError("未找到用户，重置密码失败");
		return "rePasswordFail";
	}
}
