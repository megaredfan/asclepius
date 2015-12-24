package buaa.bp.asclepius.util;

import java.sql.Date;
import java.util.Calendar;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DuplicateKeyException;

import buaa.bp.asclepius.logic.AppointmentDetailService;
import buaa.bp.asclepius.logic.DoctorService;
import buaa.bp.asclepius.model.AppointmentDetail;
import buaa.bp.asclepius.model.AutoAppointment;
import buaa.bp.asclepius.model.Doctor;
import buaa.bp.asclepius.utils.UUID11;

public class SchedulingTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	DoctorService doctorService = context.getBean(DoctorService.class);
	AppointmentDetailService appointmentDetailService = context.getBean(AppointmentDetailService.class);
	@Test
	public void test() throws ConfigurationException{
		PropertiesConfiguration conf = new PropertiesConfiguration("config.properties");
		int capacity = conf.getInt("defaultCapacity");
		double cost = conf.getDouble("defaultCost");
		
		for(Doctor d : doctorService.getAllDoctors()){
			AutoAppointment app = new AutoAppointment();
			app.setHospitalId(d.getDepartment().getHospital().getHospitalId());
			app.setDepartmentId(d.getDepartment().getDepartmentId());
			app.setDoctorId(d.getDoctorId());
			for(int i=0;i<5;i++){
				switch(i%5){
				case 0:
					app.setDay("mon");
					break;
				case 1:
					app.setDay("tue");
					break;
				case 2:
					app.setDay("wed");
					break;
				case 3:
					app.setDay("thur");
					break;
				case 4:
					app.setDay("fri");
					break;
				}
				app.setAmount(capacity);
				app.setTime("morning");
				app.setId(UUID11.getRandomId());
				app.setCost(cost);
				if(appointmentDetailService.selectByConditions(app.getHospitalId(), app.getDepartmentId(), app.getDoctorId(), app.getDay(), app.getTime())==null)
				{
					try{
						appointmentDetailService.createAutoAppointment(app);
					}catch(DuplicateKeyException e){
						app.setId(UUID11.getRandomId());
						appointmentDetailService.createAutoAppointment(app);
					}
				}
				
				app.setTime("afternoon");
				app.setId(UUID11.getRandomId());
				if(appointmentDetailService.selectByConditions(app.getHospitalId(), app.getDepartmentId(), app.getDoctorId(), app.getDay(), app.getTime())==null)
				{
					try{
						appointmentDetailService.createAutoAppointment(app);
					}catch(DuplicateKeyException e){
						app.setId(UUID11.getRandomId());
						appointmentDetailService.createAutoAppointment(app);
					}
				}
			}
			
		}
		for(AutoAppointment autoapp : appointmentDetailService.getAllAutoAppointments())
		{
			Calendar calendar = Calendar.getInstance();
			AppointmentDetail a = new AppointmentDetail();
			a.setAppdetailId(UUID11.getRandomId());
			a.setHospitalId(autoapp.getHospitalId());
			a.setDeptId(autoapp.getDepartmentId());
			a.setDoctorId(autoapp.getDoctorId());
			a.setTime(autoapp.getTime());
			a.setCost(autoapp.getCost());
			
			switch(autoapp.getDay()){
			case "mon":
				calendar.add(Calendar.DAY_OF_MONTH, 5);
				break;
			case "tue":
				calendar.add(Calendar.DAY_OF_MONTH, 6);
				break;
			case "wed":
				calendar.add(Calendar.DAY_OF_MONTH, 7);
				break;
			case "thur":
				calendar.add(Calendar.DAY_OF_MONTH, 8);
				break;
			case "fri":
				calendar.add(Calendar.DAY_OF_MONTH, 9);
				break;
			}
			Date date = new Date(calendar.getTimeInMillis());
			
			a.setDate(date);
			a.setAmount(autoapp.getAmount());
			try{
				appointmentDetailService.createAppointmentDetail(a);
			}catch(DuplicateKeyException e){
				a.setAppdetailId(UUID11.getRandomId());
				appointmentDetailService.createAppointmentDetail(a);
			}
		}
	}
}
