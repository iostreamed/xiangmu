package cn.itcast.core.dao;

import java.util.List;

import cn.itcast.core.bean.User;

public interface UserDao {
	
	List<User> selectUserList(User user);
	
	Integer selectUserListCount(User user);
	
	User getUserById(Integer id);
  
	void register(User user);
	
	User login(String username);
	
	void updateUser(User user);
	
	void delete(Integer id);

}
