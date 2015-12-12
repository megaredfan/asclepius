package buaa.bp.asclepius.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import buaa.bp.asclepius.logic.AppointmentDetailService;
import buaa.bp.asclepius.logic.DepartmentService;
import buaa.bp.asclepius.logic.DoctorService;
import buaa.bp.asclepius.logic.HospitalService;
import buaa.bp.asclepius.logic.MessageService;
import buaa.bp.asclepius.logic.SystemAdminService;
import buaa.bp.asclepius.logic.UserService;
import buaa.bp.asclepius.model.Department;
import buaa.bp.asclepius.model.Doctor;
import buaa.bp.asclepius.model.Hospital;
import buaa.bp.asclepius.model.Message;
import buaa.bp.asclepius.model.User;
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
	private static final String doctorEdit = "admin/doctorEdit";
	private static final String hosList = "admin/hosList";
	private static final String hosEdit = "admin/hosEdit";
	private static final String userlist = "admin/users";
	private static final String useredit ="admin/useredit";
	
	
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
	public ModelAndView reply(HttpServletRequest request,HttpServletResponse response){
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
				return messages(request,response);
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
					return m;
				}catch(Exception e){
					m = new ModelAndView(appedit);
					m.addObject("hospitals",hospitalService.getAllHospitals());
					System.out.println("dep is blank.but there is a exception.");
					e.printStackTrace();
				}
				return m;
			}
			if(!StringUtils.isBlank(s_dep)&&StringUtils.isBlank(s_hos)){
				m = new ModelAndView("admin/docoption");
				System.out.println("hos is blank.");
				try{
					m.addObject("doctors",doctorService.getDoctorsByDepartment(Long.parseLong(s_dep)));
				}catch(NumberFormatException e){
					return m;
				}catch(Exception e){
					m = new ModelAndView(appedit);
					m.addObject("hospitals",hospitalService.getAllHospitals());
					System.out.println("hos is blank.but there is a exception.");
					e.printStackTrace();
				}
				return m;
			}
		}
		
		return m;
	}
	
	@RequestMapping("/departmentList.html")
	public ModelAndView departmentList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(deptlist);
		return m;
	}

	@RequestMapping("/departmentEdit.html")
	public ModelAndView departmentEdit(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(deptedit);
		Department department = new Department();
		
		String opType = (String)request.getParameter("opType");
		String s_departmentId = (String)request.getParameter("departmentId");
		String s_hospitalId = (String)request.getParameter("hospitalId");
		
		if(StringUtils.isBlank(opType)&&StringUtils.isBlank(s_departmentId)&&!StringUtils.isBlank(s_hospitalId))
		{
			try{
				department = departmentService.getDepartmentById(Long.parseLong(s_departmentId));
				m.addObject("department",department);
			}catch(Exception e){
				return new ModelAndView(deptlist);
			}
		}
		
		if(!StringUtils.isBlank(opType)&&!StringUtils.isBlank(s_departmentId)&&!StringUtils.isBlank(s_hospitalId))
		{
			if(opType.compareTo("edit")==0)
			{
					try{
						department = departmentService.getDepartmentById(Long.parseLong(s_departmentId));
					}catch(Exception e){
						return new ModelAndView(deptlist);
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
		
		return new ModelAndView(deptlist);
	}

	@RequestMapping("/departmentDelete.html")
	public ModelAndView departmentDelete(HttpServletRequest request,HttpServletResponse response){
		String s_departmentid = (String)request.getParameter("departmentId");
		if(StringUtils.isBlank(s_departmentid))
		{
			return departmentList(request, response);
		}	
		long departmentId;
		try{
			departmentId = Long.parseLong(s_departmentid);
			departmentService.deleteDepartment(departmentId);
		}catch(Exception e){
			return departmentList(request, response);
		}
		
		return departmentList(request, response);
	}

	@RequestMapping("/hospitalList.html")
	public ModelAndView hospitalList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(hosList);

		hospitalService.generateList(request, response, m, "hospitals");
		return m;
	}

	@RequestMapping("/hospitalEdit.html")
	public ModelAndView hospitalEidt(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(hosEdit);
		Hospital hospital = new Hospital();
		
		String opType = (String)request.getParameter("opType");
		String s_hospitalId = (String)request.getParameter("hospitalId");
		
		if(StringUtils.isBlank(opType)&&StringUtils.isBlank(s_hospitalId))
		{
			try{
				hospital = hospitalService.getHostpitalById(Long.parseLong(s_hospitalId));
				m.addObject("hospital",hospital);
			}catch(Exception e){
				return new ModelAndView(hosList);
			}
		}
		
		if(!StringUtils.isBlank(opType)&&!StringUtils.isBlank(s_hospitalId))
		{
			if(opType.compareTo("edit")==0)
			{
					try{
						hospital = hospitalService.getHostpitalById(Long.parseLong(s_hospitalId));
					}catch(Exception e){
						return new ModelAndView(hosList);
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
		return new ModelAndView(hosList);
	}
	
	@RequestMapping("/hospitalDelete.html")
	public ModelAndView hospitalDelete(HttpServletRequest request,HttpServletResponse response){
		String s_hospitalId = (String)request.getParameter("hospitalId");
		if(StringUtils.isBlank(s_hospitalId))
		{
			return hospitalList(request, response);
		}	
		long hospitalId;
		try{
			hospitalId = Long.parseLong(s_hospitalId);
			hospitalService.deleteHospital(hospitalId);
		}catch(Exception e){
			return hospitalList(request, response);
		}
		return hospitalList(request, response);
	}

	@RequestMapping("/doctorList.html")
	public ModelAndView doctorList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(doctorlist);
		return m;
	}

	@RequestMapping("/doctorEdit.html")
	public ModelAndView doctorEdit(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(doctorEdit);
		Doctor doctor = new Doctor();
		long departmentId;
		
		String opType = (String)request.getParameter("opType");
		String s_doctorId = (String)request.getParameter("departmentId");
		
		if(StringUtils.isBlank(opType)&&StringUtils.isBlank(s_doctorId))
		{
			try{
				doctor = doctorService.getDoctorById(Long.parseLong(s_doctorId));
				m.addObject("doctor",doctor);
			}catch(Exception e){
				return new ModelAndView(doctorlist);
			}
		}
		
		if(!StringUtils.isBlank(opType)&&!StringUtils.isBlank(s_doctorId))
		{
			try{
				departmentId = Long.parseLong(request.getParameter("departmentId"));
			}catch(Exception e){
				return new ModelAndView(doctorlist);
			}
			if(opType.compareTo("edit")==0)
			{
					try{
						doctor = doctorService.getDoctorById(Long.parseLong(s_doctorId));
					}catch(Exception e){
						return new ModelAndView(doctorlist);
					}
					
					doctor.setDepartment(departmentService.getDepartmentById(departmentId));
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

				doctor.setDepartment(departmentService.getDepartmentById(departmentId));
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
		return new ModelAndView(doctorlist);
	}
	
	@RequestMapping("/doctorDelete.html")
	public ModelAndView doctorDelete(HttpServletRequest request,HttpServletResponse response){
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
			return doctorList(request, response);
		}
		return doctorList(request, response);
	}
	
	@RequestMapping("/userList.html")
	public ModelAndView userList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(userlist);
		return m;
	}
	
	@RequestMapping("userEdit.html")
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
	}

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
}
