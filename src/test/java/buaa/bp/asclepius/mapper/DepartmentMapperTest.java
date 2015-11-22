package buaa.bp.asclepius.mapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import buaa.bp.asclepius.model.Department;

public class DepartmentMapperTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	DepartmentMapper departmentMapper = context.getBean("departmentMapper",DepartmentMapper.class);
	
	@BeforeClass
	public void before(){
		Assert.assertEquals(1, departmentMapper.deleteDepartment(41216998636L));
	}
	
	@Test
	public void test(){
		Department department = new Department();
		department.setDepartmentId(41216998636L);
		department.setDepartmentName("外科");
		department.setDescription("描述：外科");
		
		Assert.assertEquals(1, departmentMapper.createDepartment(department));
		
		department.setHospitalId(41216998636L);
		
		Assert.assertEquals(1, departmentMapper.updateDepartment(department));
		
		department = departmentMapper.getDepartmentById(41216998636L, 41216998636L);
		System.out.println(department);
		
		for(Department d : departmentMapper.getAllDepartments(41216998636L)){
			System.out.println(d);
		}
		System.out.println(departmentMapper.selectByRange(0, departmentMapper.count()));
	}
}
