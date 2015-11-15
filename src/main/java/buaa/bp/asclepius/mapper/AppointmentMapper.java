package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Appointment;

public interface AppointmentMapper {
	public List<Appointment> getAllAppointments(long userId);
	public Appointment getAppointmentById(long userId,long appointmentId);
	public int updateAppointment(Appointment appointment);
	public int createAppointment(Appointment appointment);
	public int deleteAppoinement(long id);
}
