package com.foodForHungry.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.foodForHungry.bo.UserBO;
import com.foodForHungry.converter.UserConverter;
import com.foodForHungry.entity.UserDetail;
import com.foodForHungry.entity.VerificationToken;
import com.foodForHungry.exception.InvalidRequestException;
import com.foodForHungry.repo.UserDetailDao;
import com.foodForHungry.repo.VerificationTokenDao;
import com.foodForHungry.util.EmailServiceImpl;
import com.foodForHungry.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.UUID;

/**
 * Created by mashara on 1/12/17.
 */
@Service
public class UserServiceImpl {

	@Value("${tokenVerificationLink}")
	private String tokenVerificationLink;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private UserDetailDao userDetailDao;

	@Autowired
	private VerificationTokenDao verificationTokenDao;

	@Autowired
	private EmailServiceImpl emailService;

	public UserServiceImpl(UserConverter userConverter, UserDetailDao userDetailDao) {
		this.userConverter = userConverter;
		this.userDetailDao = userDetailDao;
	}

	@Transactional
	public UserBO create(UserBO userBO) throws InvalidRequestException,JsonProcessingException,ParseException{
		UserValidator.validateCreate(userBO);
		UserDetail userDetail = userConverter.getCreateUser(userBO);

		UserDetail alreadyExist = userDetailDao.findByEmail(userDetail.getEmail());

		if (alreadyExist != null){
			throw new InvalidRequestException("email already taken. Please use another email id");
		}

		userDetail.setEnabled(false);
		userDetail = userDetailDao.save(userDetail);

		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(UUID.randomUUID().toString());
		verificationToken.setUserDetail(userDetail);
		verificationTokenDao.save(verificationToken);
		String tokenVerificationUrl = tokenVerificationLink +"/"+verificationToken.getToken();
		emailService.sendVerificationTokenEmail(userDetail.getEmail(),tokenVerificationUrl);
		return userConverter.getUserBO(userDetail);
	}

	@Transactional
	public boolean verifyToken(String token) throws InvalidRequestException,JsonProcessingException,ParseException{
		VerificationToken verificationToken = verificationTokenDao.findByToken(token);
		if (token.equals(verificationToken.getToken())){
			UserDetail userDetail = verificationToken.getUserDetail();
			userDetail.setEnabled(true);
			userDetailDao.save(userDetail);
			verificationTokenDao.delete(verificationToken);
			return true;
		}else{
			return false;
		}
	}


	public String getToken(Long id) throws InvalidRequestException,JsonProcessingException,ParseException{

		UserDetail userDetail = userDetailDao.findOne(id);
		if (userDetail == null){
			throw new InvalidRequestException("user not found");
		}

		if (userDetail.getVerificationTokens().isEmpty()){
			throw new InvalidRequestException("User is already activated");
		}


		return getToken(userDetail);
	}

	private String getToken(UserDetail userDetail){
		String token = "";
		for (VerificationToken verificationToken:userDetail.getVerificationTokens()){
			token = verificationToken.getToken();
			break;
		}
		return token;
	}

	private VerificationToken getVerificationToken(UserDetail userDetail){
		VerificationToken verificationToken = null;
		for (VerificationToken verificationToken1:userDetail.getVerificationTokens()){
			verificationToken = verificationToken1;
			break;
		}
		return verificationToken;
	}

	public void resendVerificationEmail(String email) throws InvalidRequestException,JsonProcessingException,ParseException{

		UserValidator.validateEmail(email);
		UserDetail userDetail = userDetailDao.findByEmail(email);
		if (userDetail == null){
			throw new InvalidRequestException("user not found");
		}
		String token =  getToken(userDetail);;
		String tokenVerificationUrl = tokenVerificationLink +"/"+token;
		emailService.sendVerificationTokenEmail(userDetail.getEmail(),tokenVerificationUrl);
	}

	@Transactional
	public void delete(UserBO userBO) throws InvalidRequestException,JsonProcessingException,ParseException{
		UserValidator.validateDelete(userBO);
		UserDetail userDetail = userDetailDao.findByEmail(userBO.getEmail());
		if (userDetail == null){
			throw new InvalidRequestException("user not found");
		}
		if (!userDetail.isEnabled()){
			throw new InvalidRequestException("Please verify email to account");
		}
		VerificationToken verificationToken = getVerificationToken(userDetail);
		if (verificationToken != null) {
			verificationTokenDao.delete(verificationToken);
		}
		userDetailDao.delete(userDetail);
	}

	@Transactional
	public UserBO update(UserBO userBO) throws InvalidRequestException,JsonProcessingException,ParseException{
		UserValidator.validateUpdate(userBO);
		UserDetail userDetail = userDetailDao.findByEmail(userBO.getEmail());

		if (userDetail == null){
			throw new InvalidRequestException("user not found");
		}

		if (!userDetail.isEnabled()){
			throw new InvalidRequestException("Please verify email to account");
		}

		UserDetail updateUserDetail = userConverter.getUpdateUser(userBO);
		updateUserDetail.setId(userDetail.getId());
		updateUserDetail = userDetailDao.save(userDetail);

		return userConverter.getUserBO(updateUserDetail);
	}


}
