package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Hospital;

public interface HospitalMapper {
	public List<Hospital> getAllHospitals();
	public Hospital getHostpitalById(int hospitalId);
	public int createHospital(Hospital hospital);
	public int updateHospital(Hospital hospital);
	public int deleteHospital(int id);
}
