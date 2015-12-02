package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import buaa.bp.asclepius.mapper.DepartmentMapper;
import buaa.bp.asclepius.model.Department;

@Service
public class DepartmentService extends GeneralService  {
	
	@Resource(name="departmentMapper")
	private DepartmentMapper departmentMapper;
	
	public List<Department> getAllDepartments(long hospitalId) {
		return departmentMapper.getAllDepartments(hospitalId);
	}
	public Department getDepartmentById(long hospitalId,long departmentId) {
		return departmentMapper.getDepartmentById(hospitalId,departmentId);
	}
	public int createDepartment(Department department) {
		return departmentMapper.createDepartment(department);
	}
	public int updateDepartment(Department department) {
		return departmentMapper.updateDepartment(department);
	}
	public int deleteDepartment(long id) {
		return departmentMapper.deleteDepartment(id);
	}
	public int count() {
		return departmentMapper.count();
	}
	public List<?> selectByRange(int start,int length) {
		return departmentMapper.selectByRange(start, length);
	}
	public List<?> generateList(HttpServletRequest request,HttpServletResponse response){
		return super.generateList(request, response);
	}
}
