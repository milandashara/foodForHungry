/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.foodForHungry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
@Import({SpringRepositoryConfig.class, SpringServicesConfig.class,SpringSecurityConfig.class,WebSecurityConfig.class,WebSecurityConfiguration.class})
//@ImportResource("classpath:configuration/applicationContext.xml")
public class SpringConfig {

}
