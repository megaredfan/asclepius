package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Appointment;

public interface AppointmentMapper extends SQLRecord  {
	public List<Appointment> getAllAppointments(long userId);
	public List<Appointment> getAllAppointmetnsByRange(long userId,int start,int length);
	public Appointment getAppointmentById(long userId,long appointmentId);
	public int updateAppointment(Appointment appointment);
	public int createAppointment(Appointment appointment);
	public int deleteAppointment(long id);
}
