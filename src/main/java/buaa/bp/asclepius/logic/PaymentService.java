package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.mapper.PaymentMapper;
import buaa.bp.asclepius.model.Payment;

@Service
public class PaymentService extends GeneralService {
	
	@Resource(name="paymentMapper")
	private PaymentMapper paymentMapper;
	
	public List<Payment> getAllPayments(long userId) {
		return paymentMapper.getAllPayments(userId);
	}
	public Payment getPaymentById(long paymentId) {
		return paymentMapper.getPaymentById(paymentId);
	}
	public int createPayment(Payment payment) {
		return paymentMapper.createPayment(payment);
	}
	public int updatePayment(Payment payment) {
		return paymentMapper.updatePayment(payment);
	}
	public int deletePayment(Payment payment) {
		return paymentMapper.deletePayment(payment);
	}
	public int count() {
		return paymentMapper.count();
	}
	@Override
	public List<?> selectByRange(int start, int length) {
		// TODO Auto-generated method stub
		return paymentMapper.selectByRange(start, length);
	}
	@Override
	public void generateList(HttpServletRequest request, HttpServletResponse response, ModelAndView m,
			String listName) {
		// TODO Auto-generated method stub
		super.generateList(request, response, m, listName);
	}
	
}
