package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.mapper.HospitalMapper;
import buaa.bp.asclepius.model.Hospital;

@Service
public class HospitalService extends GeneralService {
	
	@Resource(name="hospitalMapper")
	private HospitalMapper hospitalMapper;
	@Cacheable(value="hospital")
	public List<Hospital> getAllHospitals() {
		return hospitalMapper.getAllHospitals();
	}
	@Cacheable(value="hospital")
	public Hospital getHostpitalById(long hospitalId) {
		return hospitalMapper.getHostpitalById(hospitalId);
	}
	@CacheEvict(value="hospital")
	public int createHospital(Hospital hospital) {
		return hospitalMapper.createHospital(hospital);
	}
	@CacheEvict(value="hospital")
	public int updateHospital(Hospital hospital) {
		return hospitalMapper.updateHospital(hospital);
	}
	@CacheEvict(value="hospital")
	public int deleteHospital(long id) {
		return hospitalMapper.deleteHospital(id);
	}
	public int count() {
		return hospitalMapper.count();
	}
	@Cacheable(value="hospital")
	public List<?> selectByRange(int start,int length) {
		return hospitalMapper.selectByRange(start, length);
	}
	public void generateList(HttpServletRequest request,HttpServletResponse response,ModelAndView m,String listName){
		super.generateList(request, response,m,listName);
	}
	public List<Hospital> getTopHospitals(int capacity) {
		return hospitalMapper.getTopHospitals(capacity);
	}
}
