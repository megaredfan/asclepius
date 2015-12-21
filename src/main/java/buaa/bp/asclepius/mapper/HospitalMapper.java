package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Hospital;

public interface HospitalMapper extends SQLRecord  {
	public List<Hospital> getAllHospitals();
	public Hospital getHostpitalById(long hospitalId);
	public int createHospital(Hospital hospital);
	public int updateHospital(Hospital hospital);
	public int deleteHospital(long id);
	public List<Hospital> getTopHospitals(int capacity);
}
