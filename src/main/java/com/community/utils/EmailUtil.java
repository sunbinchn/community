package com.community.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Properties;

public class EmailUtil {
//	 public static final String FROM = "411938182@qq.com";//发件人的email
	 public static final String FROM = "sunbinchn@qq.com";//发件人的email
     public static final String PWD = "enwaytopcmnkbgga";//发件人密码--邮箱密码
     public static final String URL = "http://localhost:8888/store";//项目主页
     public static final String HOST = "smtp.qq.com";
     public static final int PORT = 587;

	/**
	 * 发送邮件
	 * @param to 邮件接受者
	 * @param title 邮件标题
	 * @param content 邮件内容
	 */
	public static void sendMail(String to,String title,String content) {
		final Properties props = new Properties();
		 // 表示SMTP发送邮件，必须进行身份验证
		props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", HOST);
        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
        props.put("mail.smtp.port", PORT);
        // 此处填写你的账号
        props.put("mail.user", FROM);
        // 此处的密码就是前面说的16位STMP口令
        props.put("mail.password", PWD);
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        try {
        	// 设置发件人
			InternetAddress from = new InternetAddress(
			        props.getProperty("mail.user"));
			message.setFrom(from);
			// 设置收件人的邮箱
			InternetAddress toAddr = new InternetAddress(to);
			message.setRecipient(RecipientType.TO, toAddr);
			// 设置邮件标题
			message.setSubject(title);
			// 设置邮件的内容体
			message.setContent(content, "text/html;charset=UTF-8");
			// 最后当然就是发送邮件啦
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
