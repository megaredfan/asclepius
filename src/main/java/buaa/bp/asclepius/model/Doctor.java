package buaa.bp.asclepius.model;

public class Doctor {
	private int doctorId;
	private String name;
	private String sex;
	private String level;//职称
	private String description;
	private int departmentId;
	private int hospitalId;
	
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", name=" + name + ", sex=" + sex + ", level=" + level
				+ ", description=" + description + ", departmentId=" + departmentId + ", hospitalId=" + hospitalId
				+ "]";
	}
	
	
}
