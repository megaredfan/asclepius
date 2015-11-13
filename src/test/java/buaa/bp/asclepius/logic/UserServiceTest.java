package buaa.bp.asclepius.logic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import buaa.bp.asclepius.mapper.UserMapper;

public class UserServiceTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	
	@Test
	public void test(){
		UserMapper userMapper = context.getBean(UserMapper.class);
		System.out.println(userMapper.getAllUsers());
	}
}