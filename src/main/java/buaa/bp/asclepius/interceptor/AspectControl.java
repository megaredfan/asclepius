package buaa.bp.asclepius.interceptor;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
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
	
	@Pointcut("execution(* print(..))")
	public void afterPrint(){}
	
	@Pointcut("execution(* makeAnAppointment(..))")
	public void afterMakingAnAppointment(){};
	
	@Around(value="aspectjMethod()")
	public Object aroundAdvice(ProceedingJoinPoint pjp)throws Throwable{

		return pjp.proceed();
	}
	
	@Before(value="beforeMyAppointments()")
	public void checkAppointments(JoinPoint jp) throws Throwable{
		//检查用户所有预约的状态，对未支付，未打印等状态进行处理
		Object[] args = jp.getArgs();
		HttpServletRequest request = (HttpServletRequest)args[0];
		
		long userId;
		
		String s_id = (String)request.getParameter("userId");
		if(StringUtils.isBlank(s_id))
		{
			logger.info("用户id为空.");
			return;
		}
		try{
			userId = Long.parseLong(s_id);
		}catch(Exception e){
			logger.error("用户id转换失败.",e);
			return;
		}
		List<Appointment> list = appointmentService.getAllAppointments(userId);
		for(Appointment app : list)
		{
			try{
				long passed = System.currentTimeMillis()-app.getTime().getTime();
				if((passed/1000/60)>45 && app.getStatus()==Appointment.WAITING_FOR_PAYING){
					app.setStatus(Appointment.NOT_PAYED);
				}
				long time = appointmentDetailService.getAppointmentById(app.getAppointmentDetailId()).getDate().getTime();
				if(System.currentTimeMillis()>time && app.getStatus()==Appointment.WAITING_FOR_PRINTING){
					app.setStatus(Appointment.NOT_PAYED);
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
		//CreditLog creditLog = new CreditLog();
		User user = userService.getUserById(Long.parseLong(request.getParameter("userId")));
		user.setCreditLevel(user.getCreditLevel()+1);
		Credit credit = new Credit();
		credit.setCreateTime(new Timestamp(System.currentTimeMillis()));
		credit.setDescription("");
		credit.setUserId(user.getId());
		try{
			credit.setCreditId(UUID11.getRandomId());
			creditService.createCredit(credit);
		}catch(DuplicateKeyException e){
			postPrint(jp);
		}
	}
	
	@Around(value="afterMakingAnAppointment()")
	public Object confirmAppointment(ProceedingJoinPoint pjp) throws Throwable{
		//TODO:进行挂号确认
		Object[] args = pjp.getArgs();
		HttpServletRequest request = (HttpServletRequest)args[0];
		
		String trade_status = (String)request.getParameter("trade_status");//交易状态
		if(!StringUtils.isBlank(trade_status) && 
				(trade_status.compareTo("WAIT_SELLER_SEND_GOODS") == 0 ||
				trade_status.compareTo("TRADE_FINISHED") == 0))
		{
			return pjp.proceed(args);
		}
		
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run() {
				String subject = "";
		        try {
		        	subject = new String(request.getParameter("subject").getBytes(
		                "ISO-8859-1"), "UTF-8");
		        	} catch (UnsupportedEncodingException e1) {
		        		// TODO Auto-generated catch block
		        		e1.printStackTrace();
		        		}// 商品名称、订单名称,保存用户id和预约id
				String[] str = subject.split(",");
				long userId = Long.parseLong(str[0]);
				long appointmentId = Long.parseLong(str[1]);
				Appointment app = appointmentService.getAppointmentById(userId, appointmentId);
				app.setStatus(Appointment.NOT_PAYED);
			}
		}, 45*60*1000);
		return null;
	}
}
