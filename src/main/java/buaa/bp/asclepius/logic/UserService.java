package buaa.bp.asclepius.logic;

import java.security.MessageDigest;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
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
	public User getUserByName(String name) {
		return userMapper.getUserByName(name);
	}
	
	/**
	 * 验证用户名密码并且返回用户权限值
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean authentication(String name,String password){
		User admin = getUserByName(name);
		if(admin == null || StringUtils.isBlank(password)){
			return false;
		}
		String passWordMD5 = this.getMD5(password);
		if( admin.getPassword().equals(passWordMD5)==true){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 计算字符串的MD5值
	 * @param password
	 * @return
	 */
	public String getMD5(String password){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			return new String(Base64.encodeBase64(md.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
