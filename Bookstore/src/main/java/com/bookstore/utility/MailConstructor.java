package com.bookstore.utility;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.bookstore.domain.User;

@Component
public class MailConstructor {
	@Autowired
	private Environment env;
	
	public SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, User user, String password) {
		String url = contextPath + "/newUser?token=" + token;
		String message = "\nPlease click on this to verify email. Your password is \n"+password;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("BookStore for new user");
		email.setText(url+message);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}
}
