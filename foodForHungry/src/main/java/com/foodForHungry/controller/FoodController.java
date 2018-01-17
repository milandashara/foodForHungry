package com.foodForHungry.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.foodForHungry.bo.FoodBO;
import com.foodForHungry.exception.InvalidRequestException;
import com.foodForHungry.service.FoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;

/**
 * Created by mashara on 6/12/17.
 */

@RestController
public class FoodController {


	@Autowired
	private FoodServiceImpl foodServiceImpl;

	@RequestMapping(value = "/food",method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public FoodBO addFood(@RequestBody FoodBO foodBO) throws InvalidRequestException,JsonProcessingException,ParseException{
		return foodServiceImpl.create(foodBO);
	}

	@RequestMapping(value = "/food",method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteFood(@RequestBody FoodBO foodBO) throws InvalidRequestException,JsonProcessingException,ParseException{
		 foodServiceImpl.delete(foodBO);
	}

	@RequestMapping(value = "/food",method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public FoodBO updateFood(@RequestBody FoodBO foodBO) throws InvalidRequestException,JsonProcessingException,ParseException{
		return foodServiceImpl.update(foodBO);
	}
}
