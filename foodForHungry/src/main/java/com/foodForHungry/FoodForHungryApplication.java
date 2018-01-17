package com.foodForHungry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.foodForHungry")
@EnableTransactionManagement
public class FoodForHungryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodForHungryApplication.class, args);
	}
}
