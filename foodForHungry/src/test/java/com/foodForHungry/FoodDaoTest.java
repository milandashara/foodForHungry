package com.foodForHungry;

import com.foodForHungry.bo.FoodBO;
import com.foodForHungry.entity.Food;
import com.foodForHungry.entity.UserDetail;
import com.foodForHungry.repo.FoodDao;
import com.foodForHungry.service.FoodServiceImpl;
import com.foodForHungry.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

/**
 * Created by mashara on 1/12/17.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class FoodDaoTest {

	@Autowired
	private FoodDao foodDao;

	@Autowired
	private FoodServiceImpl foodServiceImpl;


	@Autowired
	private EntityManager entityManager;

	@Test
	public void findById() {


		UserDetail userDetail = new UserDetail();
		userDetail.setEmail("milandashara@gmail.com");
		entityManager.persist(userDetail);
		entityManager.flush();
		// given
		Food food = new Food();
		food.setDescription("indian food");
		food.setUserDetail(userDetail);
		entityManager.persist(food);
		entityManager.flush();

		// when
		Food found = foodDao.findOne(1L);
		userDetail=food.getUserDetail();
		// then
		Assert.assertTrue(found != null);
		Assert.assertTrue(userDetail != null);

		entityManager.remove(found);
		entityManager.remove(userDetail);
		entityManager.flush();
	}

	@Test
	public void createFoodTest(){
		try {


			UserDetail userDetail = new UserDetail();
			userDetail.setEmail("milandashara@gmail.com");
			entityManager.persist(userDetail);
			entityManager.flush();

			// given
			FoodBO foodBO = new FoodBO();
			foodBO.setDescription("indian food");
			foodBO.setAddress("test");
			foodBO.setClientTimeZone("Asia/Singapore");
			foodBO.setCountryCode("SGP");
			foodBO.setFromdateStr(DateUtil.getTimeStampStr(DateUtil.getCurrentTimestampInUTC(),foodBO.getClientTimeZone()));
			foodBO.setLatitude(41.40338);
			foodBO.setLongitude(2.17403);
			foodBO.setNumPeople(2);
			foodBO.setPickupDateStr(DateUtil.getTimeStampStr(DateUtil.getCurrentTimestampInUTC(),foodBO.getClientTimeZone()));
			foodBO.setTodateStr(DateUtil.getTimeStampStr(DateUtil.getCurrentTimestampInUTC(),foodBO.getClientTimeZone()));
			foodBO.setType("veg");
			foodBO.setUserEmail("milandashara@gmail.com");

			FoodBO newFood = foodServiceImpl.create(foodBO);
			Assert.assertTrue(newFood != null);
			Assert.assertTrue(userDetail != null);

			entityManager.remove(foodDao.findOne(newFood.getId()));
			entityManager.remove(userDetail);
			//entityManager.persist(food);
			entityManager.flush();
		}catch (Exception e){
			Assert.fail(e.getMessage());
		}
	}



}
