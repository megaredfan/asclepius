package buaa.bp.asclepius.logic;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import buaa.bp.asclepius.mapper.AppointmentDetailMapper;
import buaa.bp.asclepius.model.AppointmentDetail;

@Service
public class AppointmentDetailService {

	@Resource(name="appointmentDetailMapper")
	private AppointmentDetailMapper appointmentDetailMapper;
	public AppointmentDetail getAppointmentById(long appointmentDetailId) {
		return appointmentDetailMapper.getAppointmentById(appointmentDetailId);
	}
	public int createAppointmentDetail(AppointmentDetail appointmentDetail) {
		return appointmentDetailMapper.createAppointmentDetail(appointmentDetail);
	}
	public int updateAppointmentDetail(AppointmentDetail appointmentDetail) {
		return appointmentDetailMapper.updateAppointmentDetail(appointmentDetail);
	}
	public int deleteAppointmentDetail(long id) {
		return appointmentDetailMapper.deleteAppointmentDetail(id);
	}
}
