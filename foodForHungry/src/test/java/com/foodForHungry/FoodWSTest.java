package com.foodForHungry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodForHungry.bo.FoodBO;
import com.foodForHungry.bo.UserBO;
import com.foodForHungry.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by mashara on 6/12/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FoodForHungryApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
public class FoodWSTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testCreateFood() {
		try {

			FoodBO foodBO = new FoodBO();
			foodBO.setDescription("indian food");
			foodBO.setAddress("test");
			foodBO.setClientTimeZone("Asia/Singapore");
			foodBO.setCountryCode("SGP");
			foodBO.setFromdateStr(DateUtil.getTimeStampStr(DateUtil.getCurrentTimestampInUTC(), foodBO.getClientTimeZone()));
			foodBO.setLatitude(41.40338);
			foodBO.setLongitude(2.17403);
			foodBO.setNumPeople(2);
			foodBO.setPickupDateStr(DateUtil.getTimeStampStr(DateUtil.getCurrentTimestampInUTC(), foodBO.getClientTimeZone()));
			foodBO.setTodateStr(DateUtil.getTimeStampStr(DateUtil.getCurrentTimestampInUTC(), foodBO.getClientTimeZone()));
			foodBO.setType("veg");
			foodBO.setUserEmail("milandashara@gmail.com");

			UserBO userBO = new UserBO();
			userBO.setName("milan");
			userBO.setEmail("milandashara@gmail.com");
			userBO.setPassword("P@ssw0rd");
			userBO.setVerifyPassword("P@ssw0rd");

			//create user
			HttpEntity<UserBO> userEntity = new HttpEntity<UserBO>(userBO, headers);

			ResponseEntity<String> userResponse = restTemplate.exchange(
					createURLWithPort("/user"),
					HttpMethod.POST, userEntity, String.class);

			Assert.assertTrue(userResponse.getBody(), userResponse != null);
			Assert.assertTrue(userResponse.getStatusCode().toString(),userResponse.getStatusCode() == HttpStatus.CREATED);

			ObjectMapper mapper1 = new ObjectMapper();
			UserBO newUserBO = mapper1.readValue(userResponse.getBody().toString(),UserBO.class);

			HttpEntity<String> tokenEntity = new HttpEntity<String>("", headers);

			String gettokenUrl = "/token/"+newUserBO.getId();
			System.out.println("get token url"+gettokenUrl);
			//get token
			ResponseEntity<String> tokenResponse = restTemplate.exchange(
					createURLWithPort(gettokenUrl),
					HttpMethod.GET, tokenEntity, String.class);

			System.out.println(tokenResponse.getBody());
			String token = tokenResponse.getBody().toString();

			HttpEntity<String> verifytokenEntity = new HttpEntity<String>("", headers);

			//verify token
			ResponseEntity<String> verifytokenResponse = restTemplate.exchange(
					createURLWithPort("/verify/"+token),
					HttpMethod.GET, verifytokenEntity, String.class);

			Assert.assertTrue(verifytokenResponse.getBody(), verifytokenResponse != null);
			Assert.assertTrue(verifytokenResponse.getStatusCode().toString(),verifytokenResponse.getStatusCode() == HttpStatus.OK);

			//create food
			HttpEntity<FoodBO> entity = new HttpEntity<FoodBO>(foodBO, headers);
			ResponseEntity<String> response = restTemplate.withBasicAuth("milandashara@gmail.com","P@ssw0rd").exchange(
					createURLWithPort("/food"),
					HttpMethod.POST, entity, String.class);


			Assert.assertTrue(response.getBody(), response != null);
			Assert.assertTrue(response.getBody(),response.getStatusCode() == HttpStatus.CREATED);
			System.out.println("food response :" + response.getBody().toString());
			//delete food
			ObjectMapper mapper = new ObjectMapper();
			FoodBO deleteFoodBO = mapper.readValue(response.getBody().toString(),FoodBO.class);
			System.out.println("deleteFoodBO :" + deleteFoodBO.toString());
			HttpEntity<FoodBO> deleteEntity = new HttpEntity<FoodBO>(deleteFoodBO, headers);
			ResponseEntity<String> deleteFoodResponse = restTemplate.withBasicAuth("milandashara@gmail.com","P@ssw0rd").exchange(
					createURLWithPort("/food"),
					HttpMethod.DELETE, deleteEntity, String.class);


			System.out.println("delete Food response :" + deleteFoodResponse.getBody());
			Assert.assertTrue(deleteFoodResponse.getBody(), deleteFoodResponse != null);
			Assert.assertTrue(deleteFoodResponse.getStatusCode().toString(),deleteFoodResponse.getStatusCode() == HttpStatus.ACCEPTED);


			//delete user
			UserBO deleteUserBO = mapper.readValue(userResponse.getBody().toString(),UserBO.class);
			HttpEntity<UserBO> deleteUserEntity = new HttpEntity<UserBO>(deleteUserBO, headers);
			ResponseEntity<String> deleteUserResponse = restTemplate.withBasicAuth("milandashara@gmail.com","P@ssw0rd").exchange(
					createURLWithPort("/user"),
					HttpMethod.DELETE, deleteUserEntity, String.class);
			System.out.println("delete user response :" + deleteUserResponse.getBody());
			Assert.assertTrue(deleteUserResponse.getBody(), deleteUserResponse != null);
			Assert.assertTrue(deleteUserResponse.getStatusCode().toString(),deleteUserResponse.getStatusCode() == HttpStatus.ACCEPTED);

		}catch (Exception e){
			Assert.fail(e.getMessage());
		}
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + "/foodForHungry/ws"+uri;
	}
}