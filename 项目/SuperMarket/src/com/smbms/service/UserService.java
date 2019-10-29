package com.smbms.service;

import java.util.List;


import com.smbms.entity.Role;
import com.smbms.entity.User;

public interface UserService {
	User login(String usercode, String password);
	List<Role> findRole();
	List<User> findAll(String queryname,Integer queryUserRole,int start, int size);
	int findCount(String username,Integer userRole);
	boolean addUser(User user);
	User findUserCode(String usercode);
	User ViewUser(Integer id);
	boolean modifyUser(User user);
	boolean deleteUser(Integer id);
	boolean modifyPwd(Integer id,String password);
	boolean addImage(User user);
}
