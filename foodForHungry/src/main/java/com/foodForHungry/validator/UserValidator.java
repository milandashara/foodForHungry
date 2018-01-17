package com.foodForHungry.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodForHungry.bo.FoodBO;
import com.foodForHungry.bo.UserBO;
import com.foodForHungry.entity.Food;
import com.foodForHungry.exception.InvalidRequestException;
import com.foodForHungry.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mashara on 5/12/17.
 */
public class UserValidator {

	private static EmailValidator emailValidator = new EmailValidator();

	public static void validateCreate(UserBO userBO) throws InvalidRequestException,JsonProcessingException{
		if (userBO == null){
			throw new InvalidRequestException("empty request");
		}

		Map<String,String> errorMap = new HashMap();

		if (StringUtils.isEmpty(userBO.getEmail())){
			errorMap.put("email","it is mandatory");
		}

		if (StringUtils.isEmpty(userBO.getName())){
			errorMap.put("name","it is mandatory");
		}

		if (StringUtils.isEmpty(userBO.getPassword())){
			errorMap.put("password","it is mandatory");
		}

		if (StringUtils.isEmpty(userBO.getVerifyPassword())){
			errorMap.put("verifyPassword","it is mandatory");
		}
		if (userBO.getPassword()!= null && userBO.getVerifyPassword() != null) {
			if (!userBO.getPassword().equals(userBO.getVerifyPassword())) {
				errorMap.put("verifyPassword","password doesn't match");
			}
		}

		if(!emailValidator.validate(userBO.getEmail())){
			errorMap.put("email","invalid email");
		}

		if (!errorMap.isEmpty()){
			String json = new ObjectMapper().writeValueAsString(errorMap);
			throw new InvalidRequestException(json);
		}



	}

	public static void validateDelete(UserBO userBO) throws InvalidRequestException,JsonProcessingException{
		if (userBO == null){
			throw new InvalidRequestException("empty request");
		}

		Map<String,String> errorMap = new HashMap();

		if (StringUtils.isEmpty(userBO.getEmail())){
			errorMap.put("email","it is mandatory");
		}

		if(!emailValidator.validate(userBO.getEmail())){
			errorMap.put("email","invalid email");
		}

		if (!errorMap.isEmpty()){
			String json = new ObjectMapper().writeValueAsString(errorMap);
			throw new InvalidRequestException(json);
		}

	}

	public static void validateUpdate(UserBO userBO) throws InvalidRequestException,JsonProcessingException{
		if (userBO == null){
			throw new InvalidRequestException("empty request");
		}

		Map<String,String> errorMap = new HashMap();

		if (StringUtils.isEmpty(userBO.getEmail())){
			errorMap.put("email","it is mandatory");
		}

		if (StringUtils.isEmpty(userBO.getName())){
			errorMap.put("name","it is mandatory");
		}

		if(!emailValidator.validate(userBO.getEmail())){
			errorMap.put("email","invalid email");
		}

		if (!errorMap.isEmpty()){
			String json = new ObjectMapper().writeValueAsString(errorMap);
			throw new InvalidRequestException(json);
		}
	}

	public static void validateEmail(String email) throws InvalidRequestException{
		if(!emailValidator.validate(email)){
			throw new InvalidRequestException("email is not valid");
		}
	}

}
