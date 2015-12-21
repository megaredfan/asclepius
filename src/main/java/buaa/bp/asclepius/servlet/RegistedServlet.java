package buaa.bp.asclepius.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.logic.AppointmentDetailService;
import buaa.bp.asclepius.logic.AppointmentService;
import buaa.bp.asclepius.logic.DepartmentService;
import buaa.bp.asclepius.logic.DoctorService;
import buaa.bp.asclepius.logic.HospitalService;
import buaa.bp.asclepius.logic.UserService;
import buaa.bp.asclepius.model.Appointment;
import buaa.bp.asclepius.model.AppointmentDetail;
import buaa.bp.asclepius.model.Department;
import buaa.bp.asclepius.model.Doctor;
import buaa.bp.asclepius.model.Hospital;
import buaa.bp.asclepius.model.User;
import buaa.bp.asclepius.utils.UUID11;
import buaa.bp.asclepius.utils.TimeUtil;

@Controller
@Transactional
@RequestMapping("/registed")
public class RegistedServlet {
	private static final String account = "registed/account";
	private static final String edit = "registed/edit";
	private static final String pay = "registed/pay";
	private static final String makeAppointment = "registed/makeAppointment";
	private static final String afterbook = "registed/afterbook";
	private static final String applist = "registed/appointmentList";
	private static final String appdetail = "registed/appdetail";
	private static final String hosdetail = "registed/hosdetail";
	private static final String deptdetail = "registed/deptdetail";
	
	@Autowired(required=false)
	private Validator validator;
	
	@Resource(name="configLoader")
	private PropertiesConfiguration configLoader;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="appointmentService")
	private AppointmentService appointmentService;
	
	@Resource(name="appointmentDetailService")
	private AppointmentDetailService appointmentDetailService;
	
	@Resource(name="hospitalService")
	private HospitalService hospitalService;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;

	@Resource(name="doctorService")
	private DoctorService doctorService;
	
	@RequestMapping("/myAccount.html")
	public ModelAndView account(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView m = new ModelAndView(account);
		User user = (User) request.getSession().getAttribute("userInSession");
		//TODO:改成根据用户id获取并分页
		try{
			appointmentService.generateList(request, response, m, "appointments",user.getId());
		}catch(Exception e){
			response.sendRedirect("../login.html");
		}
		return m;
	}
	
	@RequestMapping("/editProfile.html")
	public ModelAndView editProfile(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView m = new ModelAndView(edit);
		User user = (User) request.getSession().getAttribute("userInSession");
		if(user==null){
			response.sendRedirect("../login.html");
			return null;
		}
		String opType = (String)request.getParameter("opType");
		if(StringUtils.isBlank(opType))
		{
			m.addObject("user",user);
			return m;
		}
		if(!StringUtils.isBlank(opType))
		{
			
			user.setUserName((String)request.getParameter("userName"));
			user.setRealName((String)request.getParameter("realName"));
			user.setSex((String)request.getParameter("sex"));
			user.setIdNo((String)request.getParameter("idNo"));
			Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
			if(constraintViolations.size() > 0){
				List<String> fieldErrors = new ArrayList<String>();
				for(ConstraintViolation<User> c : constraintViolations){
					fieldErrors.add(c.getMessage());
				}
				System.out.println(fieldErrors.size());
				m.addObject("fieldErrors",fieldErrors);
				return m;
			}
				userService.updateUser(user);
				request.getSession().setAttribute("userInSession", user);
				response.sendRedirect("myAccount.html");
				return null;
		}
		return m;
	}
	
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED) 
	@RequestMapping("/myAppointments.html")
	public ModelAndView myAppointments(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView m = new ModelAndView(applist);
		long userId;
		try{
			userId = ((User) request.getSession().getAttribute("userInSession")).getId();
		}catch(NullPointerException e){
			response.sendRedirect("/Asclepius/login.html");	
			return null;
		}
		//TODO:改成根据用户id获取并分页
		appointmentService.generateList(request, response, m, "appointments",userId);
		return m;
	}
	
	@RequestMapping("/appointmentDetail.html")
	public ModelAndView appointmentDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView m = new ModelAndView(appdetail);
		String s_appointmentid = (String)request.getParameter("appointmentId");
		String s_appointmentdetailid = (String)request.getParameter("appointmentDetailId");
		Appointment appointment;
		AppointmentDetail appointmentDetail;
		
		if(StringUtils.isBlank(s_appointmentdetailid)||StringUtils.isBlank(s_appointmentid))
		{
			response.sendRedirect("myAppointments.html");
			return null;
		}
		long appointmentId,appointmentDetailId;
		try{
			appointmentId = Long.parseLong(s_appointmentid);
			appointmentDetailId = Long.parseLong(s_appointmentdetailid);

			appointment = appointmentService.getAppointmentById(appointmentId);
			appointmentDetail = appointmentDetailService.getAppointmentById(appointmentDetailId);
		}catch(Exception e){
			response.sendRedirect("myAppointments.html");
			return null;
		}
		
		m.addObject("appointment",appointment);
		m.addObject("appointmentDetail",appointmentDetail);
		Doctor doctor = doctorService.getDoctorById(appointmentDetail.getDoctorId());
		m.addObject("doctor",doctor.getName());
		m.addObject("department",doctor.getDepartment().getDepartmentName());
		m.addObject("hospital",doctor.getDepartment().getHospital().getHospitalName());
		return m;
	}
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor = Exception.class)  
	@RequestMapping("/makeAnAppointment.html")
	public ModelAndView makdAnAppointment(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView m = new ModelAndView(makeAppointment);
		
		Date[] startAndEnd = TimeUtil.initDate();
		m.addObject("start",startAndEnd[0]);
		m.addObject("end",startAndEnd[1]);
		
		String s_doctorId = (String)request.getParameter("doctorId");
		String date = (String)request.getParameter("date");
		String time = (String)request.getParameter("time");
		
		long doctorId;
		try{
			doctorId = Long.parseLong(s_doctorId);
			
		}catch(Exception e){
			return m;
		}
		
		if(StringUtils.isBlank((String)request.getParameter("opType"))&&!(StringUtils.isBlank(s_doctorId)))
		{
			m.addObject("doctor",doctorService.getDoctorById(doctorId));
			return m;
		}
		
		if(StringUtils.isBlank((String)request.getParameter("opType"))||
				StringUtils.isBlank(date)||
				StringUtils.isBlank(time)){
			return m;
		}
		AppointmentDetail appointmentDetail;
		try{
			Doctor doctor = doctorService.getDoctorById(doctorId);
			appointmentDetail = appointmentDetailService.getAppointmentByConditions
					(doctor.getDepartment().getHospital().getHospitalId(), doctor.getDepartment().getDepartmentId(), doctorId,java.sql.Date.valueOf(date), time);
		}catch(Exception e){
			return m;
		}
		if(appointmentDetail == null){
			m.addObject("fieldErrors","没有此项");
			return m;
		}
		m.addObject("ammout",appointmentDetail.getAmount());
		if(appointmentDetail.getAmount()<=0){
			m.addObject("doctor",doctorService.getDoctorById(doctorId));
			m.addObject("fieldErrors","号完了");
			return m;
		}
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(UUID11.getRandomId());
		try{
			User currentUser = (User)request.getSession().getAttribute("userInSession");
			appointment.setUserId(currentUser.getId());
		}catch(Exception e){
			String redirectUrl = request.getRequestURL().toString();
			redirectUrl += "?doctorId=" + doctorId;
			System.out.println(redirectUrl);
			response.sendRedirect("../login.html?redirectUrl=" + URLEncoder.encode(redirectUrl,"UTF-8"));
			return null;
		}
		
		appointment.setAppointmentDetailId(appointmentDetail.getAppdetailId());
		try{
			appointment.setPatientAge(Integer.parseInt(((String)request.getParameter("patientAge"))));
		}catch(NumberFormatException e){
			appointment.setPatientAge(0);
		}
		appointment.setPatientName((String)request.getParameter("patientName"));
		appointment.setPatientSex((String)request.getParameter("patientSex"));
		appointment.setPatientInsuranceNo(StringUtils.isBlank((String)request.getParameter("patientInsuranceNo"))?"":(String)request.getParameter("patientInsuranceNo"));
		appointment.setTime(new Timestamp(System.currentTimeMillis()));
		appointment.setStatus(Appointment.WAITING_FOR_PAYING);
		
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
			appointmentDetail.setAmount(appointmentDetail.getAmount()-1);
			appointmentDetailService.updateAppointmentDetail(appointmentDetail);
		}catch(org.springframework.dao.DuplicateKeyException e){
			return makdAnAppointment(request,response);
		}
		return new ModelAndView(afterbook);
	}
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE,rollbackFor = Exception.class)
	@RequestMapping("/cancelAnAppointment.html")
	public ModelAndView cancelAnAppointment(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String s_appid = (String)request.getParameter("appointmentId");
		if(StringUtils.isBlank(s_appid)){
			System.out.println("blank");
			return account(request, response) ;
		}
		try{
			Appointment app = appointmentService.getAppointmentById(Long.parseLong(s_appid));
			AppointmentDetail appdetail = appointmentDetailService.getAppointmentById(app.getAppointmentDetailId());
			appdetail.setAmount(appdetail.getAmount()+1);
			appointmentDetailService.updateAppointmentDetail(appdetail);
			appointmentService.deleteAppointment(app.getAppointmentId());
		}catch(Exception e){
			System.out.println("exception");
			return account(request, response);
		}
		System.out.println("deleted");
		return account(request,response);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED) 
	@RequestMapping("/pay.html")
	public ModelAndView pay(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(pay);
		return m;
		//TODO:跳转到支付页面
	}
	
	@RequestMapping("/hospitalDetail.html")
	public ModelAndView hospitalDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView m = new ModelAndView(hosdetail);
		String s_hospitalId = (String)request.getParameter("hospitalId");
		if(StringUtils.isBlank(s_hospitalId)){
			response.sendRedirect("../general/hospitalList.html");
			return null;
		}
		long hospitalId = 0;
		try{
			hospitalId = Long.parseLong(s_hospitalId);
		}catch(Exception e){
			response.sendRedirect("../general/hospitalList.html");
			return null;
		}
		Hospital hospital = hospitalService.getHostpitalById(hospitalId);
		List<Department> departments = departmentService.getAllDepartmentsByHospital(hospitalId);
		m.addObject("hospital",hospital);
		m.addObject("departments",departments);
		return m;
	}
	
	@RequestMapping("/departmentDetail.html")
	public ModelAndView departmentDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView m = new ModelAndView(deptdetail);
		String s_departmentId = (String)request.getParameter("departmentId");
		String s_hospitalId = (String)request.getParameter("hospitalId");
		if(StringUtils.isBlank(s_departmentId)||StringUtils.isBlank(s_hospitalId)){
			response.sendRedirect("../general/hospitalList.html");
			return null;
		}
		long departmentId = 0;
		try{
			departmentId = Long.parseLong(s_departmentId);
		}catch(Exception e){
			response.sendRedirect("../general/hospitalList.html");
			return null;
		}
		Department department = departmentService.getDepartmentById(departmentId);
		List<Doctor> doctors = doctorService.getDoctorsByDepartment(departmentId);
		m.addObject("department",department);
		m.addObject("doctors",doctors);
		return m;
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED) 
	@RequestMapping("/payConfirm.html")
	public void payConfirmed(HttpServletRequest request,HttpServletResponse response) throws IOException{
		/*String trade_status = (String)request.getParameter("trade_status");//交易状态
		String trade_no = request.getParameter("trade_no"); // 支付宝交易号
        String order_no = request.getParameter("out_trade_no"); // 获取订单号
        String total_fee = request.getParameter("price"); // 获取总金额

        String subject = "";
        try {
        	subject = new String(request.getParameter("subject").getBytes(
                "ISO-8859-1"), "UTF-8");
        	} catch (UnsupportedEncodingException e1) {
        		// TODO Auto-generated catch block
        		e1.printStackTrace();
        		}// 商品名称、订单名称,保存用户id和预约id
      
        if (request.getParameter("body") != null) {
        	try {
        		String body = new String(request.getParameter("body").getBytes(
                                 "ISO-8859-1"), "UTF-8");
        		} catch (UnsupportedEncodingException e) {
        			e.printStackTrace();
        			}// 商品描述、订单备注、描述
                }
		if(trade_status.compareTo("WAIT_SELLER_SEND_GOODS") == 0 ||
				trade_status.compareTo("TRADE_FINISHED") == 0)*/
		
			String str = (String)request.getParameter("appointmentId");
			long appointmentId = Long.parseLong(str);
			Appointment app = appointmentService.getAppointmentById(appointmentId);
			app.setStatus(Appointment.WAITING_FOR_PRINTING);
			appointmentService.updateAppointment(app);
			
		
		response.sendRedirect("myAccount.html");
		return;
	}
}
