package buaa.bp.asclepius.mapper;

import buaa.bp.asclepius.model.AppointmentDetail;

public interface AppointmentDetailMapper {
	public AppointmentDetail getAppointmentById(long appointmentDetailId);
	public int createAppointmentDetail(AppointmentDetail appointmentDetail);
	public int updateAppointmentDetail(AppointmentDetail appointmentDetail);
	public int deleteAppointmentDetail(long id);
}
