package com.auctionhouse.AuctionHouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auctionhouse.AuctionHouse.Entities.Bid;
import com.auctionhouse.AuctionHouse.Entities.User;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
	
//	Double findBidAmountByItemId(@Param("itemId") int itemId);
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO bids (itemId, userId, bidAmount) VALUES (:itemId, :userId, :bidAmount)", nativeQuery = true)
	void save(@Param("itemId") Long itemId, @Param("userId") Long userId, @Param("bidAmount") Double bidAmount);
}