package buaa.bp.asclepius.utils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
 

public class MailUtil {
     
	private static  String HOST;
    private static  String PROTOCOL;   
    private static  int PORT ;
    private static  boolean AUTH;
    private static  String FROM;//发件人的email
    private static  String PWD;//发件人密码
    
   
    
    static{
    	PropertiesConfiguration config = null;
		try {
			config = new PropertiesConfiguration("config.properties");
		} catch (ConfigurationException e) {
			System.out.println("打开配置文件失败！");
		}
    	HOST = config.getString("mail.smtp.host");
    	PROTOCOL = config.getString("mail.store.protocol");
    	PORT = config.getInt("mail.smtp.port");
    	AUTH = config.getBoolean("mail.smtp.auth");
    	FROM = config.getString("mail.from");
    	PWD = config.getString("mail.password");
    /*	System.out.println(HOST);
    	System.out.println(PROTOCOL);
    	System.out.println(PORT);
    	System.out.println(AUTH);
    	System.out.println(FROM);
    	System.out.println(PWD);*/
    }
    
    /**
     * 获取Session
     * @return
     */
    private Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);//设置服务器地址
        props.put("mail.store.protocol" , PROTOCOL);//设置协议
        props.put("mail.smtp.port", PORT);//设置端口
        props.put("mail.smtp.auth" , AUTH);
         
        Authenticator authenticator = new Authenticator() {
 
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }
             
        };
        //Session session = Session.getDefaultInstance(props , authenticator);
        Session session = Session.getInstance(props, authenticator);
        session.setDebug(true);
        	
        return session;
    }
     
    public void send(String toEmail , Long userId,String content){
        Session session = getSession();
        try {
            System.out.println("--send--"+content);
            System.out.println(session.getProperties());
            // Instantiate a message
            Message msg = new MimeMessage(session);
  
            //Set message attributes
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("账号激活邮件");
            msg.setSentDate(new Date());
            msg.setContent(generateContent(userId, content),"text/html;charset=UTF-8");
            
            //Send the message
            Transport.send(msg);
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    
    public String generateContent(long userId,String content){
	   StringBuilder sBuilder = new StringBuilder();
       String head = "点击此链接激活：<a href=\"";     
       sBuilder.append("http://localhost:8080/Asclepius/general/userActivate.html?userId=");
       sBuilder.append(userId);
       sBuilder.append("&validateCode=");
       try {
          
           sBuilder.append(URLEncoder.encode(content,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("不支持的编码方式！");
			return null;
		}
       String end = "\">" + sBuilder.toString() + "</a>" + "<br/>(如果不能点击该链接地址，请复制并粘贴到浏览器的地址输入框)";
      return head+sBuilder.toString()+end;
   }
}
