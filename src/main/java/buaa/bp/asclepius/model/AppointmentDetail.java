package buaa.bp.asclepius.model;

import java.sql.Timestamp;

public class AppointmentDetail {
	private long appdetailId;
	private Timestamp start;
	private Timestamp end;
	private Timestamp date;
	private int amount;
	private long doctorId;
	private long deptId;
	private long hospitalId;
	
	public long getAppdetailId() {
		return appdetailId;
	}
	public void setAppdetailId(long appdetailId) {
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
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}
	public long getDeptId() {
		return deptId;
	}
	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}
	public long getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	@Override
	public String toString() {
		return "AppointmentDetail [appdetailId=" + appdetailId + ", start=" + start + ", end=" + end + ", date=" + date
				+ ", amount=" + amount + ", doctorId=" + doctorId + ", deptId=" + deptId + ", hospitalId=" + hospitalId
				+ "]";
	}			
}
