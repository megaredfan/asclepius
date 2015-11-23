package buaa.bp.asclepius.interceptor;

import java.util.Timer;
import java.util.TimerTask;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectControl {
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
	}
	
	@After(value="afterPrint()")
	public void postPrint(JoinPoint jp){
		//TODO:保存出诊记录，进行信用评级等操作
	}
	
	@After(value="afterMakingAnAppointment()")
	public void confirmAppointment(){
		//TODO:进行挂号确认
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run() {
				//TODO:进行确认，根据支付状态设置预约状态
			}
		}, 45*60*1000);
	}
}
