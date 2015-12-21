package buaa.bp.asclepius.logic;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.mapper.AppointmentDetailMapper;
import buaa.bp.asclepius.mapper.AutoAppointmentMapper;
import buaa.bp.asclepius.model.AppointmentDetail;
import buaa.bp.asclepius.model.AutoAppointment;

@Service
public class AppointmentDetailService extends GeneralService {

	@Resource(name="appointmentDetailMapper")
	private AppointmentDetailMapper appointmentDetailMapper;
	
	@Resource(name="autoAppointmentMapper")
	private AutoAppointmentMapper autoAppointmentMapper;
	
	@Cacheable(value="appointmentDetail")
	public AppointmentDetail getAppointmentByConditions(long hospitalId,long departmentId,long doctorId,Date date,String time){
		return appointmentDetailMapper.getAppointmentByConditions(hospitalId, departmentId, doctorId, date,time);
	}
	@Cacheable(value="appointmentDetail")
	public AppointmentDetail getAppointmentById(long appointmentDetailId) {
		return appointmentDetailMapper.getAppointmentById(appointmentDetailId);
	}
	@CacheEvict(value="appointmentDetail")
	public int createAppointmentDetail(AppointmentDetail appointmentDetail) {
		return appointmentDetailMapper.createAppointmentDetail(appointmentDetail);
	}
	@CacheEvict(value="appointmentDetail")
	public int updateAppointmentDetail(AppointmentDetail appointmentDetail) {
		return appointmentDetailMapper.updateAppointmentDetail(appointmentDetail);
	}
	@CacheEvict(value="appointmentDetail")
	public int deleteAppointmentDetail(long id) {
		return appointmentDetailMapper.deleteAppointmentDetail(id);
	}
	public int count() {
		return appointmentDetailMapper.count();
	}
	@Cacheable(value="appointmentDetail")
	public List<?> selectByRange(int start,int length) {
		return appointmentDetailMapper.selectByRange(start,length);
	}
	public void generateList(HttpServletRequest request,HttpServletResponse response,ModelAndView m,String listName){
		super.generateList(request, response,m,listName);
	}
	@Cacheable(value="autoAppointment")
	public AutoAppointment selectByConditions(long hospitalId,long departmentId,long doctorId,String day,String time){
		return autoAppointmentMapper.select(hospitalId, departmentId, doctorId, day, time);
	}
	@CacheEvict(value="autoAppointment")
	public int updateAutoAppointment(AutoAppointment app){
		return autoAppointmentMapper.update(app);
	}
	@CacheEvict(value="autoAppointment")
	public int createAutoAppointment(AutoAppointment app) {
		return autoAppointmentMapper.create(app);
	}
	@Cacheable(value="autoAppointment")
	public List<AutoAppointment> getAllAutoAppointments() {
		return autoAppointmentMapper.selectAll();
	}
	
}
