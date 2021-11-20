package com.auctionhouse.AuctionHouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auctionhouse.AuctionHouse.Entities.ItemOnSale;

import java.util.List;

@Repository
public interface ItemOnSaleRepository extends JpaRepository<ItemOnSale, Long> {
	
}
