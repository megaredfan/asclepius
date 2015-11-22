package buaa.bp.asclepius.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import buaa.bp.asclepius.model.Credit;
import junit.framework.Assert;

public class CreditMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	CreditMapper creditMapper = context.getBean("creditMapper",CreditMapper.class);
	
	@BeforeClass
	public void before(){
		Assert.assertEquals(1,creditMapper.deleteCredit(41216998636L));
	}
	
	@Test
	public void test(){
		Credit credit = new Credit();
		credit.setCreditId(41216998636L);
		credit.setCreateTime(new Timestamp(System.currentTimeMillis()));
		credit.setUserId(41216998636L);
		
		Assert.assertEquals(1, creditMapper.createCredit(credit));
		
		credit.setDescription("hello");
		
		Assert.assertEquals(1, creditMapper.updateCredit(credit));
		
		credit = creditMapper.getCreditById(41216998636L, 41216998636L);
		System.out.println(credit);
		
		List<Credit> list = creditMapper.getAllCredits(41216998636L);
		for(Credit c : list)
		{
			System.out.println(c);
		}
		System.out.println(creditMapper.selectByRange(0, creditMapper.count()));
	}
}
