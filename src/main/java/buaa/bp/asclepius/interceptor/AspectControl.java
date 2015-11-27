package buaa.bp.asclepius.interceptor;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import buaa.bp.asclepius.logic.AppointmentService;
import buaa.bp.asclepius.model.Appointment;

@Aspect
public class AspectControl {
	//private static Logger logger = Logger.getLogger(AspectControl.class);
	
	@Resource(name="appointmentService")
	private AppointmentService appointmentService;
	
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

		for(Object obj : pjp.getArgs())
		{
			System.out.println(obj);
		}
		System.out.println("before invoke");
		
		Object retvar = pjp.proceed();
		System.out.println("after invoke");
		return retvar;
	}
	
	@Before(value="beforeMyAppointments()")
	public void checkAppointments(JoinPoint jp) throws Throwable{
		//TODO:检查用户所有预约的状态，对未支付，未打印等状态进行处理
		/*Object[] args = jp.getArgs();
		HttpServletRequest request = (HttpServletRequest)args[0];
		HttpServletResponse response = (HttpServletResponse)args[1];
		
		long userId;
		
		String s_id = (String)request.getParameter("userId");
		if(StringUtils.isBlank(s_id))
		{
			return;
		}
		try{
			userId = Long.parseLong(s_id);
		}catch(Exception e){
			return;
		}
		List<Appointment> list = appointmentService.getAllAppointments(userId);
		for(Appointment app : list)
		{
			//TODO:检查预约状态
		}*/
	}
	
	@After(value="afterPrint()")
	public void postPrint(JoinPoint jp){
		//TODO:保存出诊记录，进行信用评级等操作
		
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
