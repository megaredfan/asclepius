package buaa.bp.asclepius.model;

import java.sql.Date;

public class AppointmentDetail {
	private long appdetailId;
	private Date date;
	private String time;
	private int amount;
	private long doctorId;
	private long deptId;
	private long hospitalId;
	private double cost;
	
	public long getAppdetailId() {
		return appdetailId;
	}
	public void setAppdetailId(long appdetailId) {
		this.appdetailId = appdetailId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "AppointmentDetail [appdetailId=" + appdetailId + ", date=" + date + ", time=" + time + ", amount="
				+ amount + ", doctorId=" + doctorId + ", deptId=" + deptId + ", hospitalId=" + hospitalId + ", cost="
				+ cost + "]";
	}
	
}
