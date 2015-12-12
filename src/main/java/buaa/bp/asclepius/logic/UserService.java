package buaa.bp.asclepius.logic;

import java.security.MessageDigest;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.mapper.UserMapper;
import buaa.bp.asclepius.model.User;

@Service
public class UserService extends GeneralService {

	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}
	public User getUserById(long id) {
		return userMapper.getUserById(id);
	}
	public int createUser(User user) {
		user.setPassword(this.getMD5(user.getPassword()));
		return userMapper.createUser(user);
	}
	public int updateUser(User user) {
		user.setPassword(this.getMD5(user.getPassword()));
		return userMapper.updateUser(user);
	}
	public int deleteUser(long id) {
		return userMapper.deleteUser(id);
	}
	public User getUserByName(String name) {
		return userMapper.getUserByName(name);
	}
	public int count() {
		return userMapper.count();
	}
	public List<?> selectByRange(int start,int length) {
		return userMapper.selectByRange(start, length);
	}
	public void generateList(HttpServletRequest request,HttpServletResponse response,ModelAndView m,String listName){
		super.generateList(request, response,m,listName);
	}
	/**
	 * 验证用户名密码并且返回用户权限值
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean authentication(String name,String password){
		User user = getUserByName(name);
		if(user == null || StringUtils.isBlank(password)){
			return false;
		}
		String passWordMD5 = this.getMD5(password);
		if( user.getPassword().equals(passWordMD5)==true && user.getActiveFlag()==1){
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
	public static void main(String[] args){
		System.out.println(new UserService().getMD5("123456"));
	}
}
