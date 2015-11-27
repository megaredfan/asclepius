package buaa.bp.asclepius.mapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import buaa.bp.asclepius.model.Doctor;

public class DoctorMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	DoctorMapper doctorMapper = context.getBean("doctorMapper",DoctorMapper.class);
	
	@Before
	public void before(){
		Assert.assertEquals(1, doctorMapper.deleteDoctor(41216998636L));
	}
	
	@Test
	public void test(){
		Doctor doctor = new Doctor();
		doctor.setDoctorId(41216998636L);
		doctor.setName("DR. Lee");
		doctor.setSex("男");
		doctor.setLevel("主治医生");
		doctor.setDescription("good");
		
		Assert.assertEquals(1, doctorMapper.createDoctor(doctor));
		
		doctor.setDepartmentId(41216998636L);
		doctor.setHospitalId(41216998636L);
		Assert.assertEquals(1, doctorMapper.updateDoctor(doctor));
		
		doctor = doctorMapper.getDoctorById(41216998636L);
		System.out.println(doctor);
		
		for(Doctor d : doctorMapper.getAllDoctors()){
			System.out.println(d);
		}
		System.out.println(doctorMapper.selectByRange(0, doctorMapper.count()));
		
	}
}