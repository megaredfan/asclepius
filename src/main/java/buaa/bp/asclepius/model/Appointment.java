package buaa.bp.asclepius.model;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotBlank;

public class Appointment {
	private long appointmentId;
	private Timestamp time;//创建时间 
	private int status;
	@NotBlank(message="患者姓名不能为空！")private String patientName;
	private String patientSex;
	@NotBlank(message="患者年龄不能为空！")private int patientAge;
	private String patientInsuranceNo;
	private long userId;
	@NotBlank(message="预约失败！请重新预约")private long appointmentDetailId;
	
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public String getPatientInsuranceNo() {
		return patientInsuranceNo;
	}
	public void setPatientInsuranceNo(String patientInsuranceNo) {
		this.patientInsuranceNo = patientInsuranceNo;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getAppointmentDetailId() {
		return appointmentDetailId;
	}
	public void setAppointmentDetailId(long appointmentDetailId) {
		this.appointmentDetailId = appointmentDetailId;
	}
	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", time=" + time + ", status=" + status
				+ ", patientName=" + patientName + ", patientSex=" + patientSex + ", patientAge=" + patientAge
				+ ", patientInsuranceNo=" + patientInsuranceNo + ", userId=" + userId + ", appointmentDetailId="
				+ appointmentDetailId + "]";
	}
	
	
	
}
