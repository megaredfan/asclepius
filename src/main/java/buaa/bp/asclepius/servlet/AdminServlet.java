package buaa.bp.asclepius.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import buaa.bp.asclepius.logic.AppointmentDetailService;
import buaa.bp.asclepius.logic.DepartmentService;
import buaa.bp.asclepius.logic.DoctorService;
import buaa.bp.asclepius.logic.HospitalService;
import buaa.bp.asclepius.logic.MessageService;
import buaa.bp.asclepius.logic.SystemAdminService;
import buaa.bp.asclepius.logic.UserService;
import buaa.bp.asclepius.model.AutoAppointment;
import buaa.bp.asclepius.model.Department;
import buaa.bp.asclepius.model.Doctor;
import buaa.bp.asclepius.model.Hospital;
import buaa.bp.asclepius.model.Message;
import buaa.bp.asclepius.utils.UUID11;

@Controller
@RequestMapping("/admin")
public class AdminServlet {
	
	
	private static final String admin = "admin/index";
	private static final String login = "admin/login";
	private static final String message = "admin/messages";
	private static final String reply = "admin/reply";
	private static final String applist = "admin/applist";
	private static final String appedit = "admin/appedit";
	private static final String deptlist = "admin/deptlist";
	private static final String deptedit = "admin/deptedit";
	private static final String doctorlist = "admin/doctorlist";
	private static final String doctorEdit = "admin/doctoredit";
	private static final String hosList = "admin/hoslist";
	private static final String hosEdit = "admin/hosedit";
	private static final String userlist = "admin/users";
	
	
	@Resource(name="messageService")
	private MessageService messageService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="configLoader")
	private PropertiesConfiguration configLoader;
	
	@Resource(name="systemAdminService")
	private SystemAdminService systemAdminService;
	
	@Resource(name="appointmentDetailService")
	private AppointmentDetailService appointmentDetailService;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	@Resource(name="doctorService")
	private DoctorService doctorService;
	
	@Resource(name="hospitalService")
	private HospitalService hospitalService;
	
	@Resource(name="validator")
	private Validator validator;
	
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
		String action = (String)request.getParameter("action");
		if(!StringUtils.isBlank(action)&&action.compareTo("logout")==0)
		{
			return m;
		}
		
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
	
	@RequestMapping("/messages.html")
	public ModelAndView messages(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(message);		
		m.addObject("messages",messageService.getUnReplyedMessages());
		return m;
	}
	
	@RequestMapping("/reply.html")
	public ModelAndView reply(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView m = new ModelAndView(reply);
		String s_id = (String)request.getParameter("id");
		String opType=(String)request.getParameter("opType");
		long id;
		if(StringUtils.isBlank(opType))
		{
			try{
				id = Long.parseLong(s_id);
				Message msg = messageService.getMessageById(id);
				m.addObject("message",msg);
			}catch(Exception e){
				response.sendRedirect("messages.html");
				return null;
			}
		}
		if(!StringUtils.isBlank(opType))
		{
			Message message = new Message();
			String s_pid = (String)request.getParameter("pid");
			try{
				message.setPid(Long.parseLong(s_pid));
				message.setId(UUID11.getRandomId());
				message.setAuthor(((String)request.getSession().getAttribute("adminInSession")));
				message.setContent((String)request.getParameter("content"));
				message.setCreateTime(new Timestamp(System.currentTimeMillis()));
				messageService.createMessage(message);
				return messages(request,response);
			}catch(NumberFormatException | DuplicateKeyException e){
				return messages(request,response);
			}
		}
		return m;
	}
		
	@RequestMapping("/appointmentDetailList.html")
	public ModelAndView appointmentDetailList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(applist);
		appointmentDetailService.generateList(request, response, m, "appointments");
		return m;
	}
	
	@RequestMapping("/appointmentPoolEdit.html")
	public ModelAndView appointmentPoolEdit(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = null;
		String opType = (String)request.getParameter("opType");
		String s_hos = (String)request.getParameter("hospitalId");
		String s_dep = (String)request.getParameter("departmentId");
		
		if(StringUtils.isBlank(opType))
		{
			if(StringUtils.isBlank(s_hos)&&StringUtils.isBlank(s_dep)){
				m = new ModelAndView(appedit); 
				m.addObject("hospitals",hospitalService.getAllHospitals());
				System.out.println("hos & dep is blank.");
				return m;
			}
			if(StringUtils.isBlank(s_dep)&&!StringUtils.isBlank(s_hos)){
				m = new ModelAndView("admin/deptoption");
				System.out.println("dep is blank.");
				try{
					m.addObject("departments",departmentService.getAllDepartmentsByHospital(Long.parseLong(s_hos)));
				}catch(NumberFormatException e){
					m.addObject("hospitals",hospitalService.getAllHospitals());
				}catch(Exception e){
					m = new ModelAndView(appedit);
					m.addObject("hospitals",hospitalService.getAllHospitals());
					System.out.println("dep is blank.but there is a exception.");
				}
				return m;
			}
			if(!StringUtils.isBlank(s_dep)&&StringUtils.isBlank(s_hos)){
				m = new ModelAndView("admin/docoption");
				System.out.println("hos is blank.");
				try{
					m.addObject("doctors",doctorService.getDoctorsByDepartment(Long.parseLong(s_dep)));
				}catch(NumberFormatException e){
					m.addObject("hospitals",hospitalService.getAllHospitals());
				}catch(Exception e){
					m = new ModelAndView(appedit);
					m.addObject("hospitals",hospitalService.getAllHospitals());
					System.out.println("hos is blank.but there is a exception.");
				}
				return m;
			}
		}
		if(!StringUtils.isBlank(opType)){
			System.out.println("opType = edit");
			m = new ModelAndView(appedit);
			String s_doc = (String)request.getParameter("doctorId");
			String s_day = (String)request.getParameter("day");
			String s_time = (String)request.getParameter("time");
			String s_amo = (String)request.getParameter("amount");
			String s_cost = (String)request.getParameter("cost");
			long hospitalId = 0,departmentId = 0,doctorId = 0;
			String day = s_day,time = s_time;
			int amount = 0;
			double cost = 1.0;
			if(StringUtils.isBlank(s_dep)||
					StringUtils.isBlank(s_hos)||
					StringUtils.isBlank(s_doc)||
					StringUtils.isBlank(s_day)||
					StringUtils.isBlank(s_time)||
					StringUtils.isBlank(s_amo)||
					StringUtils.isBlank(s_cost)){
				m.addObject("hospitals",hospitalService.getAllHospitals());
				System.out.println("some parameter is null.");
				return m;
			}else{
				try{
					hospitalId = Long.parseLong(s_hos);
					departmentId = Long.parseLong(s_dep);
					doctorId = Long.parseLong(s_doc);
					amount = Integer.parseInt(s_amo);
					cost = Double.parseDouble(s_cost);
					if(cost<1.0)
						cost=1.0;
					if(amount<0){
						System.out.println("amount<0.");
						return m;
					}
				}catch(NumberFormatException e){
					System.out.println("NumberFormatException.");
					return m;
				}
				AutoAppointment app = appointmentDetailService.selectByConditions(hospitalId, departmentId, doctorId, day, time);
				app.setAmount(amount);
				app.setCost(cost);
				appointmentDetailService.updateAutoAppointment(app);
			}
			m.addObject("hospitals",hospitalService.getAllHospitals());
		}
		System.out.println("finished.");
		return m;
	}
	
	@RequestMapping("/departmentList.html")
	public ModelAndView departmentList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(deptlist);
		return m;
	}

	@RequestMapping("/departmentEdit.html")
	public ModelAndView departmentEdit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView m = new ModelAndView(deptedit);
		m.addObject("hospitals",hospitalService.getAllHospitals());
		Department department = new Department();
		
		String opType = (String)request.getParameter("opType");
		String s_departmentId = (String)request.getParameter("departmentId");
		String s_hospitalId = (String)request.getParameter("hospitalId");
		String action = (String)request.getParameter("action");
		
		if(!StringUtils.isBlank(action))
		{

			if(action.compareTo("edit")==0)
			{
				try{
					Department dept = departmentService.getDepartmentById(Long.parseLong(s_departmentId));
					m.addObject("department",dept);
					m.addObject("belongsHospital",dept.getHospital());
				}catch(Exception e){
					response.sendRedirect("departmentList.html");
					return null;
				}
			}
			return m;
		}
		
		if(StringUtils.isBlank(opType)&&StringUtils.isBlank(s_departmentId)&&!StringUtils.isBlank(s_hospitalId))
		{
			try{
				department = departmentService.getDepartmentById(Long.parseLong(s_departmentId));
				m.addObject("department",department);
			}catch(Exception e){
				response.sendRedirect("departmentList.html");
				return null;
			}
		}
		
		if(!StringUtils.isBlank(opType)&&!StringUtils.isBlank(s_hospitalId))
		{
			if(opType.compareTo("edit")==0&&!StringUtils.isBlank(s_departmentId))
			{
					try{
						department = departmentService.getDepartmentById(Long.parseLong(s_departmentId));
						department.setHospital(hospitalService.getHostpitalById(Long.parseLong(s_hospitalId)));
					}catch(Exception e){
						response.sendRedirect("departmentList.html");
						return null;
					}
					department.setDepartmentName((String)request.getParameter("departmentName"));
					department.setDescription((String)request.getParameter("description"));
					Set<ConstraintViolation<Department>> constraintViolations = validator.validate(department);
					if(constraintViolations.size() > 0){
						List<String> fieldErrors = new ArrayList<String>();
						for(ConstraintViolation<Department> c : constraintViolations){
							fieldErrors.add(c.getMessage());
						}
						System.out.println(fieldErrors.size());
						m.addObject("fieldErrors",fieldErrors);
						return m;
					}
					departmentService.updateDepartment(department);
				
				
			}else if(opType.compareTo("add")==0) {
				department.setDepartmentId(UUID11.getRandomId());
				try {
					department.setHospital(hospitalService.getHostpitalById(Long.parseLong(s_hospitalId)));
				} catch (Exception e) {
					return m;
				}
				department.setDepartmentName((String)request.getParameter("departmentName"));
				department.setDescription((String)request.getParameter("description"));
				Set<ConstraintViolation<Department>> constraintViolations = validator.validate(department);
				if(constraintViolations.size() > 0){
					List<String> fieldErrors = new ArrayList<String>();
					for(ConstraintViolation<Department> c : constraintViolations){
						fieldErrors.add(c.getMessage());
					}
					System.out.println(fieldErrors.size());
					m.addObject("fieldErrors",fieldErrors);
					return m;
				}
				try{
					departmentService.createDepartment(department);
					
				}catch(DuplicateKeyException e){
					departmentEdit(request,response);
				}
			}
			
		}
		
		response.sendRedirect("departmentList.html");
		return null;
	}

	@RequestMapping("/departmentDelete.html")
	public ModelAndView departmentDelete(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String s_departmentid = (String)request.getParameter("departmentId");
		if(StringUtils.isBlank(s_departmentid))
		{
			response.sendRedirect("departmentList.html");
			return null;
		}	
		long departmentId;
		try{
			departmentId = Long.parseLong(s_departmentid);
			departmentService.deleteDepartment(departmentId);
		}catch(Exception e){
			response.sendRedirect("departmentList.html");
			return null;
		}
		response.sendRedirect("departmentList.html");
		return null;
	}

	@RequestMapping("/hospitalList.html")
	public ModelAndView hospitalList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(hosList);

		hospitalService.generateList(request, response, m, "hospitals");
		return m;
	}

	@RequestMapping("/hospitalEdit.html")
	public ModelAndView hospitalEidt(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView m = new ModelAndView(hosEdit);
		Hospital hospital = new Hospital();
		
		String opType = (String)request.getParameter("opType");
		String s_hospitalId = (String)request.getParameter("hospitalId");
		String action = (String)request.getParameter("action");
		
		if(!StringUtils.isBlank(action)){
			if(action.compareTo("edit")!=0){
				return m;
			}
			try{
				hospital = hospitalService.getHostpitalById(Long.parseLong(s_hospitalId));
				m.addObject("hospital",hospital);
			}catch(Exception e){
				
			}
			return m;
		}
		
		if(StringUtils.isBlank(opType)&&StringUtils.isBlank(s_hospitalId))
		{
			try{
				hospital = hospitalService.getHostpitalById(Long.parseLong(s_hospitalId));
				m.addObject("hospital",hospital);
			}catch(Exception e){
				response.sendRedirect("hospitalList.html");
				return null;
			}
		}
		
		if(!StringUtils.isBlank(opType))
		{
			if(opType.compareTo("edit")==0&&!StringUtils.isBlank(s_hospitalId))
			{
					try{
						hospital = hospitalService.getHostpitalById(Long.parseLong(s_hospitalId));
					}catch(Exception e){
						response.sendRedirect("hospitalList.html");
						return null;
					}
					
					hospital.setHospitalName(request.getParameter("hospitalName"));
					hospital.setDescription(request.getParameter("description"));
					
					Set<ConstraintViolation<Hospital>> constraintViolations = validator.validate(hospital);
					if(constraintViolations.size() > 0){
						List<String> fieldErrors = new ArrayList<String>();
						for(ConstraintViolation<Hospital> c : constraintViolations){
							fieldErrors.add(c.getMessage());
						}
						System.out.println(fieldErrors.size());
						m.addObject("fieldErrors",fieldErrors);
						return m;
					}
					hospitalService.updateHospital(hospital);
				
				
			}else if(opType.compareTo("add")==0) {
				hospital.setHospitalId(UUID11.getRandomId());
				hospital.setHospitalName(request.getParameter("hospitalName"));
				hospital.setDescription(request.getParameter("description"));
				Set<ConstraintViolation<Hospital>> constraintViolations = validator.validate(hospital);
				if(constraintViolations.size() > 0){
					List<String> fieldErrors = new ArrayList<String>();
					for(ConstraintViolation<Hospital> c : constraintViolations){
						fieldErrors.add(c.getMessage());
					}
					System.out.println(fieldErrors.size());
					m.addObject("fieldErrors",fieldErrors);
					return m;
				}
				try{
					hospitalService.createHospital(hospital);
				}catch(DuplicateKeyException e){
					hospitalEidt(request, response);
				}
			}
		}
		response.sendRedirect("hospitalList.html");
		return null;
	}
	
	@RequestMapping("/hospitalDelete.html")
	public ModelAndView hospitalDelete(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String s_hospitalId = (String)request.getParameter("hospitalId");
		if(StringUtils.isBlank(s_hospitalId))
		{
			response.sendRedirect("hospitalList.html");
			return null;
		}	
		long hospitalId;
		try{
			hospitalId = Long.parseLong(s_hospitalId);
			hospitalService.deleteHospital(hospitalId);
		}catch(Exception e){
			response.sendRedirect("hospitalList.html");
			return null;
		}
		response.sendRedirect("hospitalList.html");
		return null;
	}

	@RequestMapping("/doctorList.html")
	public ModelAndView doctorList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(doctorlist);
		return m;
	}

	@RequestMapping("/doctorEdit.html")
	public ModelAndView doctorEdit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView m = new ModelAndView(doctorEdit);
		Doctor doctor = new Doctor();
		long departmentId,hospitalId;
		
		m.addObject("hospitals",hospitalService.getAllHospitals());
		m.addObject("departments",departmentService.getAllDepartments());
		
		String opType = (String)request.getParameter("opType");
		String action = (String)request.getParameter("action");
		String s_doctorId = (String)request.getParameter("doctorId");
		String s_departmentId = (String)request.getParameter("departmentId");
		String s_hospitalId = (String)request.getParameter("hospitalId");
		String option = (String)request.getParameter("get");
		if(!StringUtils.isBlank(option)&&!StringUtils.isBlank(s_hospitalId))
		{
			m = new ModelAndView("admin/deptoption");
			try{
				m.addObject("departments",departmentService.getAllDepartmentsByHospital(Long.parseLong(s_hospitalId)));
			}catch(Exception e){
				
			}
			
			return m;
		}
		
		if(!StringUtils.isBlank(action))
		{
			if(action.compareTo("edit")!=0)
			{
				return m;
			}
			try{
				doctor = doctorService.getDoctorById(Long.parseLong(s_doctorId));
				m.addObject("doctor",doctor);
				m.addObject("belongsDepartment",doctor.getDepartment());
			}catch(Exception e){
				
			}
			return m;
		}
		
		if(!StringUtils.isBlank(opType))
		{
			
			try{
				departmentId = Long.parseLong(s_departmentId);
				hospitalId = Long.parseLong(s_hospitalId);
			}catch(Exception e){
				response.sendRedirect("doctorList.html");
				return null;
			}
			Department department = departmentService.getDepartmentById(departmentId);
			Hospital hospital = hospitalService.getHostpitalById(hospitalId);
			if(department==null||hospital==null)
			{
				m.addObject("fieldErrors","医院或科室不存在");
				return m;
			}
			
			if(opType.compareTo("edit")==0&&!StringUtils.isBlank(s_doctorId))
			{
				doctor = doctorService.getDoctorById(Long.parseLong(s_doctorId));
				department.setHospital(hospital);
				doctor.setDepartment(department);
				doctor.setDescription((String)request.getParameter("description"));
				doctor.setLevel((String)request.getParameter("level"));
				doctor.setName((String)request.getParameter("name"));
				doctor.setSex((String)request.getParameter("sex"));
				Set<ConstraintViolation<Doctor>> constraintViolations = validator.validate(doctor);
				if(constraintViolations.size() > 0){
					List<String> fieldErrors = new ArrayList<String>();
					for(ConstraintViolation<Doctor> c : constraintViolations){
						fieldErrors.add(c.getMessage());
					}
					System.out.println(fieldErrors.size());
					m.addObject("fieldErrors",fieldErrors);
					return m;
				}
				doctorService.updateDoctor(doctor);
			
			}else if(opType.compareTo("add")==0) {
				doctor.setDoctorId(UUID11.getRandomId());
				department.setHospital(hospital);
				doctor.setDepartment(department);
				doctor.setDescription((String)request.getParameter("description"));
				doctor.setLevel((String)request.getParameter("level"));
				doctor.setName((String)request.getParameter("name"));
				doctor.setSex((String)request.getParameter("sex"));
				Set<ConstraintViolation<Doctor>> constraintViolations = validator.validate(doctor);
				if(constraintViolations.size() > 0){
					List<String> fieldErrors = new ArrayList<String>();
					for(ConstraintViolation<Doctor> c : constraintViolations){
						fieldErrors.add(c.getMessage());
					}
					System.out.println(fieldErrors.size());
					m.addObject("fieldErrors",fieldErrors);
					return m;
				}
				try{
					doctorService.createDoctor(doctor);
					
				}catch(DuplicateKeyException e){
					doctorEdit(request,response);
				}
			}
			
		}
		response.sendRedirect("doctorList.html");
		return null;
	}
	
	@RequestMapping("/doctorDelete.html")
	public ModelAndView doctorDelete(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String s_doctorId = (String)request.getParameter("doctorId");
		if(StringUtils.isBlank(s_doctorId))
		{
			return departmentList(request, response);
		}	
		long doctorId;
		try{
			doctorId = Long.parseLong(s_doctorId);
			doctorService.deleteDoctor(doctorId);
		}catch(Exception e){
			
		}
		response.sendRedirect("doctorList.html");
		return null;
	}
	
	@RequestMapping("/userList.html")
	public ModelAndView userList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(userlist);
		return m;
	}
	
	/*@RequestMapping("userEdit.html")
	public ModelAndView userEdit(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(useredit);
		User user = new User();
		
		String opType = (String)request.getParameter("opType");
		String s_userId = (String)request.getParameter("userId");
		
		if(StringUtils.isBlank(opType)&&!StringUtils.isBlank(s_userId))
		{
			try{
				user = userService.getUserById(Long.parseLong(s_userId));
				m.addObject("user",user);
			}catch(Exception e){
				return new ModelAndView(userlist);
			}
			
		}
		if(!StringUtils.isBlank(opType))
		{
			if(opType.compareTo("edit")==0)
			{
				if(StringUtils.isBlank(s_userId))
				{
					return new ModelAndView(userlist);
				}
				else {
					try{
						user = userService.getUserById(Long.parseLong(s_userId));
					}catch(Exception e){
						return new ModelAndView(userlist);
					}
					
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
						userService.updateUser(user);
				}
				
			}else if(opType.compareTo("add")==0) {
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
					userEdit(request,response);
				}
			}							
		}
		return new ModelAndView(userlist);
	}
	
	@RequestMapping("/userDelete.html")
	public ModelAndView userDelete(HttpServletRequest request,HttpServletResponse response){
		String s_userid = (String)request.getParameter("userId");
		if(StringUtils.isBlank(s_userid))
		{
			return userList(request,response);
		}	
		long userId;
		try{
			userId = Long.parseLong(s_userid);
			userService.deleteUser(userId);
		}catch(Exception e){
			return userList(request,response);
		}
		
		return userList(request,response);
	}*/

	@RequestMapping("/users.json")
	public void usersJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		List<?> users = userService.getAllUsers();
		pw.write(mapper.writeValueAsString(users));
		return;
	}
	
	@RequestMapping("/hospitals.json")
	public void hospitalsJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		List<?> hospitals = hospitalService.getAllHospitals();
		pw.write(mapper.writeValueAsString(hospitals));
		return;
	}
	
	@RequestMapping("/departments.json")
	public void departmentsJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		List<LinkedHashMap<String,String>> departments = new ArrayList<LinkedHashMap<String,String>>();
		for(Department d : departmentService.getAllDepartments()){
			LinkedHashMap<String, String> m = new LinkedHashMap<String,String>();
			m.put("departmentId", String.valueOf(d.getDepartmentId()));
			m.put("departmentName", d.getDepartmentName());
			m.put("description", d.getDescription());
			m.put("hospitalName",d.getHospital().getHospitalName());
			departments.add(m);
		}
		pw.write(mapper.writeValueAsString(departments));
		return;
	}
	
	@RequestMapping("/doctors.json")
	public void doctorsJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		List<LinkedHashMap<String,String>> doctors = new ArrayList<LinkedHashMap<String,String>>();
		for(Doctor d : doctorService.getAllDoctors()){
			LinkedHashMap<String, String> m = new LinkedHashMap<String,String>();
			m.put("doctorId", String.valueOf(d.getDoctorId()));
			m.put("doctorName", d.getName());
			m.put("doctorSex", d.getSex());
			m.put("doctorLevel", d.getLevel());
			m.put("description", d.getDescription());
			m.put("department", d.getDepartment().getDepartmentName());
			m.put("hospital", d.getDepartment().getHospital().getHospitalName());
			doctors.add(m);
		}
		pw.write(mapper.writeValueAsString(doctors));
		return;
	}
	
	@RequestMapping("/appointmentDetails.json")
	public void appointmentDetailsJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		List<LinkedHashMap<String,String>> appdetails = new ArrayList<LinkedHashMap<String,String>>();
		for(AutoAppointment a : appointmentDetailService.getAllAutoAppointments()){
			LinkedHashMap<String, String> m = new LinkedHashMap<String,String>();
			m.put("id", String.valueOf(a.getId()));
			m.put("hospital",hospitalService.getHostpitalById(a.getHospitalId()).getHospitalName());
			m.put("department",departmentService.getDepartmentById(a.getDepartmentId()).getDepartmentName());
			m.put("doctor",doctorService.getDoctorById(a.getDoctorId()).getName());
			m.put("day",a.getDay());
			m.put("time",a.getTime());
			m.put("amount", String.valueOf(a.getAmount()));
			m.put("cost",String.valueOf(a.getCost()));
			appdetails.add(m);
		}
		pw.write(mapper.writeValueAsString(appdetails));
		return;
	}
	
	@RequestMapping(value="/upload.html",method=RequestMethod.GET)
	public ModelAndView uploadImg(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String s_hos = (String)request.getParameter("hospitalId");
		try{
			ModelAndView m = new ModelAndView("admin/afterupload");
			Hospital hospital = hospitalService.getHostpitalById(Long.parseLong(s_hos));
			m.addObject("hospital",hospital);
			return m;
		}catch(Exception e){
			response.sendRedirect("hospitalList.html");
			return null;
		}
		
	}
	
	@RequestMapping(value="/upload.html", method=RequestMethod.POST)
    public ModelAndView handleFileUpload(HttpServletRequest request,@RequestParam("hospitalImage") MultipartFile file){
		ModelAndView m = new ModelAndView("admin/afterupload");
		String hospitalId;
		System.out.println(request.getQueryString());
		hospitalId = request.getQueryString().split("=")[1];
		System.out.println(hospitalId);
		if(request.getQueryString().split("=")[0].compareTo("hospitalId")!=0||
				StringUtils.isBlank(hospitalId)){
			m.addObject("message","参数不正确！");
			return m;
		}
        if (!file.isEmpty()) {
            try {
                if(file.getOriginalFilename().endsWith(".jpg")){
                	String filePath = request.getSession().getServletContext().getRealPath("/") + "images/hospital/" + hospitalId + ".jpg";
                	File img = new File(filePath);
                	if(img.exists())
                		img.delete();
                	file.transferTo(img);
                	System.out.println(hospitalId);
                	m.addObject("message","上传成功！");
                    return m;
                }
                m.addObject("message","格式不正确！");
                return m;
                //return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
            	m.addObject("message","上传失败！");
                return m;
                //return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
        	m.addObject("message","文件为空！");
            return m;
            //return "You failed to upload " + name + " because the file was empty.";
        }
    }
}
