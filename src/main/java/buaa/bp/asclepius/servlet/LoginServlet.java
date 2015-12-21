package buaa.bp.asclepius.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.logic.DepartmentService;
import buaa.bp.asclepius.logic.HospitalService;
import buaa.bp.asclepius.logic.UserService;
import buaa.bp.asclepius.model.Department;
import buaa.bp.asclepius.model.Hospital;
import buaa.bp.asclepius.model.User;
import buaa.bp.asclepius.utils.MailUtil;
import buaa.bp.asclepius.utils.UUID11;

@Controller
public class LoginServlet {
	
	private static final String login = "login";
	private static final String index = "index";
	private static final String activate = "activate";
	private static final String register = "register";
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="hospitalService")
	private HospitalService hospitalService;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	@Resource(name="validator")
	private Validator validator;
	
	@RequestMapping("/login.html")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView m = new ModelAndView(login);
		String userName = (String)request.getParameter("userName");
		String password = (String)request.getParameter("password");
		String opType = (String)request.getParameter("opType");
		String redirectUrl = (String)request.getParameter("redirectUrl");
		
		if(StringUtils.isBlank(opType))
		{
			System.out.println("StringUtils.isBlank(opType)");
			return m;
		}else if(opType.compareTo("logout")==0){
			request.getSession().setAttribute("userInSession", null);
			return m;
		}
		if(StringUtils.isBlank(userName)||StringUtils.isBlank(password))
		{
			System.out.println("StringUtils.isBlank(userName)||StringUtils.isBlank(password)");
			m.addObject("message","请输入用户名或密码！");
			return m;
		}
		if(!userService.authentication(userName, password))
		{
			System.out.println("用户名或密码错误！");
			m.addObject("message","用户名或密码错误！");
			return m;
		}
		User user = userService.getUserByName(userName);
		user.setLastLogin(new Timestamp(System.currentTimeMillis()));
		userService.updateUser(user);
		request.getSession().setAttribute("userInSession", user);
		if(StringUtils.isBlank(redirectUrl))
		{
			response.sendRedirect("registed/myAccount.html");
			return null;
		}
		else
		{
			response.sendRedirect(URLDecoder.decode(redirectUrl,"UTF-8"));
			return null;
		}
		
	}
	
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
	
	@RequestMapping("/index")
	public ModelAndView index(){
		List<Hospital> hospitals = hospitalService.getTopHospitals(4);
		List<Department> departments = departmentService.getTopDepartments(4);
		ModelAndView m = new ModelAndView(index);
		m.addObject("hospitals",hospitals);
		m.addObject("departments",departments);
		return m;
	}
	
	@RequestMapping("/about.html")
	public ModelAndView about(){
		return new ModelAndView("about");
	}
	
	@RequestMapping("/checkUserName.html")
	public void checkUserName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String userName = (String)request.getParameter("userName");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		if(StringUtils.isBlank(userName)){
			pw.write("<span style=\"color:red\">用户名为空！</span>");
			pw.flush();
			pw.close();
			return;
		}else if(userService.getUserByName(userName)!=null){
			pw.write("<span style=\"color:red\">用户名已存在！</span>");
			pw.flush();
			pw.close();
			return;
		}else{
			pw.write("<span style=\"color:green\">你可以使用该用户名！</span>");
			pw.flush();
			pw.close();
			return;
		}
		
	}
}
