package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Department;

public interface DepartmentMapper {
	public List<Department> getAllDepartments();
	public Department getDepartmentById(int departmentId);
	public int createDepartment(Department department);
	public int updateDepartment(Department department);
	public int deleteDepartment(int id);
}
