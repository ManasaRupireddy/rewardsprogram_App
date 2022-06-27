package com.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rewardsprogram.RewardsprogramApplication;
import com.rewardsprogram.model.CustomerMonthlyPoints;
import com.rewardsprogram.model.CustomerRewards;

/**
 * For integration tests to run, you need to define spring boot class to start
 * web service
 * 
 * @author Manasa
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = RewardsprogramApplication.class)
class RewardsApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private final String API_CUSTOMER_SUMMARY = "customerrewards/get";

	@Test
	void customerRewardsEndPointTest1() throws Exception {
		String baseUrl = "http://localhost:" + port + "/";
		ResponseEntity<CustomerRewards> responseEntity = restTemplate
				.getForEntity(baseUrl + API_CUSTOMER_SUMMARY + "/1", CustomerRewards.class);
		assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		CustomerRewards rewardsInfo = responseEntity.getBody();
		assertTrue(rewardsInfo != null && rewardsInfo.getTotalPoints() > 0);
	}
	@Test
	void customerRewardsEndPointTest2() throws Exception {
		String baseUrl = "http://localhost:" + port + "/";
		ResponseEntity<CustomerRewards> responseEntity = restTemplate
				.getForEntity(baseUrl + API_CUSTOMER_SUMMARY + "/2", CustomerRewards.class);
		assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		CustomerRewards rewardsInfo = responseEntity.getBody();
		assertTrue(rewardsInfo != null && rewardsInfo.getTotalPoints() > 0);
	}


	@Test
	void customerRewardsEndPointTest3() throws Exception {
		String baseUrl = "http://localhost:" + port + "/";
		ResponseEntity<CustomerRewards> responseEntity = restTemplate
				.getForEntity(baseUrl + API_CUSTOMER_SUMMARY + "/1", CustomerRewards.class);
		assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		CustomerRewards rewardsInfo = responseEntity.getBody();
		assertTrue(rewardsInfo != null && rewardsInfo.getTotalPoints() > 0);
		responseEntity.getBody().getMonthlySummary();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

		YearMonth date = YearMonth.parse("2022-05", formatter);
		YearMonth date1 = YearMonth.parse("2022-06", formatter);
		List<CustomerMonthlyPoints> monthlySummaryArray = new ArrayList<CustomerMonthlyPoints>();
		CustomerMonthlyPoints custMonthObj1 = CustomerMonthlyPoints.builder().points(5600).yearMonth(date).build();
		CustomerMonthlyPoints custMonthObj2 = CustomerMonthlyPoints.builder().points(16200).yearMonth(date1).build();

		monthlySummaryArray.add(custMonthObj1);
		monthlySummaryArray.add(custMonthObj2);

		assertEquals(responseEntity.getBody().getCustomerId(), 1);
		assertEquals(responseEntity.getBody().getTotalPoints(), 21800);

		assertEquals(responseEntity.getBody().getMonthlySummary().get(0).getPoints(),
				monthlySummaryArray.get(0).getPoints());
		assertEquals(responseEntity.getBody().getMonthlySummary().get(1).getPoints(),
				monthlySummaryArray.get(1).getPoints());

		assertEquals(responseEntity.getBody().getMonthlySummary().get(0).getYearMonth(),
				monthlySummaryArray.get(0).getYearMonth());
		assertEquals(responseEntity.getBody().getMonthlySummary().get(1).getYearMonth(),
				monthlySummaryArray.get(1).getYearMonth());

	}
	
	@Test
	void customerRewardsEndPointTest4() throws Exception {
		String baseUrl = "http://localhost:" + port + "/";
		ResponseEntity<CustomerRewards> responseEntity = restTemplate
				.getForEntity(baseUrl + API_CUSTOMER_SUMMARY + "/2", CustomerRewards.class);
		assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		CustomerRewards rewardsInfo = responseEntity.getBody();
		assertTrue(rewardsInfo != null && rewardsInfo.getTotalPoints() > 0);
		responseEntity.getBody().getMonthlySummary();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

		YearMonth date = YearMonth.parse("2022-05", formatter);
		YearMonth date1 = YearMonth.parse("2022-06", formatter);
		List<CustomerMonthlyPoints> monthlySummaryArray = new ArrayList<CustomerMonthlyPoints>();
		CustomerMonthlyPoints custMonthObj1 = CustomerMonthlyPoints.builder().points(750).yearMonth(date).build();
		CustomerMonthlyPoints custMonthObj2 = CustomerMonthlyPoints.builder().points(20850).yearMonth(date1).build();
		monthlySummaryArray.add(custMonthObj1);
		monthlySummaryArray.add(custMonthObj2);

		assertEquals(responseEntity.getBody().getCustomerId(), 2);
		assertEquals(responseEntity.getBody().getTotalPoints(), 21600);

		assertEquals(responseEntity.getBody().getMonthlySummary().get(0).getPoints(),
				monthlySummaryArray.get(0).getPoints());
		assertEquals(responseEntity.getBody().getMonthlySummary().get(1).getPoints(),
				monthlySummaryArray.get(1).getPoints());

		assertEquals(responseEntity.getBody().getMonthlySummary().get(0).getYearMonth(),
				monthlySummaryArray.get(0).getYearMonth());
		assertEquals(responseEntity.getBody().getMonthlySummary().get(1).getYearMonth(),
				monthlySummaryArray.get(1).getYearMonth());

	}	
	
}