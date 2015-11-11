package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Doctor;

public interface DoctorMapper {
	public List<Doctor> getAllDoctors();
	public Doctor getDoctorById(int doctorId);
	public int createDoctor(Doctor doctor);
	public int updateDoctor(Doctor doctor);
	public int deleteDoctor(int id);
}
