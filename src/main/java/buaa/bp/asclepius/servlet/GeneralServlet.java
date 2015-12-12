package buaa.bp.asclepius.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import buaa.bp.asclepius.logic.AppointmentDetailService;
import buaa.bp.asclepius.logic.DepartmentService;
import buaa.bp.asclepius.logic.HospitalService;
import buaa.bp.asclepius.logic.MessageService;
import buaa.bp.asclepius.logic.UserService;
import buaa.bp.asclepius.model.Message;
import buaa.bp.asclepius.model.User;
import buaa.bp.asclepius.utils.UUID11;


@Controller
@RequestMapping("/general")
public class GeneralServlet {
	
	private static final String hoslist = "general/hoslist";
	private static final String deptlist = "general/deptlist";
	private static final String message = "general/messages";
	private static final String activate = "general/activate";
	private static final String index = "index";
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="appointmentDetailService")
	private AppointmentDetailService appointmentDetailService;
	
	@Resource(name="messageService")
	private MessageService messageService;
	
	@Resource(name="hospitalService")
	private HospitalService hospitalService;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	@Resource(name="configLoader")
	private PropertiesConfiguration configLoader;
	
	
	
	@RequestMapping("/hospitalList.html")
	public ModelAndView hospitalList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(hoslist);
		hospitalService.generateList(request, response, m, "hospitals");
		return m;
	}
	
	@RequestMapping("/departmentList.html")
	public ModelAndView departmentList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(deptlist);
		String s_hospitalid = (String)request.getParameter("hospitalId");
		if(StringUtils.isBlank(s_hospitalid)){
			departmentService.generateList(request, response, m, "departments");
			return m;
		}
		long hospitalId;
		try{
			hospitalId = Long.parseLong(s_hospitalid);
		}catch(Exception e){
			return new ModelAndView("index");
		}
		departmentService.generateList(request, response, m, "departments",hospitalId);
		return m;
	}
	
	@RequestMapping("/leaveAMessage.html")
	public ModelAndView leaveAMessage(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(message);
		String opType=(String)request.getParameter("opType");
		if(StringUtils.isBlank(opType))
		{
			messageService.generateList(request, response, m, "messages");
			return m;
		}
		Message message = new Message();
		message.setId(UUID11.getRandomId());
		message.setContent((String)request.getParameter("content"));
		if(request.getSession().getAttribute("userInSession")!=null){
			message.setAuthor(((User)request.getSession().getAttribute("userInSession")).getUserName());
		}else{
			message.setAuthor("游客");
		}
		message.setCreateTime(new Timestamp(System.currentTimeMillis()));		
		message.setPid(0L);
		try{
			messageService.createMessage(message);
		}catch(DuplicateKeyException e){
			return leaveAMessage(request,response);
		}
		
		messageService.generateList(request, response, m, "messages");
		return m;
	}

	@RequestMapping("/userActivate.html")
	public ModelAndView userActivate(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(activate);
		StringBuffer requesturl = request.getRequestURL();
		System.out.println(requesturl.toString());
		String validateCode = (String)request.getParameter("validateCode");
		String s_userid = (String)request.getParameter("userId");
		System.out.println(validateCode+" "+s_userid);
		if(StringUtils.isBlank(validateCode)||StringUtils.isBlank(s_userid)){
			m.addObject("status",0);
			m.addObject("message","激活失败！请重新注册");
		}
		try {
			validateCode = URLDecoder.decode(validateCode,"UTF-8");
			System.out.println("in url:" + validateCode);
		} catch (UnsupportedEncodingException e1) {
			System.out.println("不支持的编码方式！");
		}
		try{
			long userId = Long.parseLong(s_userid);
			User user = userService.getUserById(userId);
			System.out.println("in table" + user.getValidateCode());
			if(user.getValidateCode().compareTo(validateCode)==0){
				user.setActiveFlag(1);
				userService.updateUser(user);
				m.addObject("status",1);
				m.addObject("message","激活成功！");
			}
			else{
				m.addObject("status",0);
				m.addObject("message","激活失败！请重新注册");
			}
		}catch(Exception e){
			m.addObject("status",0);
			m.addObject("message","激活失败！请重新注册");
		}
		return m;
	}
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		return new ModelAndView(index);
	}
}
