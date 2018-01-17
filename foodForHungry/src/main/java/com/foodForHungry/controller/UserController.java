package com.foodForHungry.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.foodForHungry.bo.UserBO;
import com.foodForHungry.exception.InvalidRequestException;
import com.foodForHungry.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * Created by mashara on 6/12/17.
 */

@RestController
public class UserController {


	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping(value = "/user",method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public UserBO addUser(@RequestBody UserBO userBO) throws InvalidRequestException,JsonProcessingException,ParseException{
		return userServiceImpl.create(userBO);
	}

	@RequestMapping(value = "/user",method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteUser(@RequestBody UserBO userBO) throws InvalidRequestException,JsonProcessingException,ParseException{
		 userServiceImpl.delete(userBO);
	}

	@RequestMapping(value = "/user",method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public UserBO updateUser(@RequestBody UserBO userBO) throws InvalidRequestException,JsonProcessingException,ParseException{
		return userServiceImpl.update(userBO);
	}

	@RequestMapping(value = "/verify/{token}",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void verifytoken(@PathVariable String token) throws InvalidRequestException,JsonProcessingException,ParseException{
		if (!userServiceImpl.verifyToken(token)){
			throw new InvalidRequestException("invalid token");
		}
	}

	@RequestMapping(value = "/token/{id}",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getToken(@PathVariable Long id) throws InvalidRequestException,JsonProcessingException,ParseException{
		return userServiceImpl.getToken(id);
	}

	@RequestMapping(value = "/resendVerificationLink/{email}",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void resendVerificationEmail(@PathVariable String email) throws InvalidRequestException,JsonProcessingException,ParseException{
		userServiceImpl.resendVerificationEmail(email);
	}
}
