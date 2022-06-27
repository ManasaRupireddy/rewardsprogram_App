package com.rewardsprogram.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * This class pojo reperesents customer rewards information.
 * @author Manasa
 *
 */
@Builder
@Getter
public class CustomerRewards {	
	
	/**
	 * Customer Id.
	 */
	private Integer customerId;
	/**
	 * Total Points.
	 */
	private Integer totalPoints;

	/**
	 * monthly summary points
	 */
	private List<CustomerMonthlyPoints> monthlySummary; 

	
		
}
