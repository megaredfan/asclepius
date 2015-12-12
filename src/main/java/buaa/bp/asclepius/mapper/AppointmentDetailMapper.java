package buaa.bp.asclepius.mapper;

import java.sql.Date;
import buaa.bp.asclepius.model.AppointmentDetail;

public interface AppointmentDetailMapper extends SQLRecord {
	public AppointmentDetail getAppointmentByConditions(long hospitalId,long departmentId,long doctorId,Date date,String time);
	public AppointmentDetail getAppointmentById(long appointmentDetailId);
	public int createAppointmentDetail(AppointmentDetail appointmentDetail);
	public int updateAppointmentDetail(AppointmentDetail appointmentDetail);
	public int deleteAppointmentDetail(long id);
}
