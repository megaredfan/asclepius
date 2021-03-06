package buaa.bp.asclepius.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import buaa.bp.asclepius.model.User;


public class UserMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	UserMapper userMapper = context.getBean("userMapper",UserMapper.class);
	
	@Before
	public void before(){
		Assert.assertEquals(1, userMapper.deleteUser(41216998636L));
	}
	
	@Test
	public void test() throws JsonProcessingException{
		User user = new User();
		user.setId(41216998636L);
		user.setPassword("4QrcOUm6Wau+VuBX8g+IPg==");
		user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
		user.setIdNo("500102199501178593");
		user.setSex("male");
		user.setRealName("熊纪元");
		user.setUserName("megaredfan");
		user.setActiveFlag(1);
		
		Assert.assertEquals(1, userMapper.createUser(user));
		
		user.setLastLogin(new Timestamp(System.currentTimeMillis()));
		
		Assert.assertEquals(1, userMapper.updateUser(user));
		
		System.out.println(userMapper.count());
		
		user = userMapper.getUserById(41216998636L);
		System.out.println(user);
		
		user = userMapper.getUserByName("megaredfan");
		System.out.println(user);
		System.out.println(userMapper.selectByRange(0, userMapper.count()));
		
		List<User> users = userMapper.getAllUsers();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		System.out.println(mapper.writeValueAsString(users));
		
	}
}
