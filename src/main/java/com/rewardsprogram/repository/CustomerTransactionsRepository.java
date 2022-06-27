package com.rewardsprogram.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rewardsprogram.model.CustomerTransaction;

/**
 * Customer Transaction repository for database operations.
 * 
 * @author Manasa
 *
 */
@Repository
public interface CustomerTransactionsRepository extends CrudRepository<CustomerTransaction, Long> {

	public List<CustomerTransaction> findByCustomerId(Integer customerId);

}
