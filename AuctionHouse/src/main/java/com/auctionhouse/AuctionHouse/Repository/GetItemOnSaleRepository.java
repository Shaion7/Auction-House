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

// Remove from item_on_sale, sells, and bids
	@Transactional
	@Modifying
	@Query(value="DELETE FROM item_on_sale WHERE itemId = :itemId", nativeQuery = true)
	void removeFromItemOnSale(@Param("itemId") Long itemId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM sells WHERE itemId = :itemId", nativeQuery = true)
	void removeFromSells(@Param("itemId") Long itemId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM bids WHERE itemId = :itemId", nativeQuery = true)
	void removeFromBids(@Param("itemId") Long itemId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM item_category WHERE itemId = :itemId", nativeQuery = true)
	void removeFromItemCategory(@Param("itemId") Long itemId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM item_condition WHERE itemId = :itemId", nativeQuery = true)
	void removeFromItemCondition(@Param("itemId") Long itemId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM item_location WHERE itemId = :itemId", nativeQuery = true)
	void removeFromItemLocation(@Param("itemId") Long itemId);


// Insert into expired_item
//	@Query(value = "", nativeQuery = true)
//	void insertIntoExpiredItem()

//Insert into sold_item


// Get all items on sale except the items the user is selling
	@Query(value = "SELECT i.itemId, b.userId, i.name, i.description, i.categoryName AS category, i.conditionName AS \"condition\", i.locationName AS location, i.timeLimit, b.bidAmount AS initialPrice\r\n" + 
			"FROM (SELECT sells.itemId, bidAmount, sells.userId FROM bids INNER JOIN \r\n" + 
			"	sells ON bids.itemId = sells.itemId) as b INNER JOIN\r\n" + 
			"	(SELECT * FROM item_on_sale NATURAL JOIN\r\n" + 
			"	item_category NATURAL JOIN\r\n" + 
			"	item_condition NATURAL JOIN\r\n" + 
			"	item_location NATURAL JOIN\r\n" + 
			"	category NATURAL JOIN\r\n" + 
			"	cs157a_project.condition\r\n" + 
			"	NATURAL JOIN location) as i ON b.itemId = i.itemId\r\n" + 
			"WHERE b.userId <> :userId", nativeQuery = true)
	List<GetItemOnSale> findAll(@Param("userId") Long userId);

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