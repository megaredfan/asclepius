package buaa.bp.asclepius.model;

import java.sql.Timestamp;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


public class User {
	private long id;
	@NotBlank(message="密码不能为空！")
	@Size(min=6)
	private String password;
	private Timestamp registerTime;
	private Timestamp lastLogin;
	@NotBlank(message="身份证号不能为空！") private String idNo;//身份证号
	@NotBlank(message="性别不能为空！") private String sex;
	@NotBlank(message="用户名不能为空！") private String userName;
	@NotBlank(message="姓名不能为空！") private String realName;
	@NotBlank(message="邮箱不能为空！") private String email;
	private int activeFlag;
	private String validateCode;
	private int creditLevel;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public int getCreditLevel() {
		return creditLevel;
	}
	public void setCreditLevel(int creditLevel) {
		this.creditLevel = creditLevel;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", registerTime=" + registerTime + ", lastLogin="
				+ lastLogin + ", idNo=" + idNo + ", sex=" + sex + ", userName=" + userName + ", realName=" + realName
				+ ", email=" + email + ", activeFlag=" + activeFlag + ", validateCode=" + validateCode
				+ ", creditLevel=" + creditLevel + "]";
	}
	
	
	
}
