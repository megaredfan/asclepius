package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.AppointmentDetail;

public interface AppointmentDetailMapper extends SQLRecord {
	public List<AppointmentDetail> getAvailableAppointments();
	public List<AppointmentDetail> getAvailableAppointmentsByRange(int start,int length);
	public AppointmentDetail getAppointmentById(long appointmentDetailId);
	public int createAppointmentDetail(AppointmentDetail appointmentDetail);
	public int updateAppointmentDetail(AppointmentDetail appointmentDetail);
	public int deleteAppointmentDetail(long id);
}
