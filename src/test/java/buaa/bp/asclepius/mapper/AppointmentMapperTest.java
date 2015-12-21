package buaa.bp.asclepius.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import buaa.bp.asclepius.model.Appointment;

public class AppointmentMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	AppointmentMapper appointmentMapper = context.getBean("appointmentMapper",AppointmentMapper.class);
	
	@Before
	public void before(){
		Assert.assertEquals(1, appointmentMapper.deleteAppointment(41216998636L));
	}
	
	@Test
	public void test(){
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(41216998636L);
		appointment.setTime(new Timestamp(System.currentTimeMillis()));
		
		Assert.assertEquals(1, appointmentMapper.createAppointment(appointment));
		
		appointment.setUserId(41216998636L);
		appointment.setAppointmentDetailId(41216998636L);
		
		Assert.assertEquals(1, appointmentMapper.updateAppointment(appointment));
		
		appointment = appointmentMapper.getAppointmentById(41216998636L);
		System.out.println(appointment);
		
		List<Appointment> list = appointmentMapper.getAllAppointments(41216998636L);
		for(Appointment a : list)
		{
			System.out.println(a);
		}
		System.out.println(appointmentMapper.selectByRange(0, appointmentMapper.count()));
	}
}
