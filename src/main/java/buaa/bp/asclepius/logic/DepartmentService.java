package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.mapper.DepartmentMapper;
import buaa.bp.asclepius.model.Department;

@Service
public class DepartmentService extends GeneralService  {
	
	@Resource(name="departmentMapper")
	private DepartmentMapper departmentMapper;
	
	@Resource(name="configLoader")
	private PropertiesConfiguration configLoader;
	
	public List<Department> getAllDepartmentsByHospital(long hospitalId) {
		return departmentMapper.getAllDepartmentsByHospital(hospitalId);
	}
	public List<Department> getAllDepartments() {
		return departmentMapper.getAllDepartments();
	}
	public Department getDepartmentById(long departmentId) {
		return departmentMapper.getDepartmentById(departmentId);
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
	public void generateList(HttpServletRequest request,HttpServletResponse response,ModelAndView m,String listName){
		super.generateList(request, response,m,listName);
	}
	public void generateList(HttpServletRequest request,HttpServletResponse response,ModelAndView m,String listName,long hospitalId){
		String s_pageNo = "0";
		int pageNo = 0;
		int pageSize = configLoader.getInt("page.pageSize");
		int totalPages = (count() + pageSize - 1) / pageSize;
		s_pageNo = (String)request.getParameter("pageNo");
		try{
			pageNo = Integer.parseInt(s_pageNo);
		}catch(Exception e){
			
		}
		if(pageNo < 0){
			pageNo = 0;
		}
		if(pageNo > totalPages){
			pageNo = totalPages-1;
		}
		
		List<Department> list = departmentMapper.getAllDepartmentsByRange(hospitalId, pageNo*pageSize, pageSize);
		m.addObject(listName,list);
		m.addObject("pageNo",pageNo);
		m.addObject("totalPages",totalPages);
	}
	
}
