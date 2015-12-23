package buaa.bp.asclepius.model;

import java.sql.Timestamp;

public class Payment {
	private long paymentId;
	private Timestamp date;// 日期
	private double cost;// 金额
	private long userId;// 支付用户数
	private long appointmentId;
	
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
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
		return "Payment [paymentId=" + paymentId + ", date=" + date + ", cost=" + cost + ", payUserCount="
				+ userId + ", appointmentId=" + appointmentId + "]";
	}

	
}
