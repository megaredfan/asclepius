package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Appointment;

public interface AppointmentMapper {
	public List<Appointment> getAllAppointments(int userId);
	public Appointment getAppointmentById(int userId,int appointmentId);
	public int updateAppointment(Appointment appointment);
	public int createAppointment(Appointment appointment);
	public int deleteAppoinement(int id);
}
