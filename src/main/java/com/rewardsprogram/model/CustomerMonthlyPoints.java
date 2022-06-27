package com.rewardsprogram.model;


import java.time.YearMonth;

import lombok.Builder;
import lombok.Getter;

/**
 * This class contains points and year-month info.
 * @author Manasa
 *
 */
@Builder
@Getter
public class CustomerMonthlyPoints {

	/**
	 * customer monthly reward points
	 */
	private Integer points;
	/**
	 * Year and month of points.
	 */
	private YearMonth yearMonth;
	
}
