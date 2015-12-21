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

import buaa.bp.asclepius.model.Doctor;

public class DoctorMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	DoctorMapper doctorMapper = context.getBean("doctorMapper",DoctorMapper.class);
	DepartmentMapper departmentMapper = context.getBean("departmentMapper",DepartmentMapper.class);
	@Before
	public void before(){
		Assert.assertEquals(1, doctorMapper.deleteDoctor(41216998636L));
	}
	
	@Test
	public void test() throws JsonProcessingException{
		Doctor doctor = new Doctor();
		doctor.setDoctorId(41216998636L);
		doctor.setName("DR. Lee");
		doctor.setSex("男");
		doctor.setLevel("主治医生");
		doctor.setDescription("good");
		
		Assert.assertEquals(1, doctorMapper.createDoctor(doctor));
		doctor.setDepartment(departmentMapper.getDepartmentById(41216998636L));
		Assert.assertEquals(1, doctorMapper.updateDoctor(doctor));
		
		doctor = doctorMapper.getDoctorById(41216998636L);
		System.out.println(doctor);
		
		for(Doctor d : doctorMapper.getAllDoctors()){
			System.out.println(d);
		}
		System.out.println(doctorMapper.selectByRange(0, doctorMapper.count()));
		List<Map> doctors = new ArrayList<Map>();
		for(Doctor d : doctorMapper.getAllDoctors()){
			Map<String,Object> m = new LinkedHashMap<String,Object>();
			m.put("doctorId", d.getDoctorId());
			m.put("doctorName", d.getName());
			m.put("doctorSex", d.getSex());
			m.put("doctorLevel", d.getLevel());
			m.put("description", d.getDescription());
			m.put("department", d.getDepartment().getDepartmentName());
			m.put("hospital", d.getDepartment().getHospital().getHospitalName());
			doctors.add(m);
		}
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(doctors);
		System.out.println(result);
	}
}
