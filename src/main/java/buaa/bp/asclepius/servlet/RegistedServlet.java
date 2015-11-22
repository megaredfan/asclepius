package buaa.bp.asclepius.servlet;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.logic.AppointmentService;
import buaa.bp.asclepius.logic.UserService;
import buaa.bp.asclepius.model.Appointment;
import buaa.bp.asclepius.model.User;
import buaa.bp.asclepius.utils.UUID11;

@Controller
@RequestMapping("/Asclepius/registed")
public class RegistedServlet {
	private String account = "registed/account";
	private String pay = "registed/pay";
	private String makeAppointment = "registed/makeAppointment";
	
	@Autowired(required=false)
	private Validator validator;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="appointmentService")
	private AppointmentService appointmentService;
	
	@RequestMapping("/account.html")
	public ModelAndView account(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(account);
		return m;
	}
	
	@RequestMapping("/makeAnAppointment.html")
	public ModelAndView makdAnAppointment(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(makeAppointment);
		if(StringUtils.isBlank((String)request.getParameter("opType")))
		{
			return m;
		}
		Appointment appointment = new Appointment();
		String appointmentDetailId = (String)request.getParameter("appointmentDetailId");
		appointment.setAppointmentId(UUID11.getRandomId());
		User currentUser = (User)request.getSession().getAttribute("userInSession");
		appointment.setUserId(currentUser.getId());
		try{
			appointment.setAppointmentDetailId(Long.parseLong(appointmentDetailId));
		}catch(NumberFormatException e){
			//留空表示将appointmentDetailId设为null
		}
		try{
			appointment.setPatientAge(Integer.parseInt(((String)request.getParameter("patientAge"))));
		}catch(NumberFormatException e){
			//null
		}
		appointment.setPatientName((String)request.getParameter("patientName"));
		appointment.setPatientSex((String)request.getParameter("patientSex"));
		appointment.setPatientInsuranceNo((String)request.getParameter("patientInsuranceNo"));
		appointment.setTime(new Timestamp(System.currentTimeMillis()));
		
		Set<ConstraintViolation<Appointment>> constraintViolations = validator.validate(appointment);
		if(constraintViolations.size() > 0){
			List<String> fieldErrors = new ArrayList<String>();
			for(ConstraintViolation<Appointment> c : constraintViolations){
				fieldErrors.add(c.getMessage());
			}
			System.out.println(fieldErrors.size());
			m.addObject("fieldErrors",fieldErrors);
			return m;
		}
		try{
			appointmentService.createAppointment(appointment);
		}catch(org.springframework.dao.DuplicateKeyException e){
			makdAnAppointment(request,response);
		}
		return new ModelAndView(account);
	}
	
	@RequestMapping("/cancelAnAppointment.html")
	public ModelAndView cancelAnAppointment(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(account);
		String s_appid = (String)request.getParameter("appointmentid");
		if(StringUtils.isBlank(s_appid)){
			//TODO:添加提示信息，在前段用alert弹出
			return m ;
		}
		try{
			appointmentService.deleteAppoinement(Integer.parseInt(s_appid));
		}catch(NumberFormatException e){
			//TODO:添加提示信息，在前段用alert弹出
			return m;
		}catch(Exception e){
			//TODO:添加提示信息，在前段用alert弹出
			return m;
		}
		return new ModelAndView(account);
	}
	
	@RequestMapping("/pay.html")
	public ModelAndView pay(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(pay);
		return m;
		//TODO:跳转到支付页面
	}
}
