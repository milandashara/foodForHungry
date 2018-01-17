package com.foodForHungry.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.foodForHungry.bo.FoodBO;
import com.foodForHungry.entity.Food;
import com.foodForHungry.exception.InvalidRequestException;
import com.foodForHungry.util.DateUtil;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mashara on 5/12/17.
 */
public class FoodValidator {

	public static void validateCreate(FoodBO foodBO) throws InvalidRequestException,JsonProcessingException{
		if (foodBO == null){
			throw new InvalidRequestException("empty request");
		}

		Map<String,String> errorMap = new HashMap();

		if (StringUtils.isEmpty(foodBO.getFromdateStr())){
			errorMap.put("fromTime","it is mandatory");
		}

		if (StringUtils.isEmpty(foodBO.getTodateStr())){
			errorMap.put("toTime","it is mandatory");
		}

		if (StringUtils.isEmpty(foodBO.getPickupDateStr())){
			errorMap.put("pickupDate","it is mandatory");
		}

		if (StringUtils.isEmpty(foodBO.getAddress())){
			errorMap.put("address","it is mandatory");
		}

		if (StringUtils.isEmpty(foodBO.getClientTimeZone())){
			errorMap.put("clientTimeZone","it is mandatory");
		}else {
			if (!DateUtil.isValidTimeZone(foodBO.getClientTimeZone())){
				errorMap.put("clientTimeZone","invalid time zone");
			}
		}

		if (StringUtils.isEmpty(foodBO.getCountryCode())){
			errorMap.put("countryCode","it is mandatory");
		}

		if (StringUtils.isEmpty(foodBO.getDescription())){
			errorMap.put("description","it is mandatory");
		}

		if (StringUtils.isEmpty(foodBO.getLatitude())){
			errorMap.put("latitude","it is mandatory");
		}

		if (StringUtils.isEmpty(foodBO.getLongitude())){
			errorMap.put("longitude","it is mandatory");
		}

		if (StringUtils.isEmpty(foodBO.getNumPeople())){
			errorMap.put("numOfPeople","it is mandatory");
		}

		if (StringUtils.isEmpty(foodBO.getType())){
			errorMap.put("food type","it is mandatory");
		}

		if (StringUtils.isEmpty(foodBO.getUserEmail())){
			errorMap.put("user email","it is mandatory");
		}

		if (!errorMap.isEmpty()){
			String json = new ObjectMapper().writeValueAsString(errorMap);
			throw new InvalidRequestException(json);
		}


	}

	public static void validateUpdate(FoodBO foodBO) throws InvalidRequestException,JsonProcessingException{
		validateCreate(foodBO);
		validateDelete(foodBO);
	}

	public static void validateDelete(FoodBO foodBO) throws InvalidRequestException,JsonProcessingException{
		if (foodBO == null){
			throw new InvalidRequestException("empty request");
		}

		Map<String,String> errorMap = new HashMap();

		if (StringUtils.isEmpty(foodBO.getId())){
			errorMap.put("id","it is mandatory");
		}


		if (!errorMap.isEmpty()){
			String json = new ObjectMapper().writeValueAsString(errorMap);
			throw new InvalidRequestException(json);
		}


	}

	public static void validateTime(Food food) throws InvalidRequestException{
		Timestamp currentTimeStamp = DateUtil.getCurrentTimestampInUTC();
		currentTimeStamp.setHours(currentTimeStamp.getHours()-1);
		if(currentTimeStamp.after(food.getPickUpDate())){
			throw new InvalidRequestException("pickup date should be equal or greater than today");
		}
		if (food.getFromTime().after(food.getToTime())){
			throw new InvalidRequestException("invalid from time");
		}
	}
}
