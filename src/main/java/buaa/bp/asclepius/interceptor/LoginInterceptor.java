package buaa.bp.asclepius.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import buaa.bp.asclepius.model.User;

public class LoginInterceptor  extends HandlerInterceptorAdapter{

	/**
	 * 初始化url地址
	 * @return
	 */
	private List<String> urlInitList(){
		List<String> list=new ArrayList<String>();
		list.add("");
		list.add("index");
		list.add("login.html");
		list.add("appointmentList.html");
		list.add("leaveAMessage.html");
		list.add("auth.html");
		list.add("index.html");
		list.add("register.html");
		return list;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		boolean handlerOk = super.preHandle(request, response, handler);
			if(handlerOk){
				String url = request.getRequestURI().toString();
				url=url.substring(url.lastIndexOf("/")+1);
				System.out.println(url);
				if(urlInitList().contains(url)){
					System.out.println("passed");
					return true;
				}
				HttpSession session = request.getSession();
				User user = (User)session.getAttribute("userInSession");
				if(user == null){
					System.out.println("redirected");
					response.sendRedirect("/Asclepius/login.html");
					return true;
				}
				//请求权限控制
				if(url.contains("index")){
					return true;
				}/*else{
					//获取请求action的name
					String actionName = request.getRequestURI();
					if(StringUtils.isBlank(actionName)){
						response.sendRedirect("login.html");
					}
					actionName = actionName.substring(actionName.lastIndexOf("/")+1);
					List<MenuList> permissionMenuList = (List)session.getAttribute("menuList");
					if(permissionMenuList != null){
						for(MenuList ml:permissionMenuList){
							if(ml.getUrl().compareTo(actionName) == 0){
								return true;
							}
						}
					}
					response.sendRedirect("login.lc");
					return true;
				}*/
			}
			return false;
		}
}
