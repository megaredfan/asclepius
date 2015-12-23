package buaa.bp.asclepius.mapper;

import java.util.List;

import buaa.bp.asclepius.model.Payment;

public interface PaymentMapper extends SQLRecord {
	public List<Payment> getAllPayments(long userId);
	public Payment getPaymentById(long paymentId);
	public int createPayment(Payment payment);
	public int updatePayment(Payment payment);
	public int deletePayment(Payment payment);
	public int count();
	public List<?> selectByRange(int start, int length);
}
