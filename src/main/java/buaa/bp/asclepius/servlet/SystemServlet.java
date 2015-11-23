package buaa.bp.asclepius.servlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.logic.AppointmentService;
import buaa.bp.asclepius.model.Appointment;

@Controller
@RequestMapping("/system")
public class SystemServlet {
	private String print = "system/print";
	private String status = "system/status";
	
	@Resource(name="appointmentService")
	private AppointmentService appointmentService;
	
	@RequestMapping("/print.html")
	public ModelAndView print(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(print);
		Appointment appointment = null;
		String opType = (String)request.getParameter("opType");
		if(StringUtils.isBlank(opType)){
			return m;
		}
		String s_userid = (String)request.getParameter("userId");
		String s_appid = (String)request.getParameter("appointmentId");
		try{
			appointment= appointmentService.getAppointmentById(Integer.parseInt(s_userid), Integer.parseInt(s_appid));
		}catch(Exception e){
			return m;
		}
		m = new ModelAndView(status);
		m.addObject("appointment",appointment);
		return m;
	}
}
