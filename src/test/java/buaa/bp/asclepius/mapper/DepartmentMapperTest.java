package buaa.bp.asclepius.mapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import buaa.bp.asclepius.model.Department;
import buaa.bp.asclepius.model.Hospital;

public class DepartmentMapperTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	DepartmentMapper departmentMapper = context.getBean("departmentMapper",DepartmentMapper.class);
	
	//@Before
	public void before(){
		Assert.assertEquals(1, departmentMapper.deleteDepartment(41216998636L));
	}
	
	@Test
	public void test() throws JsonProcessingException{
		/*Department department = new Department();
		department.setDepartmentId(41216998636L);
		department.setDepartmentName("外科");
		department.setDescription("描述：外科");
		Hospital hospital = new Hospital();
		
		Assert.assertEquals(1, departmentMapper.createDepartment(department));
		
		hospital.setHospitalId(41216998636L);
		department.setHospital(hospital);
		
		Assert.assertEquals(1, departmentMapper.updateDepartment(department));
		
		department = departmentMapper.getDepartmentById(41216998636L);
		System.out.println(department);
		
		for(Department d : departmentMapper.getAllDepartmentsByHospital(41216998636L)){
			System.out.println(d);
		}
		System.out.println(departmentMapper.selectByRange(0, departmentMapper.count()));
		List<Map<String,Object>> departments = new ArrayList<Map<String,Object>>();
		for(Department d : departmentMapper.getAllDepartmentsByHospital(41216998636L)){
			Map<String,Object> m = new LinkedHashMap<String,Object>();
			m.put("departmentId", d.getDepartmentId());
			m.put("departmentName", d.getDepartmentName());
			m.put("description", d.getDescription());
			m.put("hospitalName",d.getHospital().getHospitalName());
			departments.add(m);
		}
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(departments);
		System.out.println(result);*/
		for(Department d : departmentMapper.getTopDepartments(4))
		{
			System.out.println(d);
		}
		}
		
	
}
