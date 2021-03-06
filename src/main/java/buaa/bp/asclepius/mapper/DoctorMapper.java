package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Doctor;

public interface DoctorMapper extends SQLRecord  {
	public List<Doctor> getAllDoctors();
	public Doctor getDoctorById(long doctorId);
	public int createDoctor(Doctor doctor);
	public int updateDoctor(Doctor doctor);
	public int deleteDoctor(long id);
	public List<Doctor> getDoctorsByDepartment(long departmentId);
}
