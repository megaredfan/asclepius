package buaa.bp.asclepius.mapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import buaa.bp.asclepius.model.Hospital;

public class HospitalMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	HospitalMapper hospitalMapper = context.getBean("hospitalMapper",HospitalMapper.class);
	
	@BeforeClass
	public void before(){
		Assert.assertEquals(1, hospitalMapper.deleteHospital(41216998636L));
	}
	
	@Test
	public void test(){
		Hospital hospital = new Hospital();
		hospital.setHospitalId(41216998636L);
		hospital.setHospitalName("hopital1");
		
		Assert.assertEquals(1, hospitalMapper.createHospital(hospital));
		
		hospital.setDescription("good hospital");
		
		Assert.assertEquals(1, hospitalMapper.updateHospital(hospital));
		
		hospital = hospitalMapper.getHostpitalById(41216998636L);
		System.out.println(hospital);
		
		for(Hospital h : hospitalMapper.getAllHospitals())
		{
			System.out.println(h);
		}
	}
}
