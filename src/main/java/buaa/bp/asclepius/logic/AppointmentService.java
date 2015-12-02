package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import buaa.bp.asclepius.model.Appointment;
import buaa.bp.asclepius.mapper.AppointmentMapper;

@Service
public class AppointmentService extends GeneralService {
	
	@Resource(name="appointmentMapper")
	private AppointmentMapper appointmentMapper;
	
	public List<Appointment> getAllAppointments(long userId) {
		return appointmentMapper.getAllAppointments(userId);
	}
	public Appointment getAppointmentById(long userId,long appointmentId) {
		return appointmentMapper.getAppointmentById(userId, appointmentId);
	}
	public int createAppointment(Appointment appointment) {
		return appointmentMapper.createAppointment(appointment);
	}
	public int updateAppointment(Appointment appointment) {
		return appointmentMapper.updateAppointment(appointment);
	}
	public int deleteAppoinement(long id) {
		return appointmentMapper.deleteAppoinement(id);
	}
	public int count() {
		return appointmentMapper.count();
	}
	public List<?> selectByRange(int start,int length) {
		return appointmentMapper.selectByRange(start, length);
	}
	public List<?> generateList(HttpServletRequest request,HttpServletResponse response){
		return super.generateList(request, response);
	}
}
