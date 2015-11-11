package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import buaa.bp.asclepius.mapper.SystemAdminMapper;
import buaa.bp.asclepius.model.SystemAdmin;

@Service
public class SystemAdminService {

	@Resource(name="systemAdminMapper")
	private SystemAdminMapper systemAdminMapper;
	
	public List<SystemAdmin> getAllSystemAdmins() {
		return systemAdminMapper.getAllSystemAdmins();
	}
	public SystemAdmin getSystemAdminById(int systemAdminId) {
		return systemAdminMapper.getSystemAdminById(systemAdminId);
	}
	public int createSystemAdmin(SystemAdmin systemAdmin) {
		return systemAdminMapper.createSystemAdmin(systemAdmin);
	}
	public int updateSystemAdmin(SystemAdmin systemAdmin) {
		return systemAdminMapper.updateSystemAdmin(systemAdmin);
	}
	public int deleteSystemAdmin(int id) {
		return systemAdminMapper.deleteSystemAdmin(id);
	}
}
