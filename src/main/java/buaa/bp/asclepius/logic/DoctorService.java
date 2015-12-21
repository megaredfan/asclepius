package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.mapper.DoctorMapper;
import buaa.bp.asclepius.model.Doctor;

@Service
public class DoctorService extends GeneralService{
	
	@Resource(name="doctorMapper")
	private DoctorMapper doctorMapper;
	@Cacheable(value="doctor")
	public List<Doctor> getAllDoctors() {
		return doctorMapper.getAllDoctors();
	}
	@Cacheable(value="doctor")
	public List<Doctor> getDoctorsByDepartment(long departmentId) {
		return doctorMapper.getDoctorsByDepartment(departmentId);
	}
	@Cacheable(value="doctor")
	public Doctor getDoctorById(long doctorId) {
		return doctorMapper.getDoctorById(doctorId);
	}
	@CacheEvict(value="doctor")
	public int createDoctor(Doctor doctor) {
		return doctorMapper.createDoctor(doctor);
	}
	@CacheEvict(value="doctor")
	public int updateDoctor(Doctor doctor) {
		return doctorMapper.updateDoctor(doctor);
	}
	@CacheEvict(value="doctor")
	public int deleteDoctor(long id) {
		return doctorMapper.deleteDoctor(id);
	}
	public int count() {
		return doctorMapper.count();
	}
	@Cacheable(value="doctor")
	public List<?> selectByRange(int start,int length) {
		return doctorMapper.selectByRange(start, length);
	}
	public void generateList(HttpServletRequest request,HttpServletResponse response,ModelAndView m,String listName){
		super.generateList(request, response,m,listName);
	}
}
