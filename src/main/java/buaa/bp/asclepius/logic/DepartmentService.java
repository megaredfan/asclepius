package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import buaa.bp.asclepius.mapper.DepartmentMapper;
import buaa.bp.asclepius.model.Department;

@Service
public class DepartmentService {
	
	@Resource(name="departmentMapper")
	private DepartmentMapper departmentMapper;
	
	public List<Department> getAllDepartments() {
		return departmentMapper.getAllDepartments();
	}
	public Department getDepartmentById(int departmentId) {
		return departmentMapper.getDepartmentById(departmentId);
	}
	public int createDepartment(Department department) {
		return departmentMapper.createDepartment(department);
	}
	public int updateDepartment(Department department) {
		return departmentMapper.updateDepartment(department);
	}
	public int deleteDepartment(int id) {
		return departmentMapper.deleteDepartment(id);
	}
}
