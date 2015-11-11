package buaa.bp.asclepius.model;

import java.sql.Timestamp;

public class Credit {
	private int creditId;
	private String description;
	private Timestamp createTime;
	private int userId;
	
	public int getCreditId() {
		return creditId;
	}
	public void setCreditId(int creditId) {
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Credit [creditId=" + creditId + ", description=" + description + ", createTime=" + createTime
				+ ", userId=" + userId + "]";
	}
	
	
}
