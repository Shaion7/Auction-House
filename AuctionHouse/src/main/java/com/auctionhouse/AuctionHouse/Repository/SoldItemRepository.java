package com.auctionhouse.AuctionHouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auctionhouse.AuctionHouse.Entities.GetItemOnSale;
import com.auctionhouse.AuctionHouse.Entities.PostItemOnSale;
import com.auctionhouse.AuctionHouse.Entities.SoldItem;

import java.util.List;

import javax.persistence.Transient;

@Repository
public interface SoldItemRepository extends JpaRepository<SoldItem, Long> {
	
//  Insert into sold
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO sold (soldItemId, userId) VALUES (:soldItemId, :userId)", nativeQuery = true)
	void insertIntoSold(@Param("soldItemId") Long soldItemId, @Param("userId") Long userId);
	
//  Insert into bought
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO bought (soldItemId, userId) VALUES (:soldItemId, :userId)", nativeQuery = true)
	void insertIntoBought(@Param("soldItemId") Long soldItemId, @Param("userId") Long userId);
	
//  Get userId of user who bought the item
	@Query(value = "SELECT userId FROM bids WHERE itemId = :itemId", nativeQuery = true)
	Long getBoughtUserId(@Param("itemId") Long itemId);
	
//	Get all sold items
	@Query(value = "SELECT s.soldItemId, itemName, itemDescription, finalPrice, username\n"
			+ "FROM (SELECT * FROM sold_item NATURAL JOIN sold) as s,\n"
			+ "(\n"
			+ "SELECT soldItemId, bought.userId, username\n"
			+ "FROM bought INNER JOIN user ON bought.userId = user.userId\n"
			+ ") as b\n"
			+ "WHERE s.userId = 2", nativeQuery = true)
	List<SoldItem> getSoldItems(@Param("userId") Long userId);
}