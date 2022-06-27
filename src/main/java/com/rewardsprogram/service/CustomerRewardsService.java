/**
 * 
 */
package com.rewardsprogram.service;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewardsprogram.model.CustomerMonthlyPoints;
import com.rewardsprogram.model.CustomerRewards;
import com.rewardsprogram.model.CustomerTransaction;
import com.rewardsprogram.repository.CustomerTransactionsRepository;
import com.rewardsprogram.util.LogMessages;
import com.rewardsprogram.util.RewardsConstants;

/**
 * Service class for rewards operations.
 * 
 * @author Manasa
 *
 */
@Service
public class CustomerRewardsService {

	@Autowired
	private CustomerTransactionsRepository customerTransactionsRepository;

	private static final Logger logger = LoggerFactory.getLogger(CustomerRewardsService.class);

	/**
	 * Get monthly rewards points summary by customer Id.
	 * 
	 * @param customerId
	 * @return
	 */
	public CustomerRewards getCustomerRewardsPointsById(Integer customerId) throws Exception {
		CustomerRewards customerRewards = null;
		// Fetching all the customer purchase transactions.
		List<CustomerTransaction> transactions = customerTransactionsRepository.findByCustomerId(customerId);

		// Validate if customers purchase transactions are not null/ empty.
		if (transactions != null && !transactions.isEmpty()) {
			logger.info(LogMessages.TRANSACTIONS_FOUND_SUCCESS);

			// Creating a map of YearMonth and transactions specific to it.
			Map<YearMonth, List<CustomerTransaction>> yearMonthMap = transactions.stream()
					.collect(Collectors.groupingBy(CustomerTransaction::getPurchaseYearMonth));
			
			// Create monthly summary of points for each YearMonth.
						List<CustomerMonthlyPoints> monthlySummary = yearMonthMap.entrySet().stream()
								.map(e -> CustomerMonthlyPoints.builder().yearMonth(e.getKey())
										.points(getRewardPoints(e.getValue())).build())
								.collect(Collectors.toList());

			customerRewards = CustomerRewards.builder().customerId(customerId).monthlySummary(monthlySummary)
					.totalPoints(monthlySummary.stream().mapToInt(CustomerMonthlyPoints::getPoints).sum()).build();
		} else {
			logger.info(LogMessages.NO_TRANSACTIONS_FOUND);
			customerRewards = customerRewards.builder().customerId(customerId).monthlySummary(null)
					.totalPoints(0).build();
        }

		return customerRewards;
	}

	/**
	 * Get customer reward points for a specific month.
	 * 
	 * @param transactions
	 * @return
	 */
	private Integer getRewardPoints(List<CustomerTransaction> transactions) {
		int totalAmount = 0;
		int totalPoints = 0;
		for (CustomerTransaction transaction : transactions) {
			totalAmount = transaction.getPurchaseAmount().intValue();
			if (totalAmount > RewardsConstants.CUSTOMER_REWARD_VALUE_2) {
				totalPoints = totalPoints
						+ (RewardsConstants.CUSTOMER_REWARD_VALUE_2 - RewardsConstants.CUSTOMER_REWARD_VALUE_1)
								* RewardsConstants.CUSTOMER_REWARD_VALUE_1
						+ (totalAmount - RewardsConstants.CUSTOMER_REWARD_VALUE_2) * RewardsConstants.CUSTOMER_REWARD_VALUE_1;
			} else if (totalAmount > RewardsConstants.CUSTOMER_REWARD_VALUE_1) {
				totalPoints = totalPoints
						+ (totalAmount - RewardsConstants.CUSTOMER_REWARD_VALUE_1) * RewardsConstants.CUSTOMER_REWARD_VALUE_1;
			}
		}
		return totalPoints;
	}

}