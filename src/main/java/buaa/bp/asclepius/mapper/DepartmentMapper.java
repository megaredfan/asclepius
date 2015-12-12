package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Department;
import buaa.bp.asclepius.model.Hospital;

public interface DepartmentMapper extends SQLRecord  {
	public List<Department> getAllDepartments();
	public List<Department> getAllDepartmentsByHospital(long hospitalId);
	public List<Department> getAllDepartmentsByRange(long hospitalId,int start,int length);
	public Department getDepartmentById(long departmentId);
	public int createDepartment(Department department);
	public int updateDepartment(Department department);
	public int deleteDepartment(long id);
	public Hospital getHospitalById(long hospitalId);
}
