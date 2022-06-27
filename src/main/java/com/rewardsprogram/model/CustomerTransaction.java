/**
 * 
 */
package com.rewardsprogram.model;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * This class represents purchase transaction entity data.
 * @author Manasa
 *
 */
@Entity
@Data
@Table(name = "customer_transaction")
public class CustomerTransaction {
	
	@Id
	@Column(name = "id")
    private Long id;
    
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "purchase_details")
    private String purchaseDetails;

    @Column(name = "purchase_amount")
    private Double purchaseAmount;
    
    @Column(name = "purchase_date")
    private Date purchaseDate;

    private YearMonth purchaseYearMonth;
    
    public YearMonth getPurchaseYearMonth( ) {
    	this.purchaseYearMonth= YearMonth.from(this.purchaseDate.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate());
    	return purchaseYearMonth;
    }
}
