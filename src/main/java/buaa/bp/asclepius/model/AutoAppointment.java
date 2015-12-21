package buaa.bp.asclepius.model;

public class AutoAppointment {
	private long id;
	private long hospitalId;
	private long departmentId;
	private long doctorId;
	private String day;
	private String time;
	private int amount;
	
	public AutoAppointment(){}

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}
	public long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}
	public long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "AutoAppointment [id=" + id + ", hospitalId=" + hospitalId + ", departmentId=" + departmentId
				+ ", doctorId=" + doctorId + ", day=" + day + ", time=" + time + ", amount=" + amount + "]";
	}
	
}
