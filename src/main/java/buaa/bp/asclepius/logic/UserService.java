package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import buaa.bp.asclepius.mapper.UserMapper;
import buaa.bp.asclepius.model.User;

@Service
public class UserService {

	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}
	public User getUserById(int id) {
		return userMapper.getUserById(id);
	}
	public int createUser(User user) {
		return userMapper.createUser(user);
	}
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}
	public int deleteUser(int id) {
		return userMapper.deleteUser(id);
	}
}
