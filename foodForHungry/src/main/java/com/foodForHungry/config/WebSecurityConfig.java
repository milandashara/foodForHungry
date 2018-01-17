package com.foodForHungry.config;

import com.foodForHungry.security.AuthenticationEntryPoint;
import com.foodForHungry.security.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Created by mashara on 6/12/17.
 */
@EnableWebSecurity
@Configuration
//@Profile(value = {"development", "production"})
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().mvcMatchers(HttpMethod.POST,"/user").permitAll().mvcMatchers( "/login**", "/webjars/**","/resendVerificationLink/**","/verify/**","/token/**").permitAll().anyRequest().fullyAuthenticated().and().
				httpBasic().authenticationEntryPoint(authEntryPoint).and().logout().logoutSuccessUrl("/").permitAll().and().
		// We filter the api/login requests
//        addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
//				UsernamePasswordAuthenticationFilter.class)
				// And filter other requests to check the presence of JWT in header
				addFilterBefore(new JWTAuthenticationFilter(),
						UsernamePasswordAuthenticationFilter.class).
				//xhr.setRequestHeader("X-XSRF-TOKEN",Cookies.get('XSRF-TOKEN'));
//				csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).disable();
			csrf().disable()
		;
	}




}
