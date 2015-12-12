package buaa.bp.asclepius.logic;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.bp.asclepius.mapper.AutoAppointmentMapper;
import buaa.bp.asclepius.model.AutoAppointment;

public class AppointmentDeatilServiceTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	AppointmentDetailService service = context.getBean(AppointmentDetailService.class);
	AutoAppointmentMapper mapper = context.getBean(AutoAppointmentMapper.class);
	
	@Test
	public void test() throws ConfigurationException{
		//service.initAppointmentPool(10);
//		AutoAppointment a = mapper.select(41216998636L, 41216998636L, 41216998636L, "fri", "morning");
//		a.setAmount(666);
//		mapper.update(a);
	}
}
