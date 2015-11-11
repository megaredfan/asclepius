package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.SystemAdmin;

public interface SystemAdminMapper {
	public List<SystemAdmin> getAllSystemAdmins();
	public SystemAdmin getSystemAdminById(int systemAdminId);
	public int createSystemAdmin(SystemAdmin systemAdmin);
	public int updateSystemAdmin(SystemAdmin systemAdmin);
	public int deleteSystemAdmin(int id);
}
