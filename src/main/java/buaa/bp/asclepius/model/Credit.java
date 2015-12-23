package buaa.bp.asclepius.model;

import java.sql.Timestamp;

public class Credit {
	private long creditId;
	private String description;
	private Timestamp createTime;
	private long userId;
	private long appointmentId;
	
	public long getCreditId() {
		return creditId;
	}
	public void setCreditId(long creditId) {
		this.creditId = creditId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	@Override
	public String toString() {
		return "Credit [creditId=" + creditId + ", description=" + description + ", createTime=" + createTime
				+ ", userId=" + userId + ", appointmentId=" + appointmentId + "]";
	}
	
	
}
