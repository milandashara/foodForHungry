package com.foodForHungry.util;

import com.foodForHungry.bo.FoodBO;
import com.foodForHungry.entity.Food;
import com.foodForHungry.exception.InvalidRequestException;
import com.foodForHungry.repo.UserDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Created by mashara on 4/12/17.
 */
@Component
public class FoodConverter {

	@Autowired
	private UserDetailDao userDetailDao;

	public Food getFood(FoodBO foodBO) throws ParseException{
		Food food = new Food();
		food.setUserDetail(userDetailDao.findByEmail(foodBO.getUserEmail()));
		food.setDescription(foodBO.getDescription());
		food.setAddress(foodBO.getAddress());
		food.setPickUpDate(DateUtil.getTimeStampInUTC(foodBO.getPickupDateStr()));
		food.setFromTime(DateUtil.getTimeStampInUTC(foodBO.getFromdateStr()));
		food.setToTime(DateUtil.getTimeStampInUTC(foodBO.getTodateStr()));
		food.setLatitude(foodBO.getLatitude());
		food.setLongitude(foodBO.getLongitude());
		food.setCreateTimestamp(DateUtil.getCurrentTimestampInUTC());
		food.setUpdateTimestamp(DateUtil.getCurrentTimestampInUTC());
		food.setId(foodBO.getId());
		food.setNumPeople(foodBO.getNumPeople());
		food.setType(foodBO.getType());
		food.setCountryCode(foodBO.getCountryCode());
		food.setClientTimeZone(foodBO.getClientTimeZone());
		return food;
	}

	public FoodBO getFoodBO(Food food) throws ParseException,InvalidRequestException{
		try {


			FoodBO foodBO = new FoodBO();
			foodBO.setUserEmail(food.getUserDetail().getEmail());
			foodBO.setDescription(food.getDescription());
			foodBO.setAddress(food.getAddress());
			foodBO.setPickupDateStr(DateUtil.getTimeStampStr(food.getPickUpDate(), food.getClientTimeZone()));
			foodBO.setFromdateStr(DateUtil.getTimeStampStr(food.getFromTime(), food.getClientTimeZone()));
			foodBO.setTodateStr(DateUtil.getTimeStampStr(food.getToTime(), food.getClientTimeZone()));
			foodBO.setLatitude(food.getLatitude());
			foodBO.setLongitude(food.getLongitude());
			foodBO.setId(food.getId());
			foodBO.setNumPeople(food.getNumPeople());
			foodBO.setType(food.getType());
			foodBO.setCountryCode(food.getCountryCode());
			foodBO.setClientTimeZone(food.getClientTimeZone());
			return foodBO;
		}catch (NullPointerException npe){
			throw new InvalidRequestException("user not found");
		}
	}
}
