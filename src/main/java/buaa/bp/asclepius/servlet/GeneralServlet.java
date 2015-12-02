package buaa.bp.asclepius.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import buaa.bp.asclepius.model.Message;
import buaa.bp.asclepius.model.User;
import buaa.bp.asclepius.utils.MailUtil;
import buaa.bp.asclepius.utils.UUID11;

@Controller
@RequestMapping("/general")
public class GeneralServlet {
	private static String register = "general/register";
	private static String list = "general/list";
	private static String message = "general/message";
	private static String activate ="general/activate";
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
		user.setEmail((String)request.getParameter("email"));
		user.setActiveFlag(0);
		user.setValidateCode(userService.getMD5(user.getEmail()));
		
		MailUtil mailUtil = new MailUtil();
		
		
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
			System.out.println(userService.getUserById(user.getId()));
		}catch(DuplicateKeyException e){
			return register(request,response);
		}
		mailUtil.send(user.getEmail(),user.getId(),user.getValidateCode());
		return new ModelAndView(activate);
	}
	
	@RequestMapping("/appointmentList.html")
	public ModelAndView appointmentList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(list);
		
		m.addObject("appointments",appointmentDetailService.generateList(request, response));
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
			return leaveAMessage(request,response);
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
}
