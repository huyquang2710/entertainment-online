package com.entertain.services;

import javax.servlet.ServletContext;

import com.entertain.entities.User;

public interface IEmailService {
	void sendMail(ServletContext context, User recipient, String type);
}
