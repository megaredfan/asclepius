package buaa.bp.asclepius.mapper;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import buaa.bp.asclepius.model.SystemAdmin;


public class SystemAdminMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	SystemAdminMapper systemAdminMapper = context .getBean("systemAdminMapper",SystemAdminMapper.class);
	
	@Before
	public void before(){
		Assert.assertEquals(1, systemAdminMapper.deleteSystemAdmin(41216998636L));
	}
	
	@Test
	public void test(){
		SystemAdmin admin = new SystemAdmin();
		admin.setAdminId(41216998636L);
		admin.setAdminName("admin");
		admin.setPassword("4QrcOUm6Wau+VuBX8g+IPg==");
		
		Assert.assertEquals(1, systemAdminMapper.createSystemAdmin(admin));
		
		admin.setLastVisit(new Timestamp(System.currentTimeMillis()));
		
		Assert.assertEquals(1, systemAdminMapper.updateSystemAdmin(admin));
		
		admin = systemAdminMapper.getSystemAdminById(41216998636L);
		System.out.println(admin);
		
		for(SystemAdmin a : systemAdminMapper.getAllSystemAdmins())
		{
			System.out.println(a);
		}
		System.out.println(systemAdminMapper.selectByRange(0, systemAdminMapper.count()));
	}
}
