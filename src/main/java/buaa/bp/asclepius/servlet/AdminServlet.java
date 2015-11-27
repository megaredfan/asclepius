package buaa.bp.asclepius.servlet;

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

import buaa.bp.asclepius.logic.AppointmentDetailService;
import buaa.bp.asclepius.logic.DepartmentService;
import buaa.bp.asclepius.logic.DoctorService;
import buaa.bp.asclepius.logic.HospitalService;
import buaa.bp.asclepius.logic.MessageService;
import buaa.bp.asclepius.logic.SystemAdminService;
import buaa.bp.asclepius.logic.UserService;
import buaa.bp.asclepius.model.AppointmentDetail;
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
	private static final String message = "admin/message";
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
		
	@RequestMapping("/appointmentDetailList.html")
	public ModelAndView appointmentDetailList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(applist);
		List<?> list = appointmentDetailService.getAvailableAppointments();
		//TODO:添加分页实现
		m.addObject("appointments",list);
		return m;
	}
	
	@RequestMapping("/addNewAppointment.html")
	public ModelAndView appointmentDetailAdd(HttpServletRequest request,HttpServletResponse response){
		//TODO:实现可自动化发布
		ModelAndView m = new ModelAndView(appedit);
		AppointmentDetail appointmentDetail = new AppointmentDetail();
		
		String opType = (String)request.getParameter("opType");
		int amount;
		Timestamp start,end,date;
		long deptId,doctorId,hospitalId;
		if(StringUtils.isBlank(opType))
		{
				return appointmentDetailList(request,response);
		}
		try {
			amount = Integer.parseInt(request.getParameter("amout"));
			start = Timestamp.valueOf((String)request.getParameter("start"));
			end = Timestamp.valueOf((String)request.getParameter("end"));
			date = Timestamp.valueOf((String)request.getParameter("date"));
			deptId = Long.parseLong(request.getParameter("departmentId"));
			doctorId = Long.parseLong(request.getParameter("docotrId"));
			hospitalId = Long.parseLong(request.getParameter("hospitalId"));
			
		} catch (Exception e) {
			return appointmentDetailList(request,response); 
		}
		
		appointmentDetail.setAppdetailId(UUID11.getRandomId());
		appointmentDetail.setAmount(amount);
		appointmentDetail.setDate(date);
		appointmentDetail.setDeptId(deptId);
		appointmentDetail.setDoctorId(doctorId);
		appointmentDetail.setEnd(end);
		appointmentDetail.setHospitalId(hospitalId);
		appointmentDetail.setStart(start);		
		Set<ConstraintViolation<AppointmentDetail>> constraintViolations = validator.validate(appointmentDetail);
		if(constraintViolations.size() > 0){
			List<String> fieldErrors = new ArrayList<String>();
			for(ConstraintViolation<AppointmentDetail> c : constraintViolations){
				fieldErrors.add(c.getMessage());
			}
			System.out.println(fieldErrors.size());
			m.addObject("fieldErrors",fieldErrors);
			return m;
		}
		try{
			appointmentDetailService.createAppointmentDetail(appointmentDetail);
		}catch(DuplicateKeyException e){
			appointmentDetailAdd(request, response);
		}
		return new ModelAndView(userlist);			
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
				department = departmentService.getDepartmentById(Long.parseLong(s_hospitalId),Long.parseLong(s_departmentId));
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
						department = departmentService.getDepartmentById(Long.parseLong(s_hospitalId),Long.parseLong(s_departmentId));
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
					department.setHospitalId(Long.parseLong(s_hospitalId));
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
		//TODO:添加分页
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
	
	public ModelAndView doctorList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(doctorlist);
		//TODO:添加分页
		return m;
	}

	@RequestMapping("/doctorEdit.html")
	public ModelAndView doctorEdit(HttpServletRequest request,HttpServletResponse response){
		ModelAndView m = new ModelAndView(doctorEdit);
		Doctor doctor = new Doctor();
		long hospitalId,departmentId;
		
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
				hospitalId = Long.parseLong(request.getParameter("hospitalId"));
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
					
					doctor.setDepartmentId(departmentId);
					doctor.setDescription((String)request.getParameter("description"));
					doctor.setHospitalId(hospitalId);
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
				doctor.setDepartmentId(UUID11.getRandomId());

				doctor.setDepartmentId(departmentId);
				doctor.setDescription((String)request.getParameter("description"));
				doctor.setHospitalId(hospitalId);
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
	
}
