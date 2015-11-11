package buaa.bp.asclepius.logic;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import buaa.bp.asclepius.mapper.AppointmentDetailMapper;
import buaa.bp.asclepius.model.Appointment;
import buaa.bp.asclepius.model.AppointmentDetail;

@Service
public class AppointmentDetailService {

	@Resource(name="appointmentDetailMapper")
	private AppointmentDetailMapper appointmentDetailMapper;
	public Appointment getAppointmentById(int userId,int appointmentId) {
		return appointmentDetailMapper.getAppointmentById(userId, appointmentId);
	}
	public int createAppointmentDetail(AppointmentDetail appointmentDetail) {
		return appointmentDetailMapper.createAppointmentDetail(appointmentDetail);
	}
	public int updateAppointmentDetail(AppointmentDetail appointmentDetail) {
		return appointmentDetailMapper.updateAppointmentDetail(appointmentDetail);
	}
	public int deleteAppointmentDetail(int id) {
		return appointmentDetailMapper.deleteAppointmentDetail(id);
	}
}
