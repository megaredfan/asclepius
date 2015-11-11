package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Appointment;
import buaa.bp.asclepius.model.AppointmentDetail;

public interface AppointmentDetailMapper {
	public Appointment getAppointmentById(int userId,int appointmentId);
	public int createAppointmentDetail(AppointmentDetail appointmentDetail);
	public int updateAppointmentDetail(AppointmentDetail appointmentDetail);
	public int deleteAppointmentDetail(int id);
}
