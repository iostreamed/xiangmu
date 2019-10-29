package com.smbms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.entity.Role;
import com.smbms.entity.User;

public interface UserMapper {
	User login(String usercode);	//用户登录方法
	List<Role> findRole();     		//查询用户角色
	//查所有用户
	List<User> findAll(@Param("username") String queryname,@Param("userRole")Integer queryUserRole,@Param("start") int start,@Param("size") int size);
	//查询用户个数
	int findCount(@Param("username") String username,@Param("userRole") Integer userRole);
	int addUser(User user);			//添加用户
	User findUserCode(@Param("usercode") String usercode); //查询用户编码（账户）用来确定用户是否存在
	User ViewUser(Integer id);  	//显示用户所有信息
	int modifyUser(User user);		//修改用户信息
	int deleteUser(Integer id);		//删除用户
	//修改密码
	int modifyPwd(@Param("id") Integer id,@Param("password") String password); 
	int addImage(User user);		//上传照片
}
