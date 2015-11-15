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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.logic.UserService;
import buaa.bp.asclepius.model.User;
import buaa.bp.asclepius.utils.UUID11;

@Controller
@RequestMapping("general")
public class GeneralServlet {
	
	private String register = "general/register";
	private String index = "index";
	
	@Autowired(required=false)
	private Validator validator;
	
	@Resource(name="userService")
	private UserService userService;
	
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
		userService.createUser(user);
		request.getSession().setAttribute("userInSession", user.getUserName());
		return new ModelAndView(index);
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
