package buaa.bp.asclepius.servlet;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.logic.AppointmentDetailService;
import buaa.bp.asclepius.logic.MessageService;
import buaa.bp.asclepius.logic.SystemAdminService;
import buaa.bp.asclepius.logic.UserService;
import buaa.bp.asclepius.model.Message;
import buaa.bp.asclepius.utils.UUID11;

@Controller
@RequestMapping("/admin")
public class AdminServlet {
	
	
	private String admin = "admin/index";
	private String login = "admin/login";
	private String message = "admin/message";
	private String userlist = "admin/users";
	private String useredit ="admin/useredit";
	private String applist = "admin/applist";
	
	@Resource(name="messageService")
	private MessageService messageService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="systemAdminService")
	private SystemAdminService systemAdminService;
	
	@Resource(name="appointmentDetailService")
	private AppointmentDetailService appointmentDetailService;
	
	@RequestMapping("/index.html")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(admin);
		if(request.getSession().getAttribute("adminInSession")==null){
			return new ModelAndView(login);
		}
		return m;
	}
	
	@RequestMapping("/auth.html")
	public ModelAndView auth(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(login);
		String userName = (String)request.getParameter("userName");
		String password = (String)request.getParameter("password");
		
		
		if(StringUtils.isBlank(userName)||StringUtils.isBlank(password)){
			m.addObject("message","用户名或密码为空！");
			return m;
		}
		if(!systemAdminService.authentication(userName, password)){
			m.addObject("message","用户名或密码错误!");
			return m;
		}
		request.getSession().setAttribute("adminInSession",userName);
		return new ModelAndView(admin);
	}
	
	@RequestMapping("/reply.html")
	public ModelAndView reply(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(message);
		String opType=(String)request.getParameter("opType");
		if(StringUtils.isBlank(opType))
		{
			return m;
		}
		Message message = new Message();
		String s_pid = (String)request.getParameter("pid");
		try{
			message.setPid(Long.parseLong(s_pid));
			message.setId(UUID11.getRandomId());
			message.setAuthor(((String)request.getSession().getAttribute("adminInSession")));
			message.setContent((String)request.getParameter("content"));
			message.setCreateTime(new Timestamp(System.currentTimeMillis()));
			
			messageService.createMessage(message);
		}catch(NumberFormatException | DuplicateKeyException e){
			return m;
		}
		//TODO:添加分页实现
		return m;
	}
	
	@RequestMapping("userList.html")
	public ModelAndView userList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(userlist);
		List<?> list = userService.getAllUsers();
		//TODO:添加分页实现
		m.addObject("users",list);
		return m;
	}
	
	@RequestMapping("userEdit.html")
	public ModelAndView userEdit(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(useredit);
		//TODO:添加实现
		return m;
	}
	
	@RequestMapping("appointmentDetailList.html")
	public ModelAndView appointmentDetailList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(applist);
		List<?> list = appointmentDetailService.getAvailableAppointments();
		//TODO:添加分页实现
		m.addObject("appointments",list);
		return m;
	}
	
	@RequestMapping("addNewAppointment.html")
	public ModelAndView addNewAppointment(HttpServletRequest request,HttpServletResponse response){
		//TODO:初始化appointmentDetail并添加到数据库
		//实现可自动化发布
		return appointmentDetailList(request,response);
			
	}

}
