/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */
 
package com.foodForHungry.config;


import com.foodForHungry.repo.FoodDao;
import com.foodForHungry.repo.UserDetailDao;
import com.foodForHungry.service.FoodServiceImpl;
import com.foodForHungry.util.FoodConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringServicesConfig {
	
	// TODO: Inject the repository
	@Autowired
	FoodDao foodDao;


	@Autowired
	private FoodConverter foodConverter;

	@Autowired
	private UserDetailDao userDetailDao;

	@Bean
	public FoodServiceImpl foodService(){
		return new FoodServiceImpl(foodDao,foodConverter, userDetailDao);
	}
	
	// TODO: Define the catalog bean
//	@Bean
//	public Catalog catalog() {
//		CatalogImpl catalog = new CatalogImpl(foodDao);
//		return catalog;
//	}

}