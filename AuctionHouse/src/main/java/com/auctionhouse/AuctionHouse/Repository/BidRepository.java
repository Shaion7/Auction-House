package com.auctionhouse.AuctionHouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auctionhouse.AuctionHouse.Entities.Bid;
import com.auctionhouse.AuctionHouse.Entities.GetItemOnSale;
import com.auctionhouse.AuctionHouse.Entities.User;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
	
//	Double findBidAmountByItemId(@Param("itemId") int itemId);
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO bids (itemId, userId, bidAmount) VALUES (:itemId, :userId, :bidAmount)", nativeQuery = true)
	void save(@Param("itemId") Long itemId, @Param("userId") Long userId, @Param("bidAmount") Double bidAmount);

	@Transactional
	@Modifying
	@Query(value = "UPDATE bids SET bidAmount = :bidAmount, userId = :userId WHERE itemId = :itemId", nativeQuery = true)
	void updateBid(@Param("itemId") Long itemId, @Param("userId") Long userId, @Param("bidAmount") Double bidAmount);
	
	@Query(value = "SELECT b.itemId, b.userId, i.name, i.categoryName AS category, i.conditionName AS \"condition\", i.locationName AS location, i.timeLimit, b.bidAmount \r\n" + 
			"	FROM bids b, \r\n" + 
			"	(\r\n" + 
			"	SELECT * FROM\r\n" + 
			"	item_on_sale NATURAL JOIN\r\n" + 
			"	item_category NATURAL JOIN\r\n" + 
			"	item_condition NATURAL JOIN\r\n" + 
			"	item_location NATURAL JOIN\r\n" + 
			"	category NATURAL JOIN\r\n" + 
			"	cs157a_project.condition\r\n" + 
			"	NATURAL JOIN location\r\n" + 
			"	) AS i\r\n" + 
			"	WHERE b.userId = :userId AND b.itemId = i.itemId", nativeQuery = true)
	List<Bid> getUserBids(@Param("userId") Long userId);
	
	@Query(value = "SELECT * from bids WHERE itemId = :itemId", nativeQuery = true)
	Bid getBid(@Param("itemId") Long itemId);

//	@Query(value = "SELECT * from bids WHERE itemId = :itemId", nativeQuery = true)
//	Bid getUserThatBidded(@Param("itemId") Long itemId);
}