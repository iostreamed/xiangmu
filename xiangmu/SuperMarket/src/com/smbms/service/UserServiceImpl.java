package com.smbms.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smbms.dao.UserMapper;
import com.smbms.entity.Role;
import com.smbms.entity.User;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper um;
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public User login(String usercode, String password) {
		User user = um.login(usercode);
		if(user != null && !user.getPassword().equals(password)){
			user = null;
		}
		return user;
	}

	@Override
	public List<Role> findRole() {
		List<Role> list = um.findRole();
		return list;
	}

	@Override
	public List<User> findAll(String queryname, Integer queryUserRole,int start,int size) {
		start=(start-1)*size;
		List<User> list = um.findAll(queryname, queryUserRole,start,size);
		return list;
	}

	@Override
	public int findCount(String username,Integer userRole) {
		return um.findCount(username,userRole);
	}

	@Override
	public boolean addUser(User user) {
		boolean flag = false;
		int i = um.addUser(user);
		if(i>0){
			flag = true;
			System.out.println("添加成功！！");
		}else{
			System.out.println("添加失败！！");
		}
		return flag;
	}

	@Override
	public User findUserCode(String usercode) {
		User user = um.findUserCode(usercode);
		return user;
	}

	@Override
	public User ViewUser(Integer id) {
		User user = um.ViewUser(id);
		return user;
	}

	@Override
	public boolean modifyUser(User user) {
		boolean flag = false;
		int i = um.modifyUser(user);
		if(i>0){
			flag = true;
			System.out.println("修改成功 ");
		}else{
			System.out.println("修改失败");
		}
		return flag;
	}

	@Override
	public boolean deleteUser(Integer id) {
		boolean flag = false;
		int i = um.deleteUser(id);
		if(i>0){
			flag = true;
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
		return flag;
	}

	@Override
	public boolean modifyPwd(Integer id, String password) {
		boolean flag = false;
		int i = um.modifyPwd(id, password);
		if(i>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean addImage(User user) {
		boolean flag = false;
		int i = um.addImage(user);
		if(i>0){
			flag = true;
		}
		return flag;
	}
}
