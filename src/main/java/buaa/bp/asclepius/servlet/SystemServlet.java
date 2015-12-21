package buaa.bp.asclepius.servlet;

import java.sql.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.logic.AppointmentDetailService;
import buaa.bp.asclepius.logic.AppointmentService;
import buaa.bp.asclepius.logic.DepartmentService;
import buaa.bp.asclepius.logic.DoctorService;
import buaa.bp.asclepius.logic.HospitalService;
import buaa.bp.asclepius.model.Appointment;
import buaa.bp.asclepius.model.AppointmentDetail;
import buaa.bp.asclepius.model.Department;
import buaa.bp.asclepius.model.Doctor;
import buaa.bp.asclepius.model.Hospital;

@Controller
@RequestMapping("/system")
public class SystemServlet {
	private String print = "system/print";
	private String status = "system/status";
	
	@Resource(name="appointmentService")
	private AppointmentService appointmentService;
	
	@Resource(name="doctorService")
	private DoctorService doctorService;

	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	@Resource(name="hospitalService")
	private HospitalService hospitalService;
	
	@Resource(name="appointmentDetailService")
	private AppointmentDetailService appointmentDetailService;
	
	@RequestMapping("/print.html")
	public ModelAndView print(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(print);
		Appointment appointment;
		AppointmentDetail appointmentDetail;
		Doctor doctor;
		Department department;
		Hospital hospital;
		String opType = (String)request.getParameter("opType");
		if(StringUtils.isBlank(opType)){
			return m;
		}
		String s_appid = (String)request.getParameter("appointmentId");
		try{
			appointment = appointmentService.getAppointmentById(Long.parseLong(s_appid));
			appointmentDetail = appointmentDetailService.getAppointmentById(appointment.getAppointmentDetailId());
			doctor = doctorService.getDoctorById(appointmentDetail.getDoctorId());
			department = doctor.getDepartment();
			hospital = department.getHospital();
		}catch(Exception e){
			m.addObject("message","打印失败");
			return m;
		}
		if(appointment==null||
				appointmentDetail==null||
				doctor==null||
				department==null||
				hospital==null)
		{
			m.addObject("message","打印失败");
			return m;
		}
		if(appointment.getStatus()==Appointment.WAITING_FOR_PRINTING&&
				!appointmentDetail.getDate().before(new Date(System.currentTimeMillis()))){
			m = new ModelAndView(status);
			m.addObject("doctor",doctor.getName());
			m.addObject("department",department.getDepartmentName());
			m.addObject("hospital",hospital.getHospitalName());
			m.addObject("appointment",appointment);
			m.addObject("appointmentDetail",appointmentDetail);
			return m;
		}
		m.addObject("message","打印失败");
		return m;
	}
}
