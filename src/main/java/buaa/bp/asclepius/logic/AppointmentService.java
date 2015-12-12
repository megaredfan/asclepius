package buaa.bp.asclepius.logic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.model.Appointment;
import buaa.bp.asclepius.mapper.AppointmentMapper;

@Service
public class AppointmentService extends GeneralService {
	
	@Resource(name="configLoader")
	private PropertiesConfiguration configLoader;
	
	@Resource(name="appointmentMapper")
	private AppointmentMapper appointmentMapper;
	
	public List<Appointment> getAllAppointments(long userId) {
		return appointmentMapper.getAllAppointments(userId);
	}
	public Appointment getAppointmentById(long userId,long appointmentId) {
		return appointmentMapper.getAppointmentById(userId, appointmentId);
	}
	public int createAppointment(Appointment appointment) {
		return appointmentMapper.createAppointment(appointment);
	}
	public int updateAppointment(Appointment appointment) {
		return appointmentMapper.updateAppointment(appointment);
	}
	public int deleteAppointment(long id) {
		return appointmentMapper.deleteAppointment(id);
	}
	public int count() {
		return appointmentMapper.count();
	}
	public List<?> selectByRange(int start,int length) {
		return appointmentMapper.selectByRange(start, length);
	}
	public void generateList(HttpServletRequest request,HttpServletResponse response,ModelAndView m,String listName){
		super.generateList(request, response,m,listName);
	}
	public void generateList(HttpServletRequest request,HttpServletResponse response,ModelAndView m,String listName,long userId){
		String s_pageNo = "0";
		int pageNo = 0;
		int pageSize = configLoader.getInt("page.pageSize");
		int totalPages = (count() + pageSize - 1) / pageSize;
		s_pageNo = (String)request.getParameter("pageNo");
		try{
			pageNo = Integer.parseInt(s_pageNo);
		}catch(Exception e){
			
		}
		if(pageNo < 0){
			pageNo = 0;
		}
		if(pageNo > totalPages){
			pageNo = totalPages-1;
		}
		
		List<Appointment> list = appointmentMapper.getAllAppointmetnsByRange(userId, pageNo * pageSize,pageSize);
		System.out.println(list);
		m.addObject(listName,list);
		m.addObject("pageNo",pageNo);
		m.addObject("totalPages",totalPages);
	}
}
