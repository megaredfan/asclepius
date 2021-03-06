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

import buaa.bp.asclepius.mapper.SystemAdminMapper;
import buaa.bp.asclepius.model.SystemAdmin;

@Service
public class SystemAdminService extends GeneralService {

	@Resource(name="systemAdminMapper")
	private SystemAdminMapper systemAdminMapper;
	
	public List<SystemAdmin> getAllSystemAdmins() {
		return systemAdminMapper.getAllSystemAdmins();
	}
	public SystemAdmin getSystemAdminById(long adminId) {
		return systemAdminMapper.getSystemAdminById(adminId);
	}
	public int createSystemAdmin(SystemAdmin systemAdmin) {
		systemAdmin.setPassword(this.getMD5(systemAdmin.getPassword()));
		return systemAdminMapper.createSystemAdmin(systemAdmin);
	}
	public int updateSystemAdmin(SystemAdmin systemAdmin) {
		systemAdmin.setPassword(this.getMD5(systemAdmin.getPassword()));
		return systemAdminMapper.updateSystemAdmin(systemAdmin);
	}
	public int deleteSystemAdmin(long id) {
		return systemAdminMapper.deleteSystemAdmin(id);
	}
	private SystemAdmin getAdminByName(String name) {
		return systemAdminMapper.getSystemAdminByName(name);
	}
	public int count() {
		return systemAdminMapper.count();
	}
	public List<?> selectByRange(int start,int length) {
		return systemAdminMapper.selectByRange(start, length);
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
		SystemAdmin admin = getAdminByName(name);
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
