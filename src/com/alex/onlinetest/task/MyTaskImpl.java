package com.alex.onlinetest.task;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component("mytask")
public class MyTaskImpl implements IMyTask {

	private static Logger logger = Logger.getLogger(MyTaskImpl.class);  
	
	//@Scheduled(cron="0/15 * *  * * ? ")	//姣�5绉掓墽琛屼竴娆� 
	@Scheduled(cron="* * 0/1 * * ? ")	//姣1个小时 
	@Override
	public void myTask() {
		
		logger.info("==================Task开始================");
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost("smtp1.hp.com");
		
		
		senderImpl.setUsername("wei.wang4@hpe.com");
		senderImpl.setPassword("Windows9470m$rfv");
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
//		mailMessage.setTo("2013952@qq.com");
//		mailMessage.setFrom("wei.wang4@hpe.com");
//		mailMessage.setSubject("Test");
//		mailMessage.setText("This is a test!!!");
//		
//		senderImpl.send(mailMessage);
		
		//send html
		try {
			MimeMessage mimeMessage = senderImpl.createMimeMessage(); //MimeMessage-->java的  
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"GBK"); // MimeMessageHelper-->spring的  不加后面两个参数会乱码  
	  
	        //设置收件人，主题，内容  
	        messageHelper.setSubject("Hello! ");  
	        messageHelper.setFrom("wei.wang4@hpe.com");  
	        messageHelper.setTo("2013952@qq.com");  
	          
	        StringBuffer str = new StringBuffer();  
	        str.append("<html>");  
	        str.append("<head>");
	        str.append("<link rel='stylesheet' href='//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css'>");
	        str.append("<link rel='stylesheet' href='//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css'>");
	        str.append("</head>");
	        str.append("<body>");
	        str.append("<h1>Hello! 中文! </h1>");
	        str.append("<button class='btn btn-primary' type='submit'>Button</button>");
	        str.append("<script src='//cdn.bootcss.com/jquery/1.11.3/jquery.min.js'></script>");
	        str.append("<script src='//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>");
	        str.append("</body>");
	        str.append("</html>");
	        messageHelper.setText(str.toString(),true); //为true-->发送转义HTML  	
	        
	        senderImpl.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        
		logger.info("==================邮件传送OK================");
	}

}
