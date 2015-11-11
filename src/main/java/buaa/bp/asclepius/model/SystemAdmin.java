package buaa.bp.asclepius.model;

import java.sql.Timestamp;

public class SystemAdmin {
	private int adminId;
	private String password;
	private String adminName;
	private Timestamp lastVisit;
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Timestamp getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(Timestamp lastVisit) {
		this.lastVisit = lastVisit;
	}
	
	@Override
	public String toString() {
		return "SystemAdmin [adminId=" + adminId + ", password=" + password + ", adminName=" + adminName
				+ ", lastVisit=" + lastVisit + "]";
	}
	
	
}
