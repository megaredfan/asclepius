package buaa.bp.asclepius.servlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import buaa.bp.asclepius.logic.UserService;

@Controller
public class LoginServlet {
	
	private String login = "login";
	private String index = "index";
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("/login.html")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(login);
		String userName = (String)request.getParameter("userName");
		String password = (String)request.getParameter("password");
		String opType = (String)request.getParameter("opType");
		if(StringUtils.isBlank(opType))
		{
			System.out.println("StringUtils.isBlank(opType)");
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
		request.getSession().setAttribute("userInSession", userService.getUserByName(userName));
		return new ModelAndView(index);
		
	}
	
	@RequestMapping("/index")
	public ModelAndView index(){
		return new ModelAndView(index);
	}

}
