package com.auctionhouse.AuctionHouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auctionhouse.AuctionHouse.Entities.ExpiredItem;
import com.auctionhouse.AuctionHouse.Entities.GetItemOnSale;
import com.auctionhouse.AuctionHouse.Entities.PostItemOnSale;
import com.auctionhouse.AuctionHouse.Entities.SoldItem;

import java.util.List;

import javax.persistence.Transient;

@Repository
public interface ExpiredItemRepository extends JpaRepository<ExpiredItem, Long> {
	
// Insert into expired
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO expired (expiredItemId, userId) VALUES (:expiredItemId, :userId)", nativeQuery = true)
	void insertIntoExpired(@Param("expiredItemId") Long soldItemId, @Param("userId") Long userId);
	
	@Query(value = "SELECT *\n"
			+ "FROM expired_item ei NATURAL JOIN expired e\n"
			+ "WHERE userId = :userId", nativeQuery= true)
	List<ExpiredItem> getExpiredItems(@Param("userId") Long userId);
}