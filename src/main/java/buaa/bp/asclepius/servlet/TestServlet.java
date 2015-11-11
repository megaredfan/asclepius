package buaa.bp.asclepius.servlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import buaa.bp.asclepius.logic.*;

@Controller
public class TestServlet {

	@Resource(name="appointmentService")
	private AppointmentService appointmentService;
	
	@Resource(name="appointmentDetailService")
	private AppointmentDetailService appointmentDetailService;
	
	@Resource(name="creditService")
	private CreditService creditService;	
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	@Resource(name="doctorService")
	private DoctorService doctorService;	
	
	@Resource(name="hospitalService")
	private HospitalService hospitalService;	
	
	@Resource(name="systemAdminService")
	private SystemAdminService systemAdminService;	
	
	@Resource(name="userService")
	private UserService userservice;

	@RequestMapping("servicetest.html")
	public String service(HttpServletRequest request){
		return "success";
	}
}
