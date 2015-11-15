package buaa.bp.asclepius.mapper;

import java.sql.Timestamp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import buaa.bp.asclepius.model.AppointmentDetail;
import junit.framework.Assert;

public class AppointmentDetailMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	AppointmentDetailMapper appointmentDetailMapper = context.getBean("appointmentDetailMapper",AppointmentDetailMapper.class);
	
	@BeforeClass
	public void before(){
		Assert.assertEquals(1, appointmentDetailMapper.deleteAppointmentDetail(41216998636L));
	}
	
	@Test
	public void test(){
		AppointmentDetail appointmentDetail = new AppointmentDetail();
		appointmentDetail.setAppdetailId(41216998636L);
		appointmentDetail.setDoctorId(41216998636L);
		appointmentDetail.setDeptId(41216998636L);
		appointmentDetail.setHospitalId(41216998636L);
		appointmentDetail.setStart(new Timestamp(System.currentTimeMillis()));
		
		Assert.assertEquals(1, appointmentDetailMapper.createAppointmentDetail(appointmentDetail));
		
		appointmentDetail.setAppointmentId(41216998636L);
		appointmentDetail.setEnd(new Timestamp(System.currentTimeMillis()));
		
		Assert.assertEquals(1, appointmentDetailMapper.updateAppointmentDetail(appointmentDetail));
		
		appointmentDetail = appointmentDetailMapper.getAppointmentById(41216998636L);
		System.out.println(appointmentDetail);

	}
}
