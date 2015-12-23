package buaa.bp.asclepius.interceptor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.DuplicateKeyException;

import buaa.bp.asclepius.logic.AppointmentDetailService;
import buaa.bp.asclepius.logic.AppointmentService;
import buaa.bp.asclepius.logic.CreditService;
import buaa.bp.asclepius.logic.UserService;
import buaa.bp.asclepius.model.Appointment;
import buaa.bp.asclepius.model.Credit;
import buaa.bp.asclepius.model.User;
import buaa.bp.asclepius.utils.UUID11;

@Aspect
public class AspectControl {
	private static Logger logger = Logger.getLogger(AspectControl.class);
	
	@Resource(name="appointmentService")
	private AppointmentService appointmentService;
	
	@Resource(name="appointmentDetailService")
	private AppointmentDetailService appointmentDetailService;	
	
	@Resource(name="creditService")
	private CreditService creditService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Pointcut("execution(* selectByRange(..))")
	public void aspectjMethod(){}
	
	@Pointcut("execution(* myAppointments(..))")
	public void beforeMyAppointments(){}
	
	@Pointcut("execution(* account(..))")
	public void account(){}
	
	@Pointcut("execution(* print(..))")
	public void afterPrint(){}
	
	@Pointcut("execution(* makeAnAppointment(..))")
	public void afterMakingAnAppointment(){};
	
	@Before(value="aspectjMethod()")
	public void aroundAdvice(){
		System.out.println("before selectByRange");
		
	}

	
	@Before(value="account()")
	public void checkAppointments(JoinPoint jp) throws Throwable{
		//检查用户所有预约的状态，对未支付，未打印等状态进行处理
		Object[] args = jp.getArgs();
		HttpServletRequest request = (HttpServletRequest)args[0];
		
		long userId;
		User user;
		try{
			user = (User) request.getSession().getAttribute("userInSession");
			userId = user.getId();
		}catch(Exception e){
			logger.error("用户session为空.",e);
			return;
		}
		
		List<Appointment> list = appointmentService.getAllAppointments(userId);
		for(Appointment app : list)
		{
			try{
				long passed = System.currentTimeMillis()-app.getTime().getTime();
				if((passed/1000/60)>45 && app.getStatus()==Appointment.WAITING_FOR_PAYING){
					app.setStatus(Appointment.NOT_PAYED);
					appointmentService.updateAppointment(app);
					user.setCreditLevel(user.getCreditLevel()-1);
					userService.updateUser(user);
					return;
				}
				long time = appointmentDetailService.getAppointmentById(app.getAppointmentDetailId()).getDate().getTime();
				if(System.currentTimeMillis()>time && app.getStatus()==Appointment.WAITING_FOR_PRINTING){
					app.setStatus(Appointment.NOT_PRINTED);
					appointmentService.updateAppointment(app);
					user.setCreditLevel(user.getCreditLevel()-1);
					userService.updateUser(user);
					return;
				}
			}catch(Exception e){
				logger.error(e);
				return;
			}
		}
	}
	
	@After(value="afterPrint()")
	public void postPrint(JoinPoint jp){
		//保存出诊记录，进行信用评级等操作
		Object[] args = jp.getArgs();
		HttpServletRequest request = (HttpServletRequest)args[0];
		
		String opType = (String)request.getParameter("opType");
		String s_appointmentID = (String)request.getParameter("appointmentId");
		long appointmentId;
		if(!StringUtils.isBlank(opType))
		{
			Credit credit = new Credit();
			credit.setCreateTime(new Timestamp(System.currentTimeMillis()));
			credit.setDescription("");
			
			credit.setCreditId(UUID11.getRandomId());
			try{
				User user = userService.getUserById(Long.parseLong(request.getParameter("userId")));
				user.setCreditLevel(user.getCreditLevel()+1);
				credit.setUserId(user.getId());
				credit.setAppointmentId(Long.parseLong(s_appointmentID));
				creditService.createCredit(credit);
				userService.updateUser(user);
			}catch(DuplicateKeyException e){
				postPrint(jp);
			}catch(NumberFormatException e1){
				logger.error(e1);
			}
		}
	}
	
	@After(value="afterMakingAnAppointment()")
	public void confirmAppointment(JoinPoint jp) throws Throwable{
		//支付确认
		Object[] args = jp.getArgs();
		HttpServletRequest request = (HttpServletRequest)args[0];

		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run() {
				String s_app = request.getParameter("appointmentId");
				long appointmentId = Long.parseLong(s_app);
				Appointment app = appointmentService.getAppointmentById(appointmentId);
				if(app.getStatus()!=Appointment.WAITING_FOR_PRINTING){
					app.setStatus(Appointment.NOT_PAYED);
					appointmentService.updateAppointment(app);
				}
			}
		}, 45*60*1000);
		return;
	}
}
