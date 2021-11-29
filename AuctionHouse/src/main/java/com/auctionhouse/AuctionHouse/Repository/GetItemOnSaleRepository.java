package com.auctionhouse.AuctionHouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auctionhouse.AuctionHouse.Entities.GetItemOnSale;
import com.auctionhouse.AuctionHouse.Entities.PostItemOnSale;

import java.util.List;

import javax.persistence.Transient;

@Repository
public interface GetItemOnSaleRepository extends JpaRepository<GetItemOnSale, Long> {

	@Query(value = "	SELECT itemId, item_on_sale.name, initialPrice, item_on_sale.description, timeLimit, categoryName AS category, conditionName AS \"condition\", locationName AS location \r\n" + 
			" FROM \r\n" + 
			"	(item_on_sale NATURAL JOIN \r\n" + 
			"	item_category NATURAL JOIN \r\n" + 
			"	item_condition NATURAL JOIN \r\n" + 
			"	item_location NATURAL JOIN \r\n" + 
			"	category NATURAL JOIN \r\n" + 
			"	cs157a_project.condition \r\n" + 
			"	NATURAL JOIN location)\r\n" + 
			"	WHERE itemId IN\r\n" + 
			"(SELECT itemId FROM sells WHERE userId = :userId);", nativeQuery = true)
	List<GetItemOnSale> findItemOnSaleByUserId(@Param("userId") Long userId);
}