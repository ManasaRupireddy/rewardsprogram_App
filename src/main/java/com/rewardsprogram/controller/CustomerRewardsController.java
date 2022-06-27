/**
 * 
 */
package com.rewardsprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rewardsprogram.customerexception.CustomErrorException;
import com.rewardsprogram.model.CustomerRewards;
import com.rewardsprogram.service.CustomerRewardsService;

/**
 * Controller class for fetching reward points for specific customer.
 * 
 * @author Manasa
 *
 */
@RestController
@RequestMapping("/customerrewards")
public class CustomerRewardsController {

	@Autowired
	private CustomerRewardsService customerRewardsService;

	/**
	 * Get customer reward points by Id.
	 * 
	 * @param customerId
	 * @return
	 */
	// @RequestMapping("/get/{customerId}")
	@RequestMapping(value = { "/get/{customerId}" }, method = RequestMethod.GET)
	public ResponseEntity<CustomerRewards> getCustomerRewardsPointsById(
			@PathVariable(required = false) Integer customerId) throws Exception {
		CustomerRewards customerRewards = null;
		try {
			if (customerId != null) {
				customerRewards = customerRewardsService.getCustomerRewardsPointsById(customerId);
			}
		} catch (NullPointerException e) {

			throw new CustomErrorException(HttpStatus.NOT_FOUND, e.getMessage(), (Integer) customerId);
		}
		return ResponseEntity.ok().body(customerRewards);

	}

}
