package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.mapper.DoctorMapper;
import buaa.bp.asclepius.model.Doctor;

@Service
public class DoctorService extends GeneralService{
	
	@Resource(name="doctorMapper")
	private DoctorMapper doctorMapper;
	
	public List<Doctor> getAllDoctors() {
		return doctorMapper.getAllDoctors();
	}
	public List<Doctor> getDoctorsByDepartment(long departmentId) {
		return doctorMapper.getDoctorsByDepartment(departmentId);
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
	public void generateList(HttpServletRequest request,HttpServletResponse response,ModelAndView m,String listName){
		super.generateList(request, response,m,listName);
	}
}
