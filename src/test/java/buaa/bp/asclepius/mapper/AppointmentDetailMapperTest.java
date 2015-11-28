package buaa.bp.asclepius.mapper;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.bp.asclepius.model.AppointmentDetail;
import junit.framework.Assert;

public class AppointmentDetailMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	AppointmentDetailMapper appointmentDetailMapper = context.getBean(AppointmentDetailMapper.class);
	
	@Before
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
		
		appointmentDetail.setEnd(new Timestamp(System.currentTimeMillis()));
		
		Assert.assertEquals(1, appointmentDetailMapper.updateAppointmentDetail(appointmentDetail));
		
		appointmentDetail = appointmentDetailMapper.getAppointmentById(41216998636L);
		System.out.println(appointmentDetail);
		
		for(AppointmentDetail a : appointmentDetailMapper.getAvailableAppointments())
		{
			System.out.println(a);
		}
		
		System.out.println(appointmentDetailMapper.selectByRange(0, appointmentDetailMapper.count()));

	}
}
