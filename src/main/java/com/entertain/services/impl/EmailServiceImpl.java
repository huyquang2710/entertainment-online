package com.entertain.services.impl;

import javax.servlet.ServletContext;

import com.entertain.entities.User;
import com.entertain.services.IEmailService;
import com.entertain.utils.EmailUtility;

public class EmailServiceImpl implements IEmailService{
	private static final String EMAIL_WELCOME_SUBJECT = "Welcome to Radio Story";
	private static final String EMAIL_FORGOT_PASSWORD = "From Radio - New Password";

	@Override
	public void sendMail(ServletContext context, User recipient, String type) {
		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		String user = context.getInitParameter("user");
		String pass = context.getInitParameter("pass");
		
		try {
			String content = null;
			String subject = null;
			
			switch (type) {
			case "welcome": {
				subject = EMAIL_WELCOME_SUBJECT;
				content = "Dear" + recipient.getUsername() + ", your welcome" ;
				break;
				}
			case "forgot": {
				subject = EMAIL_FORGOT_PASSWORD;
				content = "Dear" + recipient.getUsername() + ", new Password: " + recipient.getPassword();
				break;
				}
			default: {
				subject = "Radio Love";
				content = "Something went wrong!! please try again!!";
				break;
				}
			}
			EmailUtility.sendEmail(host, port, user, pass, recipient.getEmail(), subject, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
