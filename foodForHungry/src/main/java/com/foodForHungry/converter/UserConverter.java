package com.foodForHungry.converter;

import com.foodForHungry.bo.UserBO;
import com.foodForHungry.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Created by mashara on 4/12/17.
 */
@Component
public class UserConverter {

	@Autowired
	PasswordEncoder passwordEncoder;

	public UserDetail getCreateUser(UserBO userBO) throws ParseException{
		UserDetail userDetail = new UserDetail();
		userDetail.setEmail(userBO.getEmail());
		userDetail.setName(userBO.getName());
		userDetail.setPasswordHash(passwordEncoder.encode(userBO.getPassword()));
		return userDetail;
	}

	public UserDetail getUpdateUser(UserBO userBO) throws ParseException{
		UserDetail userDetail = new UserDetail();
		userDetail.setName(userBO.getName());
		//userDetail.setPasswordHash(passwordEncoder.encode(userBO.getPassword()));
		return userDetail;
	}


	public UserBO getUserBO(UserDetail userDetail){
		UserBO userBO = new UserBO();
		userBO.setEmail(userDetail.getEmail());
		userBO.setName(userDetail.getName());
		userBO.setId(userDetail.getId());
		return userBO;
	}

}
