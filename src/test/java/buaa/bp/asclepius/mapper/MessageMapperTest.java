package buaa.bp.asclepius.mapper;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import buaa.bp.asclepius.model.Message;

public class MessageMapperTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
	MessageMapper messageMapper = context.getBean("messageMapper",MessageMapper.class);
	
	@Before
	public void before(){
		Assert.assertEquals(1, messageMapper.deleteMessage(41216998636L));
		Assert.assertEquals(1, messageMapper.deleteMessage(41216998637L));
	}
	
	@Test
	public void test(){
		Message message1 = new Message();
		Message message2 = new Message();
		
		message1.setId(41216998636L);
		message1.setContent("hello world!");
		message1.setAuthor("visitor1");
		message1.setCreateTime(new Timestamp(System.currentTimeMillis()));
		message1.setPid(0L);
		
		message2.setId(41216998637L);
		message2.setContent("hello world to you!");
		message2.setAuthor("visitor2");
		message2.setCreateTime(new Timestamp(System.currentTimeMillis()));
		
		Assert.assertEquals(1, messageMapper.createMessage(message1));
		Assert.assertEquals(1, messageMapper.createMessage(message2));
		
		message2.setPid(41216998636L);
		
		Assert.assertEquals(1, messageMapper.updateMessage(message2));
		
		for(Message m : messageMapper.getAllRootMessages())
		{
			System.out.println(m);
		}
		for(Message m : messageMapper.getMessagesById(41216998636L))
		{
			System.out.println(m);
		}
		System.out.println(messageMapper.selectByRange(0, messageMapper.count()));
	}
}
