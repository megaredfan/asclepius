package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import buaa.bp.asclepius.mapper.HospitalMapper;
import buaa.bp.asclepius.model.Hospital;

@Service
public class HospitalService {
	
	@Resource(name="hospitalMapper")
	private HospitalMapper hospitalMapper;
	
	public List<Hospital> getAllHospitals() {
		return hospitalMapper.getAllHospitals();
	}
	public Hospital getHostpitalById(long hospitalId) {
		return hospitalMapper.getHostpitalById(hospitalId);
	}
	public int createHospital(Hospital hospital) {
		return hospitalMapper.createHospital(hospital);
	}
	public int updateHospital(Hospital hospital) {
		return hospitalMapper.updateHospital(hospital);
	}
	public int deleteHospital(long id) {
		return hospitalMapper.deleteHospital(id);
	}
}
