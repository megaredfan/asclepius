package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Department;

public interface DepartmentMapper extends SQLRecord  {
	public List<Department> getAllDepartments(long hospitalId);
	public Department getDepartmentById(long hospitalId,long departmentId);
	public int createDepartment(Department department);
	public int updateDepartment(Department department);
	public int deleteDepartment(long id);
}
