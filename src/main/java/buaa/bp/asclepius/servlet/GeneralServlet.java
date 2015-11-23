package buaa.bp.asclepius.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.logic.AppointmentDetailService;
import buaa.bp.asclepius.logic.MessageService;
import buaa.bp.asclepius.logic.UserService;
import buaa.bp.asclepius.model.AppointmentDetail;
import buaa.bp.asclepius.model.Message;
import buaa.bp.asclepius.model.User;
import buaa.bp.asclepius.utils.UUID11;

@Controller
@RequestMapping("/general")
public class GeneralServlet {
	private String register = "general/register";
	private String index = "index";
	private String list = "general/list";
	private String message = "general/message";
	
	@Resource(name="validator")
	private Validator validator;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="appointmentDetailService")
	private AppointmentDetailService appointmentDetailService;
	
	@Resource(name="messageService")
	private MessageService messageService;
	
	@Resource(name="configLoader")
	private PropertiesConfiguration configLoader;
	
	@RequestMapping("/register.html")
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(register);
		String opType=(String)request.getParameter("opType");
		if(StringUtils.isBlank(opType))
		{
			return m;
		}
		
		User user = new User();
		user.setId(UUID11.getRandomId());
		user.setUserName((String)request.getParameter("userName"));
		user.setPassword((String)request.getParameter("password"));
		user.setRealName((String)request.getParameter("realName"));
		user.setSex((String)request.getParameter("sex"));
		user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
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
		try{
			userService.createUser(user);
		}catch(DuplicateKeyException e){
			register(request,response);
		}
		request.getSession().setAttribute("userInSession", user.getUserName());
		return new ModelAndView(index);
	}
	
	@RequestMapping("/appointmentList.html")
	public ModelAndView appointmentList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(list);
		
		String s_pageNo = "0";
		int pageNo = 0;
		int pageSize = configLoader.getInt("page.pageSize");
		int totalPages = (appointmentDetailService.count() + pageSize - 1) / pageSize;
		s_pageNo = (String)request.getParameter("pageNo");
		try{
			pageNo = Integer.parseInt(s_pageNo);
		}catch(Exception e){
			
		}
		if(pageNo < 0){
			pageNo = 0;
		}
		if(pageNo > totalPages){
			pageNo = totalPages;
		}
		List<AppointmentDetail> list = appointmentDetailService.getAvailableAppointmentsByRange(pageNo * pageSize,pageSize);
		m.addObject("appoinments",list);
		return m;
	}
	
	@RequestMapping("/leaveAMessage.html")
	public ModelAndView leaveAMessage(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(message);
		String opType=(String)request.getParameter("opType");
		if(StringUtils.isBlank(opType))
		{
			return m;
		}
		Message message = new Message();
		message.setId(UUID11.getRandomId());
		message.setContent((String)request.getParameter("content"));
		if(request.getSession().getAttribute("userInSession")!=null){
			message.setAuthor((String)request.getSession().getAttribute("userInSession"));
		}else{
			message.setAuthor("游客");
		}
		message.setCreateTime(new Timestamp(System.currentTimeMillis()));		
		message.setPid(0L);
		try{
			messageService.createMessage(message);
		}catch(DuplicateKeyException e){
			leaveAMessage(request,response);
		}
		return m;
	}
	
	@RequestMapping("/checkUserName.html")
	public void checkUserName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String userName = (String)request.getParameter("userName");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		if(StringUtils.isBlank(userName)){
			pw.write("<span style=\"color:red\">用户名为空！</span>");
			pw.flush();
			return;
		}else if(userService.getUserByName(userName)!=null){
			pw.write("<span style=\"color:red\">用户名已存在！</span>");
			pw.flush();
			return;
		}else{
			pw.write("<span style=\"color:green\">你可以使用该用户名！</span>");
			pw.flush();
			return;
		}
		
	}
}
