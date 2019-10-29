package cn.itcast.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.utils.Page;
import cn.itcast.core.bean.User;
import cn.itcast.core.dao.UserDao;
import cn.itcast.core.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserDao userdao;
	
	
	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
       userdao.register(user);
	}
	
	@Override
	public User login(String username,String userpassword) {
		 User user = userdao.login(username);
		 if(user != null && user.getUserpassword().equals(userpassword)) {
			 return user;
		 }
		 
		 return null;
	}

	@Override
	public void updateUser(User user) {
		userdao.updateUser(user);
	}
	
	@Override
	public Page<User> selectUserList(Integer page, Integer rows) {
		User user = new User();
		user.setStart((page-1) * rows);
		user.setRows(rows);
		List<User> userList = userdao.selectUserList(user);
		Integer count = userdao.selectUserListCount(user);
		Page<User> result = new Page<>();
		result.setPage(page);
		result.setRows(userList);
		result.setSize(rows);
		result.setTotal(count);
		return result;
	}

	@Override
	public User getUserById(Integer id) {
		User user = userdao.getUserById(id);
		return user;
	}

	@Override
	public void delete(Integer id) {
		userdao.delete(id);
	}

	@Override
	public User selectUserByUsername(String username) {
		User user = userdao.login(username);
		if(user != null){
			return user;
		}
		return null;
	}


}
