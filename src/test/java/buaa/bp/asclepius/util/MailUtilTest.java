package buaa.bp.asclepius.util;

import org.junit.Test;
import buaa.bp.asclepius.utils.MailUtil;
 
public class MailUtilTest {
	MailUtil mailUtil = new MailUtil();

    @Test
    public void testSend(){
    	//mailUtil.send("xiongjiyuan@hotmail.com",13211130L,"测试邮件");
    	System.out.println(mailUtil.generateContent(13211130L, "测试"));
    }
}
