package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import buaa.bp.asclepius.mapper.DoctorMapper;
import buaa.bp.asclepius.model.Doctor;

@Service
public class DoctorService {
	
	@Resource(name="doctorMapper")
	private DoctorMapper doctorMapper;
	
	public List<Doctor> getAllDoctors() {
		return doctorMapper.getAllDoctors();
	}
	public Doctor getDoctorById(long doctorId) {
		return doctorMapper.getDoctorById(doctorId);
	}
	public int createDoctor(Doctor doctor) {
		return doctorMapper.createDoctor(doctor);
	}
	public int updateDoctor(Doctor doctor) {
		return doctorMapper.updateDoctor(doctor);
	}
	public int deleteDoctor(long id) {
		return doctorMapper.deleteDoctor(id);
	}
	public int count() {
		return doctorMapper.count();
	}
	public List<?> selectByRange(int start,int length) {
		return doctorMapper.selectByRange(start, length);
	}
}
