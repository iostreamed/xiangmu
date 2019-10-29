package cn.itcast.core.service;

import cn.itcast.common.utils.Page;
import cn.itcast.core.bean.User;

public interface UserService {
	
	public Page<User> selectUserList(Integer page,Integer rows);
	
	public User getUserById(Integer id);
	
	public void register(User user);
	
	public  User login(String username,String userpassword);

	public void updateUser(User user);
	
	public void delete(Integer id);
	
	public User selectUserByUsername(String username);
}
