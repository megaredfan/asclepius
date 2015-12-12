package buaa.bp.asclepius.logic;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.mapper.AppointmentDetailMapper;
import buaa.bp.asclepius.mapper.AutoAppointmentMapper;
import buaa.bp.asclepius.model.AppointmentDetail;
import buaa.bp.asclepius.model.AutoAppointment;
import buaa.bp.asclepius.model.Doctor;
import buaa.bp.asclepius.utils.UUID11;

@Service
public class AppointmentDetailService extends GeneralService {

	@Resource(name="appointmentDetailMapper")
	private AppointmentDetailMapper appointmentDetailMapper;
	
	@Resource(name="autoAppointmentMapper")
	private AutoAppointmentMapper autoAppointmentMapper;
	
	@Resource(name="doctorService")
	private DoctorService doctorService;
	
	public AppointmentDetail getAppointmentByConditions(long hospitalId,long departmentId,long doctorId,Date date,String time){
		return appointmentDetailMapper.getAppointmentByConditions(hospitalId, departmentId, doctorId, date,time);
	}
	public AppointmentDetail getAppointmentById(long appointmentDetailId) {
		return appointmentDetailMapper.getAppointmentById(appointmentDetailId);
	}
	public int createAppointmentDetail(AppointmentDetail appointmentDetail) {
		return appointmentDetailMapper.createAppointmentDetail(appointmentDetail);
	}
	public int updateAppointmentDetail(AppointmentDetail appointmentDetail) {
		return appointmentDetailMapper.updateAppointmentDetail(appointmentDetail);
	}
	public int deleteAppointmentDetail(long id) {
		return appointmentDetailMapper.deleteAppointmentDetail(id);
	}
	public int count() {
		return appointmentDetailMapper.count();
	}
	public List<?> selectByRange(int start,int length) {
		return appointmentDetailMapper.selectByRange(start,length);
	}
	public void generateList(HttpServletRequest request,HttpServletResponse response,ModelAndView m,String listName){
		super.generateList(request, response,m,listName);
	}
	
	public void initAppointmentPool(int capacity){
		
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
				autoAppointmentMapper.create(app);
				app.setTime("afternoon");
				app.setId(UUID11.getRandomId());
				autoAppointmentMapper.create(app);
			}
			
		}
	}
	
	
}
