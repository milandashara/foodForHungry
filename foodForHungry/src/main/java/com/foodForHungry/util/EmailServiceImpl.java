package com.foodForHungry.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Created by mashara on 6/12/17.
 */
@Component
public class EmailServiceImpl  {

	@Autowired
	public JavaMailSender emailSender;

	public void sendSimpleMessage(
			String to, String subject, String text) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);

	}

	public void sendVerificationTokenEmail(String email,String tokenVerificationUrl){
		this.sendSimpleMessage(email,"please click verify link to activate your account",tokenVerificationUrl);
	}
}
