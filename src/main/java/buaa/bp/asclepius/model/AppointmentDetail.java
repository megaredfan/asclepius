package buaa.bp.asclepius.model;

import java.sql.Timestamp;

public class AppointmentDetail {
	private int appdetailId;
	private Timestamp start;
	private Timestamp end;
	private int amount;
	private int appointmentId;
	private int doctorId;
	private int deptId;
	private int hospitalId;
	
	public int getAppdetailId() {
		return appdetailId;
	}
	public void setAppdetailId(int appdetailId) {
		this.appdetailId = appdetailId;
	}
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public int getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}
	@Override
	public String toString() {
		return "AppointmentDetail [appdetailId=" + appdetailId + ", start=" + start + ", end=" + end + ", amount="
				+ amount + ", appointmentId=" + appointmentId + ", doctorId=" + doctorId + ", deptId=" + deptId
				+ ", hospitalId=" + hospitalId + "]";
	}
	
	
}
