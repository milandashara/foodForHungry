package com.foodForHungry.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.foodForHungry.bo.FoodBO;
import com.foodForHungry.entity.Food;
import com.foodForHungry.exception.InvalidRequestException;
import com.foodForHungry.repo.FoodDao;
import com.foodForHungry.repo.UserDetailDao;
import com.foodForHungry.util.FoodConverter;
import com.foodForHungry.validator.FoodValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;

/**
 * Created by mashara on 1/12/17.
 */
@Service
public class FoodServiceImpl {

	@Autowired
	private FoodDao foodDao;

	@Autowired
	private FoodConverter foodConverter;

	@Autowired
	private UserDetailDao userDetailDao;

	public FoodServiceImpl(FoodDao foodDao, FoodConverter foodConverter, UserDetailDao userDetailDao) {
		this.foodDao = foodDao;
		this.foodConverter = foodConverter;
		this.userDetailDao = userDetailDao;
	}
	@Transactional
	public FoodBO create(FoodBO foodBO) throws InvalidRequestException,JsonProcessingException,ParseException{
		FoodValidator.validateCreate(foodBO);
		Food newFood = foodConverter.getFood(foodBO);
		FoodValidator.validateTime(newFood);
		newFood = foodDao.save(newFood);
		return foodConverter.getFoodBO(newFood);

	}


	@Transactional
	public void delete(FoodBO foodBO) throws InvalidRequestException,JsonProcessingException,ParseException{
		FoodValidator.validateDelete(foodBO);
		Food food = foodDao.findOne(foodBO.getId());

		if (food == null){
			throw new InvalidRequestException("food not found. invalid food id");
		}

		foodDao.delete(food);
	}

	@Transactional
	public FoodBO update(FoodBO foodBO) throws InvalidRequestException,JsonProcessingException,ParseException{
		FoodValidator.validateUpdate(foodBO);
		Food food = foodDao.findOne(foodBO.getId());

		if (food == null){
			throw new InvalidRequestException("food not found. invalid food id");
		}
		Food newFood = foodConverter.getFood(foodBO);
		newFood.setId(food.getId());
		newFood = foodDao.save(newFood);
		return foodConverter.getFoodBO(newFood);
	}


}
