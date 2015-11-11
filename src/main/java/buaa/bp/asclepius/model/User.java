package buaa.bp.asclepius.model;

import java.sql.Timestamp;

public class User {
	private int id;
	private String password;
	private Timestamp registerTime;
	private Timestamp lastLogin;
	private String idNo;//身份证号
	private String sex;
	private String name;
	private int creditLevel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCreditLevel() {
		return creditLevel;
	}
	public void setCreditLevel(int creditLevel) {
		this.creditLevel = creditLevel;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", registerTime=" + registerTime + ", lastLogin="
				+ lastLogin + ", idNo=" + idNo + ", sex=" + sex + ", name=" + name + ", creditLevel=" + creditLevel
				+ "]";
	}
	
	
}
