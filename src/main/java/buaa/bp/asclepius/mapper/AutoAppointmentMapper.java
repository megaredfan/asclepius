package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.AutoAppointment;

public interface AutoAppointmentMapper {
	public int create(AutoAppointment a);
	public int update(AutoAppointment a);
	public int delete(long id);
	public List<AutoAppointment> selectAll();
	public AutoAppointment select(long hospitalId,long departmentId,long doctorId,String day,String time);
}
