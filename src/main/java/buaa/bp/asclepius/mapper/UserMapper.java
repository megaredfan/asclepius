package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.User;

public interface UserMapper {
	public List<User> getAllUsers();
	public User getUserById(long id);
	public int createUser(User user);
	public int updateUser(User user);
	public int deleteUser(long id);
	public User getUserByName(String name);
}
